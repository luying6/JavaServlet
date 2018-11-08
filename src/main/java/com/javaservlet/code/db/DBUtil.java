package main.java.com.javaservlet.code.db;

import java.sql.*;

/**
 * @author luying
 * @date 2018/10/31
 */
public class DBUtil {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/person";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "lu123456";
    private static Connection connection = null;

    //数据库连接池
    private static JdbcPool jdbcPool = new JdbcPool();

//
//    //加载mysql的驱动类
//    static {
//        try {
//            Class.forName(DRIVER_NAME);
//            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @return Connection数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return jdbcPool.getConnection();
//        return connection;
    }


    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();//关闭存储查询结果的ResultSet对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }

        if (statement != null) {
            try {
                statement.close();//关闭负责执行SQL命令的Statement对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();//关闭Connection数据库连接对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
