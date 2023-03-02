package Service;

import Model.HistoryDAO;
import Model.WifiInfoDAO;
import Model.WifiInfoVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/wifiInfoList")
public class WifiInfoList extends HttpServlet {

    WifiInfoDAO wifiInfoDAO;
    HistoryDAO historyDAO;

    public WifiInfoList() {
        wifiInfoDAO = new WifiInfoDAO();
        historyDAO = new HistoryDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        List<WifiInfoVO> wifiInfoList = wifiInfoDAO.getWifiInfoList(lat, lnt);
        request.setAttribute("wifiInfoList", wifiInfoList);
        request.setAttribute("lat", lat);
        request.setAttribute("lnt", lnt);

        System.out.println(wifiInfoList);

        historyDAO.addHistory(lat, lnt);

        RequestDispatcher rd = request.getRequestDispatcher("/");
        rd.forward(request, response);
    }
}
