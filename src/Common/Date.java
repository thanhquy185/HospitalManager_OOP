package Common;

public class Date {
	//Properties
	private Integer day;
	private Integer month;
	private Integer year;

	//Constructors
	public Date() {
		day = null;
		month = null;
		year = null;
	}
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public Date(Date date) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}

	//Setter - Getter
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDay() {
		return this.day;
	}
	public int getMonth() {
		return this.month;
	}
	public int getYear() {
		return this.year;
	}

	//Methods
	public static boolean isDay(int day) {
		if(day <= 0 || day >= 32)
			return false;
		return true;
	}
	public static boolean isMonth(int month) {
		if(month <= 0 || month >= 13)
			return false;
		return true;
	}
	public static boolean isYear(int year) {
		if(year < 1900 || year > 2025)
			return false;
		return true;
	}
	public static boolean isDate(int day, int month, int year) {
		if(!isDay(day)) return false;
		if(!isMonth(month)) return false;
		if(!isYear(year)) return false;
		return true;
	}
	public String getDateFormatByCondition(String condition) {
		if(!isDate(this.day, this.month, this.year)) 
			return "Ngày sinh không hợp lệ";
		String day = String.valueOf(this.day);
		if(this.day<=9) day = "0" + day;
		String month = String.valueOf(this.month);
		if(this.month<=9) month = "0" + month;
		String year = String.valueOf(this.year);
		//Lấy được định dạng "dd/mm/yyyy"
		if(condition == "has forward slash")
			return day + "/" + month + "/" + year;
		if(condition == "has not forward slash")
			return day + month + year;
		return null;
	}
}
