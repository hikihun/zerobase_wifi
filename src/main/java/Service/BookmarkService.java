package Service;

import Model.BookmarkGroupDAO;
import Model.BookmarkGroupVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmark/*")
public class BookmarkService extends HttpServlet {
    BookmarkGroupDAO bookmarkDAO;

    public BookmarkService() {
        bookmarkDAO = new BookmarkGroupDAO();
    }
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getPathInfo();
        System.out.println("action: " + action);

        if (action.equals(null) || action.equals("/group")) {
            List<BookmarkGroupVO> bookmarkGroupList = bookmarkDAO.getBookmarkGroupList();
            request.setAttribute("bookmarkGroupList", bookmarkGroupList);

            RequestDispatcher rd = request.getRequestDispatcher("/bookmark-group.jsp");
            rd.forward(request, response);
        } else if (action.equals("/add")) {
            RequestDispatcher rd = request.getRequestDispatcher("/bookmark-group-add.jsp");
            rd.forward(request, response);
        } else if (action.equals("/addBookmark")) {
            String name = request.getParameter("name");
            int order = Integer.parseInt(request.getParameter("order"));
            bookmarkDAO.addBookmark(name, order);
            response.sendRedirect("/bookmark/group");
        } else if (action.equals("/edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);
            RequestDispatcher rd = request.getRequestDispatcher("/bookmark-group-edit.jsp");
            rd.forward(request, response);
        } else if (action.equals("/editBookmark")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int order = Integer.parseInt(request.getParameter("order"));
            bookmarkDAO.updateBookmark(name, order, id);
            response.sendRedirect("/bookmark/group");
        } else if (action.equals("/delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bookmarkDAO.deleteBookmark(id);
            response.sendRedirect("/bookmark/group");
        }


    }

}
