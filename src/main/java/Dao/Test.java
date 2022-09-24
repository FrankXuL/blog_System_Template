package Dao;

import enity.Blog;

import java.sql.Timestamp;
import java.util.List;

/**
 * @title: Test
 * @Author Xu
 * @Date: 2022/9/22 21:33
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        //测试数据库增删改查
        BlogDao blogDao = new BlogDao();
        Blog blog = new Blog();
        blog.setTitle("这是一个作者的文章");
        blog.setContent("一个作者文章的正文");
        blog.setUserId(2);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blogDao.insert(blog);
        Blog blog2 = new Blog();
        blog2.setTitle("这是另一个作者的文章");
        blog2.setContent("另一个作者文章的正文");
        blog2.setUserId(2);
        blog2.setPostTime(new Timestamp(System.currentTimeMillis()));
        blogDao.insert(blog2);
        List<Blog> list = blogDao.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        Blog blog3 = blogDao.selectById(0);
        System.out.println(blog3);
        blogDao.delete(1);
        List<Blog> list1 = blogDao.selectAll();
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
}