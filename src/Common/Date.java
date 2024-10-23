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
		if(!isDate(day, month, year)) {
			System.out.println("Ngày sinh không hợp lệ");
			return;
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public Date(Date date) {
		if(!isDate(date.day, date.month, date.year)) {
			System.out.println("Ngày sinh không hợp lệ");
			return;
		}
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}
	
	//Setter - Getter
	public void setDay(int day) {
		if(!isDay(day)) {
			System.out.println("Ngày không hợp lệ");
			return;
		}
		this.day = day;
	}
	public void setMonth(int month) {
		if(!isMonth(month)) {
			System.out.println("Tháng không hợp lệ");
			return;
		}
		this.month = month;
	}
	public void setYear(int year) {
		if(!isYear(year)) {
			System.out.println("Năm không hợp lệ");
			return;
		}
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
	private boolean isDay(int day) {
		if(day <= 0 || day >= 32)
			return false;
		return true;
	}
	private boolean isMonth(int month) {
		if(month <= 0 || month >= 13)
			return false;
		return true;
	}
	private boolean isYear(int year) {
		//Tuổi làm tối thiểu là 25 tuổi và tối đa là 54 tuổi
		if(year <= 1969 || year >= 2000)
			return false;
		return true;
	}
	private boolean isDate(int day, int month, int year) {
		if(!isDay(day)) return false;
		if(!isMonth(month)) return false;
		if(!isYear(year)) return false;
		return true;
	}
	public String getDate() {
		if(this.day == null || this.month == null || this.year == null
			|| !isDate(this.day, this.month, this.year))
			return "dd/mm/yyyy";
		String day = String.valueOf(this.day);
		if(this.day<=9) day = "0" + day;
		String month = String.valueOf(this.month);
		if(this.month<=9) month = "0" + month;
		//Lấy được định dạng "dd/mm/yyyy"
		return day + "/" + month + "/" + String.valueOf(this.year);
	}


	// public static void main(String[] args) {
	// 	Date date = new Date(1, 8, 1998);
	// 	System.out.println(date.getDate());
	// }
}
