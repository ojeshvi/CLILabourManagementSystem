package lms.db.oj;

class Admin {
String FName;
String LName;
String email;
String password;
public Admin(String fName, String lName, String email, String password) {
	super();
	FName = fName;
	LName = lName;
	this.email = email;
	this.password = password;
}

void delete(){
	System.out.println("1. Labourer \n2.Contractor");
}
	
}
