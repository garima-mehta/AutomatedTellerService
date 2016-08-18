 package txttohtml;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.batch.Main;

import oracle.jdbc.driver.OracleDriver;

public class Parser {
	int user_id = 0;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "system";
	String password = "tiger";
	String query = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public String parsing(String msg) {
		
		Driver d = new OracleDriver();
		try {
			DriverManager.registerDriver(d);
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String text[] = msg.split(" ");
		for(int i = 0; i<text.length; i++) {
			if(text[i].equalsIgnoreCase("promotions")) {
				 
				query = "select * from Best_Offers";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("balance")) {
				
				query = "select Current_Balance from Prepaid where User_id=?";
				
				try {
					ps.setInt(1, user_id);
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					int cub = 0;
					if(rs.next()) {
						 cub = rs.getInt(1);
					}
					String cuba = Integer.toString(cub);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(text[i].equalsIgnoreCase("prepaid")&&text[i+1].equalsIgnoreCase("plans"))  {
				query = "Select Prepaid from Offers where Amount < (Select Current_Balance from Prepaid where User_Id=?)";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					if(rs.next()) {
						return rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("postpaid")&&text[i+1].equalsIgnoreCase("plans"))  {
				query = "Select Postpaid from Offers where Amount < (Select Bill_amt from Postpaid where User_Id=?)";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					if(rs.next()) {
						return rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("brodband")&&text[i+1].equalsIgnoreCase("plans"))  {
				query = "Select Broadband from Offers where Amount < (Select Bill_amt from Broadband where User_Id=?)";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					if(rs.next()) {
						return rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("prepaid")&&text[i+1].equalsIgnoreCase("balance"))  {
				query = "Select Current_Balance from Prepaid where User_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int in;
					if(rs.next()) {
						in = rs.getInt(1);
						return (Integer.toString(in));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("postpaid")&&text[i+1].equalsIgnoreCase("balance"))  {
				query = "Select Bill_amt from Postpaid where User_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int in;
					if(rs.next()) {
						in = rs.getInt(1);
						return Integer.toString(in);
					}
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
			if(text[i].equalsIgnoreCase("broadband")&&text[i+1].equalsIgnoreCase("balance"))  {
				query = "Select Bill_amt from Brodband where User_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int in;
					if(rs.next()) {
						in = rs.getInt(1);
						return Integer.toString(in);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
			if(text[i].equalsIgnoreCase("plans")) {
				query = "Select Prepaid, Postpaid, Broadband from Offers";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					String res ="";
					while(rs.next()) {
						res +=rs.getString(1);
						res += "/n";
					}
					return res;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("offers")) {
				query = "Select Prepaid,Postpaid, Broadband from Best_Offers where User_Id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					String res ="";
					while(rs.next()) {
						res +=rs.getString(1);
						res += "/n";
					}
					return res;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("transactions")) {
				query = "Select Transaction_details from Transaction where User_Id=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					String res;
					if(rs.next()) {
						res = rs.getString(1);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return "The server cannot handlle the query, Kindly contact the nearest Verizon Office!!";
	}
	public static void main(String[] args) {
		String msg1 = "what are promotions available?";
		Parser p = new Parser();
		p.parsing(msg1);
		
	}
}
