package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @title: LogoutServlet
 * @Author Xu
 * @Date: 2022/9/25 21:40
 * @Version 1.0
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        // 1. 检查当前用户是否登录
        HttpSession session = req.getSession(false);
        if (session == null) {
            String html = "<h3>您当前未登录</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 不管 session 里有没有 user, 都可以直接去删除
        session.removeAttribute("user");
        // 3. 重定向到登录页面
        resp.sendRedirect("login.html");
    }
}