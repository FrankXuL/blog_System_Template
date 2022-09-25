package Servlet;

import Dao.BlogDao;
import enity.Blog;
import enity.user;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: BlogDeleteServlet
 * @Author Xu
 * @Date: 2022/9/25 21:28
 * @Version 1.0
 */
@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. 先获取到用户的登录状态
        user user = UserUtil.checkLoginStatus(req);
        if(user == null){
            String html = "<h3>您尚未登录, 不能删除</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 获取到当前请求中要删除的博客的 id
        String blogId = req.getParameter("blogId");
        if(blogId == null || "".equals(blogId)){
            String html = "<h3>blogId 参数缺失!</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 校验一下当前执行删除的人是否是作者
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectById(Integer.parseInt(blogId));
        if(blog.getUserId() != user.getUserId()){
            String html = "<h3>您不是博客作者, 不能删除!</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 4. 进行删除操作, 从数据库中删除博客
        blogDao.delete(Integer.parseInt(blogId));
        // 5. 重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}