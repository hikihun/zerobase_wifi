package Service;

import Model.WifiInfoDAO;
import Model.WifiInfoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/wifiDetail")
public class WifiInfoDetail extends HttpServlet {

    WifiInfoDAO wifiInfoDAO;

    public WifiInfoDetail() {
        wifiInfoDAO = new WifiInfoDAO();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        WifiInfoVO detail = wifiInfoDAO.getWifiInfoDetail(id, lat, lnt);
        request.setAttribute("wifiInfoDetail", detail);

        RequestDispatcher rd = request.getRequestDispatcher("/wifiDetail.jsp");
        rd.forward(request, response);
    }
}
