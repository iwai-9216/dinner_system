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
import models.validators.DinnerValidator;
import utils.DBUtil;

@WebServlet("/dinners/update")
public class DinnersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Dinner d = em.find(Dinner.class, (Integer)(request.getSession().getAttribute("dinner_id")));

            d.setDish(request.getParameter("dish"));

            List<String> errors = DinnerValidator.validate(d);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("dinner", d);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("dinner_id");

                response.sendRedirect(request.getContextPath() + "/dinners/index");
            }
        }
    }
}
