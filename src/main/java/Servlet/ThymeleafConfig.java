package Servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @title: configServlet
 * @Author Xu
 * @Date: 2022/9/24 21:04
 * @Version 1.0
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        // 1. 创建 TemplateEngine 实例
        TemplateEngine engine = new TemplateEngine();
        // 2. 创建 resolver 对象
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 3. 把 resolver 对象和 engine 关联起来
        engine.setTemplateResolver(resolver);
        // 4. 把创建好的 engine 对象给放到 ServletContext 中
        context.setAttribute("engine", engine);
        System.out.println("初始化 TemplateEngine 完毕!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}