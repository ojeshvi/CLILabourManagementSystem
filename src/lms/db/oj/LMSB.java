package lms.db.oj;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LMSB {

	static void Register(String Phone_num, int userType) {
		Scanner sc = new Scanner(System.in);
		
		if (userType == 1) {

		}
		else if (userType == 2) {
			String FName,LName,Gender,Street,City,State,Skills,DOB;
			int year,Postal_Code;
			int Month=1;
			int Date=1;
			boolean flag = true;
			do {
				flag = false;
				System.out.println("Enter First Name");
				FName = sc.nextLine();
				FName = FName.trim();
				if (!FName.matches("[a-zA-z]+")) {
					System.out.println(
							"Name Not Supported. Please Enter Name in Correct Format. FirstName Without Spaces");
					flag = true;
				}
				if(FName.length()>25) {
					System.out.println("Name Too Long");
					flag = true;
				}
			} while (flag);
			do {
				flag = false;
				System.out.println("Enter Last Name");
				 LName = sc.nextLine();
				LName = LName.trim();
				if (!LName.matches("[a-zA-z]+")) {
					System.out.println(
							"Name Not Supported. Please Enter Name in Correct Format. Last Name Without Spaces");
					flag = true;
				}
				if(LName.length()>25) {
					System.out.println("Name Too Long");
					flag = true;
				}
			} while (flag);
			do {
				flag = false;
				System.out.println("Enter Gender :");
				Gender = sc.nextLine();
				Gender = Gender.trim();
				if ((Gender.equalsIgnoreCase("Male")) || (Gender.equalsIgnoreCase("Female"))
						|| (Gender.equalsIgnoreCase("Others"))) {
					break;
				} else {
					System.out.println("Invalid Input");
					flag = true;
				}
			} while (flag);
			do {
				flag = false;
				System.out.println("Enter DOB :");
				System.out.println("Enter Year");
				year = sc.nextInt();
				int y = Calendar.getInstance().get(Calendar.YEAR);

				if (y - year <= 18 || y - year >= 60) {
					System.out.println("Bache Budhe Dur Rahe");
					flag = true;
				} else {
					System.out.println("Enter Month");
					 Month = sc.nextInt();
					if (Month > 12 || Month < 0) {
						System.out.println("You don't belong to this planet");
						flag = true;
					} else {
						System.out.println("Enter Date");
						Date = sc.nextInt();
						if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10) {
							if (Date < 0 || Date > 31) {
								System.out.println("You don't belong to this planet");
								flag = true;
							}
						} else if (Month == 2) {
							if (Date < 0 || Date > 28) {
								System.out.println("You don't belong to this planet");
								flag = true;
							}
						} else {
							if (Date < 0 || Date > 30) {
								System.out.println("You don't belong to this planet");
								flag = true;
							}
						}

					}
				}
			} while (flag);
			sc.nextLine();
			do {
				flag = false;
				System.out.println("Enter Street :");
				Street = sc.nextLine();
				Street=Street.trim();
				if (!Street.matches("[0-9\\sa-zA-Z]+")) {
					System.out.println("error aagya");
					flag = true;
				}
				if(Street.length()>100) {
					System.out.println("Address Too Long");
					flag=true;
				}
			} while (flag);
			do {
				flag=false;
				System.out.println("Enter City :");
				City = sc.nextLine();
				City=City.trim();
				if (!City.matches("[a-zA-Z]+")) {
					flag = true;
				}
				if(City.length()>25) {
					System.out.println("City Name Too Long");
					flag=true;
				}
			
			}while(flag);
			do {
				flag=false;
				System.out.println("Enter State :");
				State = sc.nextLine();
				State=State.trim();
				if (!State.matches("[a-zA-Z]+")) {
					flag = true;
				}
				if(State.length()>25) {
					System.out.println("State Name Too Long");
					flag=true;
				}
			
			}while(flag);
			do {
				flag=false;
			System.out.println("Enter Postal Code :");
			Postal_Code = sc.nextInt();
			if(Postal_Code<100000||Postal_Code>999999) {
				System.out.println("Invalid Postal Code:");
				flag=true;
			}
			}
			while(flag);
			do {
			flag=false;
			sc.nextLine();
			System.out.println("Enter Skills Separated by Space");
			Skills = sc.nextLine();
			if(!Skills.matches("[a-zA-z]+(\\s[a-zA-z]+)*")) {
				System.out.println("Invalid Input");
				flag=true;
			}
			}
			while(flag);
			DBConnection db=new DBConnection();
			db.startConnection();
			try {
			PreparedStatement max = db.con.prepareStatement("SELECT max(Labour_ID) FROM lms.labourer");
			
			ResultSet r=max.executeQuery();
			r.next();
			int maxx=r.getInt(1);
			maxx++;
			PreparedStatement ps=db.con.prepareStatement("Insert into Labourer values(?,?,?,?,?,?,?,?,?,?)");
			DOB=String.valueOf(year);
			DOB+="-";
			DOB+=String.valueOf(Month);
			DOB+="-";
			DOB+=String.valueOf(Date);
			ps.setInt(1, maxx);
			ps.setString(2, FName);
			ps.setString(3, LName);
			ps.setString(4, Gender);
			ps.setString(5, DOB);
			ps.setString(6, Street);
			ps.setString(7, City);
			ps.setString(8, State);
			ps.setInt(9, Postal_Code);
			ps.setInt(10, 0);
			ps.execute();
			ps=db.con.prepareStatement("Insert into lphone_directory values(?,?)");
			ps.setInt(1, maxx);
			ps.setString(2, Phone_num);
			ps.execute();
			String[] skill=Skills.split(" ");
			for(int i=0;i<skill.length;i++) {
				ps=db.con.prepareStatement("Insert into skill_directory values(?,?)");
				ps.setInt(1, maxx);
				ps.setString(2, skill[i]);
				ps.execute();
			}
			System.out.println("Registered Succesfully. Please Login Again");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Invalid UserType");
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Labour Employement System");
		int choice;
		do {
			System.out.println("\t1. Register as a new user\n\t2. Login\n\t3. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Registration");
				System.out.println("Select user type to Register as:\n\t1. Contractor\n\t2. Labourer");
				int Rchoice=sc.nextInt();
				switch(Rchoice) {
				case 1: 
					System.out.println("Contractor Registration");
					break;
				case 2:
					sc.nextLine();
					System.out.println("Enter Your Phone Number");
					String Phone_num=sc.nextLine();
					DBConnection db=new DBConnection();
					db.startConnection();
					try {
						PreparedStatement ps=db.con.prepareStatement("call lms.CheckRegistration(?, @res)");
						ps.setString(1, Phone_num);
						ps.execute();
						ps=db.con.prepareStatement("select @res");
						ResultSet rs=ps.executeQuery();
						rs.next();
						if(rs.getInt(1)==0) {
							Register(Phone_num, Rchoice);
						}
						else {
							System.out.println("You are a Registered User. Please Login IN");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				default:
					System.out.println("Invalid Input");
				}
				break;
			case 2:
				System.out.println("Select user type to login as:\n\t1. Contractor\n\t2. Labourer\n\t3. Admin");
				int LChoice = sc.nextInt();
				switch (LChoice) {
				case 1:
					System.out.println("ShIT");
					break;
				case 2:
					DBConnection db = new DBConnection();
					db.startConnection();
					System.out.println("Enter the Phone number of Labourer");
					sc.nextLine();
					Labourer l = new Labourer();
					String Phone_num = sc.nextLine();
					PreparedStatement ps;
					try {
						ps = db.con.prepareStatement(
								"select l.*, group_concat(distinct(skills)),group_concat(distinct(phone_no)) from labourer l join skill_directory sd on l.labour_id = sd.lid join lphone_directory pd on pd.lid=sd.lid where phone_no=?");
						ps.setString(1, Phone_num);
						ResultSet rs = ps.executeQuery();
						rs.next();
						if (rs.getInt(1) == 0) {
							System.out.println("Not a Registered User");
							System.exit(0);
						} else {
							l = new Labourer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
									rs.getBoolean(10), rs.getString(11), rs.getString(12));
						}
						db.closeConnection(db.con);
						l.LMenu();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					labourer.register(2);
					System.out.println("Entered");
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
				break;
			case 3:
				System.exit(0);
				break;
			}
		} while (choice != 3);
	}

}
