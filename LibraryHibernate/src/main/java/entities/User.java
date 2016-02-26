package entities;

public class User {
	public static int nextId;
	private int id;
	private String name;
	private String ssn;
	private String address;
	private Boolean isAdmin;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User(String name, String ssn, String address, int id, Boolean isAdmin){
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.id = id;
		this.isAdmin = isAdmin;
		nextId = id + 1;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
}
