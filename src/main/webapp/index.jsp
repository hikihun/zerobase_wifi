<%@ page import="Service.WifiInfoList" %>
<%@ page import="Model.WifiInfoVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
    <script>
        // const btn = document.getElementById("getGPS");
        function getGps() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (pos) {
                    document.getElementById("lat").value = pos.coords.latitude;
                    document.getElementById("lnt").value = pos.coords.longitude;
                });
            }
        }

    </script>

</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="/">홈</a> |
    <a href="/history/list">위치 히스토리 목록</a> |
    <a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="/bookmark/list">북마크 보기</a> |
    <a href="/bookmark/group">북마크 그룹 관리</a><br>

    <form method="post" action="/wifiInfoList">
        LAT:<input type="text" value="<%=request.getAttribute("lat")%>" name="lat" id="lat"/>,
        LNT:<input type="text" value="<%=request.getAttribute("lnt")%>" name="lnt" id="lnt"/>
        <input type="button" value="내 위치 가져오기" id="getGPS" onclick="getGps()"/>
        <input type="submit" value="근처 WIFI 정보 보기"/>
    </form>

    <table id="customers">
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명 주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <%

            List<WifiInfoVO> wifiInfoList = (List<WifiInfoVO>) request.getAttribute("wifiInfoList");
            if(wifiInfoList != null && !wifiInfoList.isEmpty()){
                for (WifiInfoVO wifiInfo : wifiInfoList) {
        %>
            <tr>
                <td><%=wifiInfo.getDistance() %></td>
                <td><%=wifiInfo.getX_SWIFI_MGR_NO() %></td>
                <td><%=wifiInfo.getX_SWIFI_WRDOFC() %> </td>
                <td><a href="/wifiDetail?id=<%=wifiInfo.getX_SWIFI_MGR_NO()%>&lat=<%=request.getAttribute("lat")%>&lnt=<%=request.getAttribute("lnt")%>"><%=wifiInfo.getX_SWIFI_MAIN_NM() %></a>  </td>
                <td><%=wifiInfo.getX_SWIFI_ADRES1() %> </td>
                <td><%=wifiInfo.getX_SWIFI_ADRES2() %></td>
                <td><%=wifiInfo.getX_SWIFI_INSTL_FLOOR() %></td>
                <td><%=wifiInfo.getX_SWIFI_INSTL_TY() %></td>
                <td><%=wifiInfo.getX_SWIFI_INSTL_MBY() %></td>
                <td><%=wifiInfo.getX_SWIFI_SVC_SE() %></td>
                <td><%=wifiInfo.getX_SWIFI_CMCWR() %></td>
                <td><%=wifiInfo.getX_SWIFI_CNSTC_YEAR() %></td>
                <td><%=wifiInfo.getX_SWIFI_INOUT_DOOR() %></td>
                <td><%=wifiInfo.getX_SWIFI_REMARS3() %></td>
                <td><%=wifiInfo.getLAT() %></td>
                <td><%=wifiInfo.getLNT() %></td>
                <td><%=wifiInfo.getWORK_DTTM() %></td>
            </tr>
        <% }} else {%>
            <tr>
                <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
            </tr>
        <% } %>
    </table>

</body>
</html>