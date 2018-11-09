package main.java.com.javaservlet.code.dao;

import main.java.com.javaservlet.code.db.DBUtil;
import main.java.com.javaservlet.code.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luying
 * @date 2018/10/31
 */
public class UserDao {
    public void addUser(User user) throws SQLException {
        Connection connection = DBUtil.getConnection();//拿到数据库连接
        String sql = "insert into user (id, adminName, password, age, creatTime, updateTime) value(?,?, ?, ?, current_date, current_date)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2, user.getAdminName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getAge());
        preparedStatement.execute();
    }

    public void delUser(int id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "delete user from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    /**
     * 更改密码，年纪，更新时间
     *
     * @param mUser
     * @throws SQLException
     */
    public void updateUser(User mUser) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "update user set password = ?, age = ?, updateTime = current_date where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, mUser.getPassword());
        preparedStatement.setInt(2, mUser.getAge());
        preparedStatement.setInt(3, mUser.getId());
        preparedStatement.execute();
    }


    public User query(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "select id, adminName, password, age, creatTime, updateTime from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet set = preparedStatement.executeQuery();

        List<User> users = new ArrayList<>();
        if (set.next()) {
            do {
                User user = new User();
                user.setAdminName(set.getString("adminName"));
                user.setAge(set.getInt("age"));
                user.setId(set.getInt("id"));
                user.setPassword(set.getString("password"));
                users.add(user);
                DBUtil.release(connection, stmt, set);

                return user;
            } while (set.next());
        }
        return null;
    }


    public List<User> getUser(List<Map<String, Object>> params) throws SQLException {
        User user = null;
        List<User> users = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select * from user where 1 = 1");
        if (params != null && params.size() > 0){
            for (int i = 0; i < params.size(); i++){
                Map<String, Object> map = params.get(i);
                buffer.append("and" + map.get("name") + " " + map.get("rela") + " "+ map.get("value") + " ");//查询什么？关系是什么？以及值是什么，我们都可以通过参数传进来。
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
        System.out.println(buffer.toString());

        //执行SQL语句
        /*psmt.execute();*///execute()方法是执行更改数据库操作（包括新增、修改、删除）;executeQuery()是执行查询操作
        ResultSet set = preparedStatement.executeQuery();//返回一个结果集

        while (set.next()){
                user = new User();
                user.setId(set.getInt("id"));
                user.setAdminName(set.getString("adminName"));
                user.setPassword(set.getString("passwprd"));
                user.setAge(set.getInt("age"));
                user.setCreatTime(set.getString("creatTime"));
                user.setUpdateTime(set.getString("updateTime"));
                users.add(user);
            }

        DBUtil.release(connection, preparedStatement, set);
        return users;
        }

        public List<User> getAllUser() throws SQLException {
            List<User> users = new ArrayList<>();
            Connection connection = DBUtil.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from user where 1 = 1");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAdminName(resultSet.getString("adminName"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setCreatTime(resultSet.getString("creatTime"));
                user.setUpdateTime(resultSet.getString("updateTime"));
                users.add(user);
            }
            DBUtil.release(connection, stmt, resultSet);
            return users;
        }
    }



