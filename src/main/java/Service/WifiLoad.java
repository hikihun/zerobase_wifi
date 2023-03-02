package Service;

import Model.WifiInfoDAO;
import Model.WifiInfoVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/load-wifi")
public class WifiLoad extends HttpServlet {
    WifiInfoDAO wifiInfoDAO;

    public WifiLoad() {
        wifiInfoDAO = new WifiInfoDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String jsonString = WifiApi.getJsonString();
        List<WifiInfoVO> list = WifiApi.jsonToString(jsonString);
        int addNum = list.size();
        request.setAttribute("addNum", addNum);
//        System.out.println(list);
        for (WifiInfoVO wifiInfo : list) {
            wifiInfoDAO.addWifiInfo(wifiInfo);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/load-wifi.jsp");
        rd.forward(request, response);

//        if (action.equals("/load-wifi")) {
//            String jsonString = WifiApi.getJsonString();
//            List<WifiInfoVO> list = WifiApi.jsonToString(jsonString);
//            int addNum = list.size();
//            request.setAttribute("addNum", addNum);
////        System.out.println(list);
//            for (WifiInfoVO wifiInfo : list) {
//                wifiInfoDAO.addWifiInfo(wifiInfo);
//            }
//
//            RequestDispatcher rd = request.getRequestDispatcher("/load-wifi.jsp");
//            rd.forward(request, response);
//        } else if (action.equals("wifiInfoList")) {
//            String lat = request.getParameter("lat");
//            String lnt = request.getParameter("lnt");
//            List<WifiInfoVO> wifiInfoList = wifiInfoDAO.getWifiInfoList(lat, lnt);
//            request.setAttribute("wifiInfoList", wifiInfoList);
//            System.out.println(wifiInfoList);
//            RequestDispatcher rd = request.getRequestDispatcher("/");
//            rd.forward(request, response);
//        }

    }
}
