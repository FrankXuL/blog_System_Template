package Dao;

import enity.Blog;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: BlogDao
 * @Author Xu
 * @Date: 2022/9/20 23:05
 * @Version 1.0
 */
public class BlogDao implements Dao<Blog> {
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    @Override
    public void insert(Blog blog) {
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into blog values(null,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setTimestamp(3, blog.getPostTime());
            statement.setInt(4, blog.getUserId());
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Blog> selectAll() {
        List<Blog> blogs = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog order by postTime desc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                //blog.setContent(resultSet.getString(""));
                String content = resultSet.getString("content");
                if (content.length() > 90) {
                    content = content.substring(0, 90) + "...";
                }
                blog.setContent(content);
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Blog selectById(int blogId) {
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void delete(int blogId) {
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
    }
}