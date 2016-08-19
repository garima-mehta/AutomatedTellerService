 package txttohtml;

import java.sql.Connection;
import java.util.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import oracle.jdbc.driver.OracleDriver;

public class Parser {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "system";
	String password = "tiger";
	String query = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String parsing(String msg, int user_id) {
		
		try {
			Driver d = new OracleDriver();
			DriverManager.registerDriver(d);
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String text[] = msg.split(" ");
		for(int i = 0; i<text.length; i++) {
			if(text[i].equalsIgnoreCase("Hi")|| text[i].equalsIgnoreCase("Hello")) {
				return "Hello, I am Verii!!";
			}
			if(text[i].equalsIgnoreCase("promotions")) {
				 
				query = "select * from Best_Offers";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					String res = "The offers available to you are: \n";
					while(rs.next()) {
						res += rs.getString(2);
						res += rs.getString(3);
						res += rs.getString(4);
						res += "\n";
					}
					return res;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("balance")) {
				
				query = "select Current_Balance from Prepaid where User_id=?";
				
				try {
					
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int cub = 0;
					if(rs.next()) {
						 cub = rs.getInt(1);
					}
					String cuba = "Your current balance is: \n"+Integer.toString(cub);
					return cuba;
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
						return "Your prepaid offers are: \n"+rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("postpaid")&&text[i+1].equalsIgnoreCase("plans"))  {
				query = "Select amount,Postpaid from Offers where Amount < (Select Bill_amt from Postpaid where User_Id=?)";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					if(rs.next()) {
						return "Your postpaid offers are: \n"+rs.getInt(1)+" "+rs.getString(2);
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
						return "Your broadband offers are: \n"+rs.getString(1);
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
						return ("Your current prepaid balance is: \n"+Integer.toString(in));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(text[i].equalsIgnoreCase("postpaid")&&text[i+1].equalsIgnoreCase("bill"))  {
				query = "Select Bill_amt from Postpaid where User_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int in;
					if(rs.next()) {
						in = rs.getInt(1);
						return "Your Postpaid bill amount  is: "+Integer.toString(in);
					}
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
			if(text[i].equalsIgnoreCase("broadband")&&text[i+1].equalsIgnoreCase("bill"))  {
				query = "Select Bill_amt from Brodband where User_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					int in;
					if(rs.next()) {
						in = rs.getInt(1);
						return "Your broadband bill amount is: \n"+Integer.toString(in);
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
					String res ="The plans the we offer are: ";
			
					while(rs.next()) {
						res +=rs.getString(1);
						res += rs.getString(2);
						res += rs.getString(3); 
						
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
					String res ="Best offers for you are: \n";
					while(rs.next()) {
						res +=rs.getString(1);
						res += rs.getString(2);
						res += rs.getString(3);
						res += "\n";
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
					String res = "Your transactions are: ";
					while(rs.next()) {
						res += rs.getString(1);
					
					}
					return res;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(text[i].equalsIgnoreCase("due")) {
				query = "select duedate from postpaid where user_id = ?";
				SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
				Date d = new Date();
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					String res = "Your postpaid due date is: ";
					if(rs.next()) {
						d = rs.getDate(1);
						res += sdfr.format(d);
						return res;
						
					
					}
					String query2;
					query2 = "select duedate from broadband where user_id =?";
					ps = con.prepareStatement(query2);
					rs = ps.executeQuery();
					String res1 = "Your broadband due date is: ";
					if(rs.next()) {
						d = rs.getDate(1);
						res += sdfr.format(d);
						return res;
					}
					
					return res;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		return "The server cannot handlle the query, Kindly contact the nearest Verizon Office!!";
	}
	
}
