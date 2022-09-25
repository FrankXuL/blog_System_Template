package Servlet;

import Dao.BlogDao;
import enity.Blog;
import enity.user;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        if (user == null) {
            System.out.println("当前用户未登录");
            resp.sendRedirect("login.html");
            return;
        }
        // 1. 从数据库中拿到所有的博客列表
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.selectAll();
        // 2. 通过模板引擎, 渲染页面
        ServletContext context = this.getServletContext();
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        WebContext webContext = new WebContext(req, resp, context);
        webContext.setVariable("blogs", blogs);
        webContext.setVariable("user", user);
        String html = engine.process("blog_list", webContext);
        resp.getWriter().write(html);
    }
}