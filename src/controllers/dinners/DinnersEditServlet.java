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
import models.User;
import utils.DBUtil;

@WebServlet("/dinners/edit")
public class DinnersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersEditServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil.createEntityManager();

            Dinner d = em.find(Dinner.class, Integer.parseInt(request.getParameter("dinner_id")));

            em.close();

            User login_user = (User)request.getSession().getAttribute("login_user");
            if(d != null && login_user.getId() == d.getUser().getId()) {
                request.setAttribute("dinner", d);
                request.setAttribute("_token", request.getSession().getId());
                request.getSession().setAttribute("dinner_id", d.getDinner_id());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/edit.jsp");
            rd.forward(request, response);
    }
}
