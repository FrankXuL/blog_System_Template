package Servlet;

import enity.user;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: BlogListServlet
 * @Author Xu
 * @Date: 2022/9/24 22:01
 * @Version 1.0
 */
@WebServlet("/blog_list.html")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        // 针对登录状态的监测, 未登录时则重定向到登录页面
        user user = UserUtil.checkLoginStatus(req);
        if(user == null){
            System.out.println("当前用户未登录");
            resp.sendRedirect("login.html");
            return;
        }
        // 1. 从数据库中拿到所有的博客列表

        // 2. 通过模板引擎, 渲染页面
    }
}