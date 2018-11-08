package db;

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


    //加载mysql的驱动类
    static {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
//                String sql = "select adminName from user";
//                PreparedStatement prst = connection.prepareStatement(sql);
//                ResultSet rs = prst.executeQuery();
//                while (rs.next()) {
//                    System.out.println("用户名:" + rs.getString("adminName"));
//                }
//                rs.close();
//                prst.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        return connection;
    }


    public static void main(String[] args) throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet set = statement.executeQuery()
    }

}
