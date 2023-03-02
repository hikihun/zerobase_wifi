package Service;

import Model.WifiInfoVO;
import com.google.gson.*;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WifiApi {

    public static String getJsonString() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("6949747970736b683733776e41526e","UTF-8") ); /*인증키
(sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입
(xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치
(sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8"));
        /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결
자체에 대한 확인이 필요하므로 추가합니다.*/

        BufferedReader rd;
// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        jsonToString(sb.toString());

        return sb.toString();

    }

    public static List<WifiInfoVO> jsonToString(String jsonString) {
        List <WifiInfoVO > list = new ArrayList<>();

        JsonElement jsonElement = JsonParser.parseString(jsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject TbPublicWifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");

        int list_total_count = TbPublicWifiInfo.get("list_total_count").getAsInt();
        String CODE = TbPublicWifiInfo.getAsJsonObject("RESULT").get("CODE").getAsString();
        String MESSAGE = TbPublicWifiInfo.getAsJsonObject("RESULT").get("MESSAGE").getAsString();
        System.out.println(list_total_count);
        System.out.println(CODE);
        System.out.println(MESSAGE);

        JsonArray row = TbPublicWifiInfo.getAsJsonArray("row");
        if (row != null) {
            row.forEach(e ->{
                Gson gson = new Gson();
                JsonObject jo = e.getAsJsonObject();
//                System.out.println("jo = " + jo);
                WifiInfoVO vo = gson.fromJson(jo.toString(), WifiInfoVO.class);
                list.add(vo);
            });
        }
        System.out.println(list);

        return list;

    }

}


