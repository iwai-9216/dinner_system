package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")){
        if(!servlet_path.matches("/users/new")){
        if(!servlet_path.matches("/users/create")){

        HttpSession session = ((HttpServletRequest)request).getSession();

            //セッションスコープに保存されたユーザー情報を取得
            User u = (User)session.getAttribute("login_user");

            if(!servlet_path.equals("/login")){
                //ログアウトしている状態であれば
                //ログイン画面にリダイレクト
                if(u == null){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }

                //ユーザー管理の機能は管理者のみが閲覧できるようにする
                if(servlet_path.matches("/users.*") && u.getAdmin_flag() == 0){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }

            }else{     //ログイン画面について
                //ログインしているのにログイン画面を表示させようとした場合は
                //システムのトップページにリダイレクト
                if(u != null){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }
            }
        }}}
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }
}
