package Servlet;

import Dao.UserDao;
import enity.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @title: LoginServlet
 * @Author Xu
 * @Date: 2022/9/24 21:08
 * @Version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        //从请求种读取用户名和密码
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        if(userName == null ||" ".equals(userName) || passWord == null || " ".equals(passWord)){
            String html = "<h3>登录失败! 缺少用户名或者密码!</h3>";
            resp.getWriter().write(html);
            return;

        }
        //从数据库根据用户名查询密码
        UserDao userDao = new UserDao();
        user user = userDao.selectByName(userName);
        if(user == null){
            String html = "<h3>登录失败! 缺少用户名或者密码!</h3>";
            resp.getWriter().write(html);
            return;
        }
        //把从数据库查出来的密码和用户输入的密码进行比较
        if(!user.getPassword().equals(passWord)){
            String html = "<h3>登录失败! 缺少用户名或者密码!</h3>";
            resp.getWriter().write(html);
            return;
        }
        //登录如果成功，就把当前user对象存到HttpSession中
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("user",user);
        //重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}