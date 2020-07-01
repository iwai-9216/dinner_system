package controllers.dinners;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dinner;
import utils.DBUtil;

@WebServlet("/dinners/show")
public class DinnersShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Dinner d = em.find(Dinner.class, Integer.parseInt(request.getParameter("dinner_id")));

        em.close();

        request.setAttribute("dinner", d);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/show.jsp");
        rd.forward(request, response);
    }
}
