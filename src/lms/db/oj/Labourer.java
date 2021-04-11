package lms.db.oj;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Labourer {
	int Labour_ID;
	String LFname, LLname, LDOB;
	String phone_num;
	String Street;
	String Gender;
	String City;
	String State;
	int Postal_Code;
	boolean Hiring_Status;
	String Skills;
	int userType = 2;
	Scanner sc = new Scanner(System.in);

	Labourer() {

	}

	public Labourer(int labour_ID, String lFname, String lLname, String gender, String lDOB, String street, String city,
			String state, int postal_Code, boolean hiring_Status, String skills, String phone_num) {
		super();
		Labour_ID = labour_ID;
		LFname = lFname;
		LLname = lLname;
		Gender = gender;
		LDOB = lDOB;
		Street = street;
		City = city;
		State = state;
		Postal_Code = postal_Code;
		Hiring_Status = hiring_Status;
		Skills = skills;
		this.phone_num = phone_num;

	}

	public void LMenu() {
		boolean flag = true;
		do {
			System.out.println("Welcome, "+LFname+" "+LLname+" \n\n1. Personal information \n"
					+ "2. Update details \n3. Find published contract \n" + "4. Logout\n" + "5. Delete account");
			
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				personalInformation();
				break;
			case 2:
				updateDetails();
				break;
			case 3:
				findPublishedContract();
				break;
			case 4:
				System.out.println("You have successfully logged out");
				flag = false;
				break;
			case 5:

				deleteAccount();
				break;
			}

		} while (flag);
	}

	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");
		System.out.println("1. To Confirm" + "\n" + "2. To abort");
		int choice = sc.nextInt();
		DBConnection db = new DBConnection();
		switch (choice) {
		case 1:
			db.startConnection();
			try {
				PreparedStatement ps = db.con.prepareStatement("Delete from Labourer where Labour_ID=?");
				ps.setInt(1, Labour_ID);
				ps.execute();
				System.out.println("Account Deleted");
				System.exit(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);
			break;
		case 2:
			break;
		}

	}

	public void updateDetails() {
		personalInformation();
		System.out.println("Please Choose a option");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Gender");
		System.out.println("4. Street");
		System.out.println("5. City");
		System.out.println("6. State");
		System.out.println("7. Postal Code");
		System.out.println("8. Skills");
		int choice = sc.nextInt();
		DBConnection db = new DBConnection();
		switch (choice) {
		case 1:
			System.out.println("Enter Your First Name");
			sc.nextLine();
			String New_Fname = sc.nextLine();
			New_Fname = New_Fname.trim();
			LFname = New_Fname;
			try {
				db.startConnection();
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set Fname=? where Labour_ID=?");
				ps.setString(1, New_Fname);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 2:
			System.out.println("Enter Your Last Name");
			sc.nextLine();
			String New_Lname = sc.nextLine();
			New_Lname = New_Lname.trim();
			LLname = New_Lname;
			try {
				db.startConnection();
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set Lname=? where Labour_ID=?");
				ps.setString(1, New_Lname);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 3:
			System.out.println("Choose Your Gender:");
			System.out.println("Enter 1. For Male");
			System.out.println("Enter 2. For Female");
			System.out.println("Enter 3. For Others");
			sc.nextLine();
			int Gchoice = sc.nextInt();
			db.startConnection();
			if (Gchoice == 1) {
				try {
					PreparedStatement ps = db.con
							.prepareStatement("Update Labourer set Gender='Male' where Labour_ID=?");
					ps.setInt(1, Labour_ID);
					ps.execute();
					Gender = "Male";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Gchoice == 2) {
				try {
					PreparedStatement ps = db.con
							.prepareStatement("Update Labourer set Gender='Female' where Labour_ID=?");
					ps.setInt(1, Labour_ID);
					ps.execute();
					Gender = "Female";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Gchoice == 3) {
				try {
					PreparedStatement ps = db.con
							.prepareStatement("Update Labourer set Gender='Others' where Labour_ID=?");
					ps.setInt(1, Labour_ID);
					ps.execute();
					Gender = "Others";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			db.closeConnection(db.con);

			break;
		case 4:
			System.out.println("Enter Your Street");
			sc.nextLine();
			String StreetAddress = sc.nextLine();
			StreetAddress = StreetAddress.trim();
			Street = StreetAddress;
			db.startConnection();
			try {
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set Street=? where Labour_ID=?");
				ps.setString(1, StreetAddress);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 5:
			System.out.println("Enter Your City");
			sc.nextLine();
			String New_City = sc.nextLine();
			New_City = New_City.trim();
			City = New_City;
			db.startConnection();
			try {
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set City=? where Labour_ID=?");
				ps.setString(1, New_City);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 6:
			System.out.println("Enter Your State");
			sc.nextLine();
			String New_State = sc.nextLine();
			New_State = New_State.trim();
			State = New_State;
			db.startConnection();
			try {
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set State=? where Labour_ID=?");
				ps.setString(1, New_State);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 7:
			System.out.println("Enter Your Postal Code");
			int New_Postal_Code = sc.nextInt();
			Postal_Code = New_Postal_Code;
			db.startConnection();
			try {
				PreparedStatement ps = db.con.prepareStatement("Update Labourer set Postal_Code=? where Labour_ID=?");
				ps.setInt(1, New_Postal_Code);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		case 8:
			StringBuilder ss = new StringBuilder();
			try {
				db.startConnection();
				PreparedStatement ps = db.con.prepareStatement("Delete from skill_directory where LID=?");
				ps.setInt(1, Labour_ID);
				ps.execute();
				boolean flag = true;
				sc.nextLine();
				do {
					System.out.println("Add a Skill:");
					String s = sc.nextLine();
					s = s.trim();
					ss.append(s);
					ps = db.con.prepareStatement("Insert into skill_directory values (?,?)");
					ps.setInt(1, Labour_ID);
					ps.setString(2, s);
					ps.execute();
					System.out.println("Do you want to add more skills:");
					String c = sc.nextLine();
					if (c.equalsIgnoreCase("yes")) {
						ss.append(',');
						continue;
					} else {
						Skills = ss.toString();
						flag = false;
					}
				} while (flag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(db.con);

			break;
		}

	}

	public void findPublishedContract() {
		DBConnection db = new DBConnection();
		db.startConnection();
		try {
			PreparedStatement ps = db.con.prepareStatement(
					"call lms.Find_Contracts(?)");
			ps.setInt(1, Labour_ID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				System.out.println("No Contracts Found");

			}
			else {
				System.out.println("Contract_ID" + "\t" + "Days" + "\t" + "Wage" + "\t" + "Adress" + "\t" + "City"
						+ "\t" + "Skills");
				do {
					String Contract_ID = rs.getString(1);
					int Days = rs.getInt(3);
					int wage = rs.getInt(4);
					String Address = rs.getString(5);
					String City = rs.getString(6) + "," + rs.getString(8);
					String skills = rs.getString(9);
					System.out.println(
							"-----------------------------------------------------------------------------------------------");
					System.out.println(
							Contract_ID + "\t" + Days + "\t" + wage + "\t" + Address + "\t" + City + "\t" + skills);
				}
				while (rs.next());
				sc.nextLine();
				System.out.println("Do you want to apply for a contract:");
				System.out.println("Enter Yes or No:");
				String choice = sc.nextLine();
				switch (choice) {
				case "yes":
					System.out.println("Enter the Contract_ID to apply for a contract:");
					int cid = sc.nextInt();
					ps = db.con.prepareStatement("Insert into contract_request values(?,?,?)");
					ps.setInt(1, cid);
					ps.setInt(2, Labour_ID);
					ps.setInt(3, 0);
					ps.execute();
					System.out.println("Successfully applied to the contract");
					break;
				case "no":
					// Menu()
					System.out.println("Go To HEll");
					break;
				default:
					System.out.println("You're Gemini and you're indecisive");
				}
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection(db.con);
	}

	public void personalInformation() {
		System.out.println();
		System.out.println("Personal Infromation");
		System.out.println("First Name:\t" + LFname + " " + LLname);
		System.out.println("Gender:\t" + Gender);
		System.out.println("Date of Birth:\t" + LDOB);
		System.out.println("Phone Number:\t" + phone_num);
		System.out.println("Address:\t" + Street + "," + City + "," + State + "," + Postal_Code);
		System.out.println("Skills:\t" + Skills);
		System.out.println();
	}
}
