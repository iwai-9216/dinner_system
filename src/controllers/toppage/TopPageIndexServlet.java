package controllers.toppage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    public TopPageIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        EntityManager em = DBUtil.createEntityManager();

        User login_user = (User)request.getSession().getAttribute("login_user");

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Dinner> dinners = em.createNamedQuery("getMyAllDinners", Dinner.class)
                                   .setParameter("user", login_user)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                                   .getResultList();

        long dinners_count = (long)em.createNamedQuery("getDinnersCount", Long.class)
                                        .setParameter("user", login_user)
                                        .getSingleResult();

        em.close();

        request.setAttribute("dinners", dinners);
        request.setAttribute("dinners_count", dinners_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
*/
        String[] dinner = {"ハンバーグ", "目玉焼き", "焼肉", "すき焼き"};
        String value = dinner[(int)(Math.random() * dinner.length)];

        request.setAttribute("value", value);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}
