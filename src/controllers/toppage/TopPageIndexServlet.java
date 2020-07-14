package controllers.toppage;

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

@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    public TopPageIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        List<Dinner> all_dish_url = em.createNamedQuery("getAllDinnersUrl", Dinner.class)
                                        .setFirstResult(0)
                                        .setMaxResults(100)
                                        .getResultList();

        if(all_dish_url.size() == 0){
            Dinner dishId = null;
            request.setAttribute("dishId", dishId);
        }else{
            Dinner dishId = all_dish_url.get((int)(Math.random() * all_dish_url.size()));
            request.setAttribute("dishId", dishId.getId());
            request.setAttribute("dishName", dishId.getDish());
        }


        em.close();

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }
}
