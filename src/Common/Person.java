package Common;

public class Person {
	//Properties
	protected String fullname;
	protected Date birthday;
	protected String gender;
	protected String phone;
	protected String country;
	
	//Constructors
	public Person() {
		this.fullname = null;
		this.birthday = new Date();
		this.gender = null;
		this.phone = null;
		this.country = null;
	}
	public Person(String fullname, Date birthday, String gender, String phone, String country) {
		this.fullname = fullname;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.country = country;
	}
	public Person(Person person) {
		this.fullname = person.fullname;
		this.birthday = person.birthday;
		this.gender = person.gender;
		this.phone = person.phone;
		this.country = person.country;
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
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getPhone() {
		return this.phone;
	}
	public String getCountry() {
		return this.country;
	}

	//Methods
	// - Hàm trả về tên gồm các chữ cái thường và không khoảng trắng
	public String getFullnameFormat() {
		return CharacterFormat.removeAccent(
			this.fullname.toLowerCase().replaceAll(" ", "")
		);
	}
	public String getBirthdayFormat(String condition) {
		if(condition == null)
			return "dd/mm/yyyy";
		return this.birthday.getDateFormatByCondition(condition);
	}
}
