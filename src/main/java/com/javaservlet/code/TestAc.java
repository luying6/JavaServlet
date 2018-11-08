package main.java.com.javaservlet.code;

import main.java.com.javaservlet.code.controller.UserAction;
import main.java.com.javaservlet.code.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author luying
 * @date 2018/11/8
 */
public class TestAc {
    public static void main(String[] args) {
        UserAction userAction = new UserAction();
        try {
            List<User> users = userAction.getAllUser();
            for (User user : users) {
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
