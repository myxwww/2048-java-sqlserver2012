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
	//�������ݿ�
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
		   System.out.println("���ݿ���������ʧ��");
		  } 
		  return null;
	  }
	//��ѯid
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
		   System.out.print("���ݲ�ѯʧ��");
		   return 1;
		  }
	}
	//���ݲ�ѯ����
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
		   System.out.print("���ݲ�ѯʧ��");
		   int num=IdQuery()+1;
		   nameUpdate("�ο�"+num);
		   return executeQuery();
		  }
	}
	//��ѯ�ɼ�
	public static void ScoreQuery() {
		try{
			   Connection conn=getCoonection();  
			   Statement stmt = conn.createStatement();
			   String  SQL ="select top 1 score,id from username order by id desc";
			   ResultSet rs = stmt.executeQuery(SQL); 
			   while(rs.next()) {
					   if(rs.getInt("score")!=0) {
						   int num=rs.getInt("id")+1;
						   nameUpdate("�ο�"+num);
					   }else {
						   return;
					   }
			   }
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("�ɼ���ѯʧ��");
		  }
	}
	  //�ɼ�����
	public static void  executeUpdate(int score,String name){  
		  try
		  {
		   Connection conn=getCoonection();
		   System.out.println("�������ݿ�ɹ�");
		   String  SQL = "update  username set score="+score+" where name='"+name+"'";
		   PreparedStatement pstmt;
		   pstmt = (PreparedStatement) conn.prepareStatement(SQL);
		   int result = pstmt.executeUpdate();
		   if(result>0)
			   System.out.println("���ݸ��³ɹ�");
		   pstmt.close();
		   conn.close();
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.println("���ݸ���ʧ��");
		  }
		  
	}
	//�����û���
	public static void  nameUpdate(String name){  
		  try
		  {
		   Connection conn=getCoonection();
		   System.out.println("�������ݿ�ɹ�");  
		   String SQL = "insert into username(name)values('"+name+"') ";
		   PreparedStatement pstmt;
		   pstmt = (PreparedStatement) conn.prepareStatement(SQL);
		   int result = pstmt.executeUpdate();
		   if(result>0) {
			   System.out.println("�û�������ɹ�");
		   }
		   pstmt.close();
		   conn.close();
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.println("���ݸ���ʧ��");
		  }
	}
}
