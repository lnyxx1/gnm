import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private String USERNAME = "root";
	private String PASSWORD = "";
	private String DATABASE = "g&m_db";
	
	private String HOST = "localhost:3306";
	private String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	private Statement st;
	private static Connect conn;
	
	public ResultSet res;
	public ResultSetMetaData resM;

	public static Connect getInstance() {
		if (conn == null) return conn = new Connect();
		return conn;
	}
	
	// show data
	public ResultSet execQuery(String q) {
		try {
			res = st.executeQuery(q);
			resM = res.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	// add data
	public void execUpdate(String q) {
		try {
			st.executeUpdate(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public PreparedStatement preparedStatement(String query) {
//		try {
//			return con.prepareStatement(query);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	private Connect() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");		forName digunakan untuk return sebuah object dari jdbc driver tanpa membuat objeknya
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
