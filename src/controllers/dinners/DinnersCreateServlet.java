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
import models.User;
import models.validators.DinnerValidator;
import utils.DBUtil;

@WebServlet("/dinners/create")
public class DinnersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Dinner d = new Dinner();

            d.setDish(request.getParameter("dish"));

            d.setUser((User)request.getSession().getAttribute("login_user"));


            List<String> errors = DinnerValidator.validate(d);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("dinner", d);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(d);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/dinners/index");
            }
        }
    }
}
