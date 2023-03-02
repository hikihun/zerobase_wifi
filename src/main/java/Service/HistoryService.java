package Service;

import Model.HistoryDAO;
import Model.HistoryVO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history/*")
public class HistoryService extends HttpServlet {
    HistoryDAO historyDAO;

    public HistoryService() {
        historyDAO = new HistoryDAO();
    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getPathInfo();
        System.out.println("action: " + action);

        if (action.equals(null) || action.equals("/list")) {
            List<HistoryVO> historyList = historyDAO.listHistory();
            request.setAttribute("historyList", historyList);

            RequestDispatcher rd = request.getRequestDispatcher("/history.jsp");
            rd.forward(request, response);
        } else if (action.equals("/delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            historyDAO.deleteHistory(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/history/list");
            dispatcher.forward(request, response);
        }


    }
}