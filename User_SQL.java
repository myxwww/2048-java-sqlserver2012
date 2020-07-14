package other;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_SQL {
	private static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbURL="jdbc:sqlserver://127.0.0.1:1433;databasename=User_java";
	private static String userName="sa";
	private static String userPwd="myx95598";
	//连接数据库
	private static Connection getCoonection(){
		  try
		  {
		   Class.forName(driverName);
		   Connection conn=DriverManager.getConnection(dbURL,userName,userPwd);
		   return conn;
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.println("数据库驱动连接失败");
		  } 
		  return null;
	  }
	//查询id
	public static int IdQuery(){  
		  try
		  {
		   Connection conn=getCoonection();  
		   Statement stmt = conn.createStatement();
		   String  SQL ="select top 1 id from username order by id desc";
		   ResultSet rs = stmt.executeQuery(SQL); 
		   while(rs.next()) {
		      return  rs.getInt("id");
		      }
		   return 1;
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("数据查询失败");
		   return 1;
		  }
	}
	//数据查询姓名
	public static String executeQuery(){  
		  try
		  {
		   Connection conn=getCoonection();  
		   Statement stmt = conn.createStatement();
		   String  SQL ="select top 1 name from username order by id desc";
		   ResultSet rs = stmt.executeQuery(SQL); 
		   while(rs.next()) {
		      return  rs.getString("name");
		      }
		   return null;
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("数据查询失败");
		   int num=IdQuery()+1;
		   nameUpdate("游客"+num);
		   return executeQuery();
		  }
	}
	//查询成绩
	public static void ScoreQuery() {
		try{
			   Connection conn=getCoonection();  
			   Statement stmt = conn.createStatement();
			   String  SQL ="select top 1 score,id from username order by id desc";
			   ResultSet rs = stmt.executeQuery(SQL); 
			   while(rs.next()) {
					   if(rs.getInt("score")!=0) {
						   int num=rs.getInt("id")+1;
						   nameUpdate("游客"+num);
					   }else {
						   return;
					   }
			   }
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("成绩查询失败");
		  }
	}
	  //成绩更新
	public static void  executeUpdate(int score,String name){  
		  try
		  {
		   Connection conn=getCoonection();
		   System.out.println("连接数据库成功");
		   String  SQL = "update  username set score="+score+" where name='"+name+"'";
		   PreparedStatement pstmt;
		   pstmt = (PreparedStatement) conn.prepareStatement(SQL);
		   int result = pstmt.executeUpdate();
		   if(result>0)
			   System.out.println("数据更新成功");
		   pstmt.close();
		   conn.close();
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.println("数据更新失败");
		  }
		  
	}
	//插入用户名
	public static void  nameUpdate(String name){  
		  try
		  {
		   Connection conn=getCoonection();
		   System.out.println("连接数据库成功");  
		   String SQL = "insert into username(name)values('"+name+"') ";
		   PreparedStatement pstmt;
		   pstmt = (PreparedStatement) conn.prepareStatement(SQL);
		   int result = pstmt.executeUpdate();
		   if(result>0) {
			   System.out.println("用户名插入成功");
		   }
		   pstmt.close();
		   conn.close();
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.println("数据更新失败");
		  }
	}
}
