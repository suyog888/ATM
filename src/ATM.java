

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	public class ATM {
		Scanner sc=new Scanner(System.in);
		void insertRecord() throws SQLException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","9175805545");
				PreparedStatement stmt=con.prepareStatement("insert into atm2 values(?,?,?,?,?,?,?)");
				System.out.println("enter Account no");
				int id=sc.nextInt();
				System.out.println("enter UserName");
				String name=sc.next();
				System.out.println("Create ATM Pin");
				int pin=sc.nextInt();
				System.out.println("enter mobile no");
				String mno=sc.next();
				
				System.out.println("enter deposit Amt");
				int depo=sc.nextInt();
				
						
				
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setInt(3,pin);
				stmt.setString(4,mno);
				stmt.setInt(5,depo);
				stmt.setInt(6,0);
				stmt.setInt(7,depo);
				
				int i=stmt.executeUpdate();
				System.out.println(" Your Account created successfully : ");
				con.close();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				
			}
		}
		
		
		
		void checkBalance() throws SQLException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","9175805545");
				System.out.println("enter account no");
				int i=sc.nextInt();
				PreparedStatement stmt=con.prepareStatement("select * from ATM2 WHERE AccountNo ="+i);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Enter PIN");
				int pin=sc.nextInt();
				
				while(rs.next()) {
					if(rs.getInt(3)==pin) {
						System.out.println("Your balance is : "+rs.getInt(5));
					}
					else {
						System.out.println("Wrong pin");
					}
				}
				con.close();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		
		void withdraw ()throws SQLException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","9175805545");
				System.out.println("enter account no");
				int i=sc.nextInt();
				PreparedStatement stmt=con.prepareStatement("select * from ATM2 WHERE AccountNo ="+i);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Enter PIN");
				int pin=sc.nextInt();
			
				
				while(rs.next()) {
					
					if(rs.getInt(3)==pin) {
						System.out.println("Enter withdraw amount");
						int withdr=sc.nextInt();
						int check=rs.getInt(5);
						int check2=check-withdr;
						if(check2>=0) {
							int w;
							int uw;
							int b=(rs.getInt(5));
							int pw=(rs.getInt(6));
							w=b-withdr;  //for current balance update
							uw=pw+withdr; //for withdraw update
						  	System.out.println("you are withdraw : "+withdr+" and your current balance is : "+w);
							PreparedStatement stmt2=con.prepareStatement("UPDATE ATM2 set  CurrentBalance = ? WHERE AccountNo = ? ");
							stmt2.setInt(1, w);
							stmt2.setInt(2, i);
							
							int i0 =stmt2.executeUpdate();
							//System.out.println(i0+"record is updated");
							
							PreparedStatement stmt3=con.prepareStatement("UPDATE ATM2 set  WithdrawAmt = ? WHERE AccountNo = ? ");
							
							stmt3.setInt(1, uw);
							stmt3.setInt(2, i);
							int i1 =stmt3.executeUpdate();
							
						}
						else {
							System.out.println("low balance");
						}
					}
					else {
						System.out.println("Wrong Pin");
					}
					
				
				}
				
				 
				
				con.close();
				
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		
		void deposit() throws SQLException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","9175805545");
				System.out.println("enter account no");
				int i=sc.nextInt();
				PreparedStatement stmt=con.prepareStatement("select * from ATM2 WHERE AccountNo ="+i);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Enter PIN");
				int pin=sc.nextInt();
				
				while(rs.next()) {
					if(rs.getInt(3)==pin) {
						System.out.println("Enter Deposit amount");
						int depo=sc.nextInt();
						int w;
						int uw;
						int b=(rs.getInt(5));//current balance
						int pw=(rs.getInt(7));//deposite amout
						  w=b+depo;  //for current balance update
						 uw=pw+depo; //for withdraw update
						  
						
						System.out.println("you are Deposited : "+depo+" and your current balance is : "+w);
						PreparedStatement stmt2=con.prepareStatement("UPDATE ATM2 set  CurrentBalance = ? WHERE AccountNo = ? ");
						stmt2.setInt(1, w);
						stmt2.setInt(2, i);
						
						int i0 =stmt2.executeUpdate();
						//System.out.println(i0+"record is updated");
						
						PreparedStatement stmt3=con.prepareStatement("UPDATE ATM2 set  DepositAmt = ? WHERE AccountNo = ? ");
						
						stmt3.setInt(1, uw);
						stmt3.setInt(2, i);
						int i1 =stmt3.executeUpdate();
					
				
				}
					else {
						System.out.println("Wrong Pin");
					}
				}
				 
				
				con.close();
				
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
			
		

		public static void main(String[] args) throws SQLException {
			// TODO Auto-generated method stub
			ATM att=new ATM();
		
			
			System.out.println("*****************Menu Card*****************");
			System.out.println("Choose 1 for create new accout           : ");
			System.out.println("Choose 2 for Check Balance               : ");
			System.out.println("Choose 3 for Deposit                     : ");
			System.out.println("Choose 4 for Withdraw                    : ");
			System.out.println("Choose 5 for Exit                        :\n\n ");
			
			
			
			System.out.println("Choose the operation you want to perform : ");
			Scanner sc=new Scanner(System.in);

			int ch =sc.nextInt();
			
			
			
			switch (ch) {
			case 1:
				att.insertRecord();
				
				break;
			case 2:
				att.checkBalance();
				break;
			case 3:
				att.deposit();
				
				break;
			case 4:
				
				att.withdraw();
				break;
				
			case 5:
				
				System.out.println("Thank You ... :-)  ");
				break;
			default:
				System.out.println("Invalid Choice");
			
			
			}
		}

	}

