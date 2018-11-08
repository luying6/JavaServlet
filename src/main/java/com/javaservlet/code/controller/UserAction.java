package main.java.com.javaservlet.code.controller;

import main.java.com.javaservlet.code.dao.UserDao;
import main.java.com.javaservlet.code.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author luying
 * @date 2018/11/2
 */
public class UserAction {

    /**
     * 插入用户
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.addUser(user);
    }

    /**
     * 更新指定user
     * @param user
     * @throws SQLException
     */
    public void updateUser(User user) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.updateUser(user);
    }

    /**
     * 删除指定user
     * @param id
     * @throws SQLException
     */
    public void delUser(Integer id) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.delUser(id);
    }

    /**
     * 所有User
     * @return
     * @throws SQLException
     */
    public List<User> getAllUser() throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getAllUser();
    }

    /**
     * 条件查询
     * @param param
     * @return
     * @throws SQLException
     */
    public List<User> queryUser(List<Map<String, Object>> param) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUser(param);
    }
}
