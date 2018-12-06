package apply.tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Db {
	
	//数据库连接信息
	private String url = "jdbc:mysql://47.106.20.38:3306/Apply18?useSSL=false&characterEncoding=utf-8";
	private String user = "homyit17";
	private String password = "Homyit.17";
	private String db_driver = "com.mysql.jdbc.Driver";
	//数据库链接对象
	private Connection conn;
	//数据库命令执行对象
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * 连接数据库
	 */
	private void connect()
	{
		try
		{
			Class.forName(db_driver);
		}
		catch
		(ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		if(conn == null)
			try
			{
				conn = DriverManager.getConnection(url, user, password);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	
	//数据查找
		public ResultSet query(String sql,Object ...args)
		{
			connect();
			try
			{
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<args.length;i++)
					pstmt.setObject(i+1,args[i]);
				rs = pstmt.executeQuery();
				return rs;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			return null;
		}
		
		//数据增删改  运用可变参数
		public int update(String sql, Object ...args) throws SQLException
		{
			connect();
			//创建预处理对象
			pstmt = conn.prepareStatement(sql);
			//为PreparedStatement对象设置SQL参数
			for (int i = 0 ; i < args.length ; ++ i)
			{
				pstmt.setObject(1 + i, args[i]) ; 
			}
			//执行
			return pstmt.executeUpdate();
		}
		
		/**
		 * 关闭连接
		 */
		public void close()
		{
			try
			{
				pstmt.close();
				stmt.close();
				conn.close();
			} 
			catch(SQLException e)
			{
				// 处理 JDBC 错误
				e.printStackTrace();
			}catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
		}
		
		
}


