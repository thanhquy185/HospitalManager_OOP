package Common;

public abstract class Person {
	//Properties
	protected String fullname;
	protected Date birthday;
	protected String gender;
	protected String country;
	protected String phone;
	
	//Constructors
	public Person() {
		this.fullname = "?";
		this.birthday = new Date();
		this.gender = "?";
		this.country = "?";
		this.phone = "?";
	}
	public Person(String fullname, Date birthday, String gender, String country, String phone) {
		this.fullname = fullname;
		this.birthday = birthday;
		this.gender = gender;
		this.country = country;
		this.phone = phone;
	}
	public Person(Person person) {
		this.fullname = person.fullname;
		this.birthday = person.birthday;
		this.gender = person.gender;
		this.country = person.country;
		this.phone = person.phone;
	}
	
	//Setter - Getter
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFullname() {
		return this.fullname;
	}
	public Date getBirthday() {
		return this.birthday;
	}
	public String getGender() {
		return this.gender;
	}
	public String getCountry() {
		return this.country;
	}
	public String getPhone() {
		return this.phone;
	}
	
	//Methods (Còn nhiều hàm nữa mà tôi chưa viết)
}
