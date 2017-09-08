package com.hyb.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;



/*
 * JDBC工具类
 */
public class JDBCUtils {
	
	
	/*
	 * 获取数据库连接的方法：通过读取配置文件从服务器获取一个连接
	 */
	static public Connection getConnection() throws Exception{
		
		Properties properties = new Properties();
		InputStream in = 
				JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		String driver = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		
		Class.forName(driver);
		Connection connection = 
				DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}
	
	
	/*
	 * 关闭statement和connection
	 */
	static public void release(Statement statement,Connection conn){
				
		if(statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void release(ResultSet rs,Statement statement,Connection conn){
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		if(statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void release(ResultSet rs,PreparedStatement PreparedStatement,Connection conn){	
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		if(PreparedStatement != null) {
			try {
				PreparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 执行SQL语句，INSERT,DELETE,UPDATE
	 */
	static public void update(String sql){
		Connection conn = null;
		Statement statement = null;		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(statement, conn);		
		}
		
	}
	/*
	 * 使用preparedstatement 语句
	 */
	public static void update(String sql,Object ... args){
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = JDBCUtils.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			
			for(int i = 0;i < args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
					
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(null, preparedStatement, conn);
		}
	}
	
	
	
	/*
	 * 读取任何对象,查询通用
	 */
	public <T> T get(Class<T> clazz,String sql,Object ... args){
		T entity = null;
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			//1.得到ResultSet对象
			conn = JDBCUtils.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}		
			rs = preparedStatement.executeQuery();
					
			//2.得到ResultSetMetaData对象
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//3.创建一个Map<String,Object>对象，键：SQL查询的列的别名 值：列的值
			Map<String,Object> values = new HashMap<>();
			
			//4.处理结果集，利用ResultSetMetaData对象 填充3对应的Map对象。
			if(rs.next()){
				for(int i =0;i < rsmd.getColumnCount();i++){
					String columnLabel =rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(i + 1);
					
					values.put(columnLabel, columnValue);
				}
			}
			
			//5.若Map不为空集利用反射创建clazz对象
			if(values.size() > 0){
				entity = clazz.newInstance();
				
				//6.遍历Map对象，利用反射Class为对象赋值
				for(Map.Entry<String, Object> entry : values.entrySet()){
					String fieldName = entry.getKey();
					Object value = entry.getValue();
									
					//ReflectionUtils.setFieldValue(entity, fieldName, value);
					BeanUtils.setProperty(entity, fieldName, value);
				}
			}
			

		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, preparedStatement, conn);
		}
		
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
