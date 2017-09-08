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
 * JDBC������
 */
public class JDBCUtils {
	
	
	/*
	 * ��ȡ���ݿ����ӵķ�����ͨ����ȡ�����ļ��ӷ�������ȡһ������
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
	 * �ر�statement��connection
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
	 * ִ��SQL��䣬INSERT,DELETE,UPDATE
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
	 * ʹ��preparedstatement ���
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
	 * ��ȡ�κζ���,��ѯͨ��
	 */
	public <T> T get(Class<T> clazz,String sql,Object ... args){
		T entity = null;
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			//1.�õ�ResultSet����
			conn = JDBCUtils.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}		
			rs = preparedStatement.executeQuery();
					
			//2.�õ�ResultSetMetaData����
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//3.����һ��Map<String,Object>���󣬼���SQL��ѯ���еı��� ֵ���е�ֵ
			Map<String,Object> values = new HashMap<>();
			
			//4.��������������ResultSetMetaData���� ���3��Ӧ��Map����
			if(rs.next()){
				for(int i =0;i < rsmd.getColumnCount();i++){
					String columnLabel =rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(i + 1);
					
					values.put(columnLabel, columnValue);
				}
			}
			
			//5.��Map��Ϊ�ռ����÷��䴴��clazz����
			if(values.size() > 0){
				entity = clazz.newInstance();
				
				//6.����Map�������÷���ClassΪ����ֵ
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
