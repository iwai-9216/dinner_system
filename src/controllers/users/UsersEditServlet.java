package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

@WebServlet("/users/edit")
public class UsersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsersEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = em.find(User.class, Integer.parseInt(request.getParameter("user_id")));

        em.close();

        request.setAttribute("user", u);
        request.setAttribute("_token", (request.getSession()).getId());
        request.getSession().setAttribute("user_id", u.getUser_id());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
        rd.forward(request, response);
    }
}