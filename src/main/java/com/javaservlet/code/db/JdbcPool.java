package main.java.com.javaservlet.code.db;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 数据库连接池
 *
 * @author luying
 * @date 2018/11/8
 */
public class JdbcPool implements DataSource {
    /**
     * arraylist基于数组，扩容移动的是数据，linkedlist基于链表，移动的是指针，因此linkedlist适合频繁的添加删除
     */
    private static LinkedList<Connection> listConnectins = new LinkedList<>();//适用于频繁的插入操作

    static {
//        在静态代码块中加载db.properties数据库配置文件
        InputStream inputStream = JdbcPool.class.getClassLoader().getResourceAsStream("main/java/com/javaservlet/resources/db.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("sqlUseName");
            String password = prop.getProperty("password");
            int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
            //加载数据驱动
            Class.forName(driver);
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                listConnectins.add(connection);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        if (listConnectins.size() > 0) {
            //获取第一个数据库连接
            final Connection connection = listConnectins.removeFirst();
            System.out.println("listConnection数据库连接池大小是" + listConnectins.size());

            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), connection.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (!method.getName().equals("close")) {
                        return method.invoke(connection, args);
                    } else {
                        //如果调用的是Connection对象的close方法,就把connection还给数据库连接池
                        listConnectins.add(connection);
                        System.out.println(connection + "被换给了listConnections数据库连接池");
                        System.out.println("listConnection数据库连接池大小为" + listConnectins.size());
                        return null;
                    }
                }
            });
        } else {
            throw new RuntimeException("数据库繁忙");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
