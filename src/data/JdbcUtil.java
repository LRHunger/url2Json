package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {
	/*java链接数据库，通过这个类，我们可以对数据库进行增删改查等操作*/
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8";
	private static String username = "root";
	private static String password = "root";
	static {
		try {
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("无法加载数据库的驱动");
		}
	}
	/*进行java和数据库的链接*/
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*通过sql语句对数据进行操作*/
	public static int executeUpdate(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConn();
			stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeResource(stmt, conn);
		}
	}
	/*关闭对应的流，防止内存溢出*/
	public static void closeResource(Statement stmt, Connection conn) {
		closeResource(null, stmt, conn);
	}
	public static void closeResource(ResultSet rs, Statement stmt,
			Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
