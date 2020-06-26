package controllers.dinners;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dinner;

@WebServlet("/dinners/new")
public class DinnersNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnersNewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Dinner d = new Dinner();
        request.setAttribute("dinner", d);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dinners/new.jsp");
        rd.forward(request, response);
    }
}
