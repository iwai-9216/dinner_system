package controllers.dinners;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dinner;
import utils.DBUtil;

@WebServlet("/dinners/index")
public class DinnersIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Dinner> dinners = em.createNamedQuery("getAllDinners", Dinner.class)
                                    .setFirstResult(12 * (page - 1))
                                    .setMaxResults(12)
                                    .getResultList();

        long dinners_count = (long)em.createNamedQuery("getDinnersCount", Long.class)
                                        .getSingleResult();
        em.close();

        request.setAttribute("dinners", dinners);
        request.setAttribute("dinners_count", dinners_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/index.jsp");
        rd.forward(request, response);
    }
}