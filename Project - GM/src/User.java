import java.sql.Date;

public class User {
	
	private String userID;
	private String username;
	private String phone;
	private String address;
	private String email;
	private String password;
	private Date dob;
	private String gender;
	private String role;	
	
	public User(String userID, String username, String phone, String address, String email, String password, Date dob,
			String gender, String role) {
		this.userID = userID;
		this.username = username;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.role = role;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
