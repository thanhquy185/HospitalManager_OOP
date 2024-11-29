package Common;

public class Date {
	//Properties
	private Integer day;
	private Integer month;
	private Integer year;
	private static Date instance;

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
	public static Date getInstance() {
		if(Date.instance == null) {
			Date.instance = new Date();
		}
		return Date.instance;
	}

	//Methods
	// - Hàm kiểm tra có phải là năm nhuận hay không ?
	private boolean leapYear(int year) {
		if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
			return true;
		return false;
	}
	// - Hàm tính tổng số ngày của đối tượng Date, tính từ 01-01-1900
	private int calDays(int day, int month, int year) {
		int days = 0;
		for(int i = 1900; i < year; i++) {
			days += leapYear(i) ? 366 : 365;
		}
		for(int i = 1; i < month; i++) {
			switch(i) {
				case 2: {
					days += leapYear(year) ? 29 : 28;
					break;
				}
				case 4: case 6: case 9: case 11: {
					days += 30;
					break;
				}
				case 1: case 3: case 5: case 7: case 8: case 10: case 12: {
					days += 31;
				}
			}
		}
		days += day;
		return days;
	}
	// - Hàm tính số ngày giữa ngày trước và ngày sau
	public int calNumbersOfDay(Date dateBefore, Date dateAfter) {
		int daysBefore = calDays(dateBefore.getDay(), dateBefore.getMonth(), dateBefore.getYear());
		int daysAfter = calDays(dateAfter.getDay(), dateAfter.getMonth(), dateAfter.getYear());
		int res = daysAfter - daysBefore;
		return res;
	}
	// - Hàm kiểm tra ngày sau phải đúng là "sau" ngày trước
	public boolean checkBeforeAfterDate(Date dateBefore, Date dateAfter) {
		if (dateBefore.getYear() > dateAfter.getYear()) {
			return false;
		} else if(dateBefore.getYear() == dateAfter.getYear()) {
			if (dateBefore.getMonth() > dateAfter.getMonth()) {
				return false;
			} else if(dateBefore.getMonth() == dateAfter.getMonth()) {
				if(dateBefore.getDay() >= dateAfter.getDay()) return false;
			}
		}
		return true;
	}
	// - Kiểm tra ngày sinh có hợp lệ hay không ? (đối tượng Date)
	public boolean isDate() {
		if(year < 1900 || year > 2025) return false;
		if(month < 1 || month > 12) return false;
		if(leapYear(year)) {
			if(month == 2) {
				if(day < 1 || day > 29) return false;
			} else {
				if(day < 1 || day > 31) return false;
			}
		} else {
			if(month == 2) {
				if(day < 1 || day > 28) return false;
			} else {
				if(day < 1 || day > 31) return false;
			}
		}
		return true;
	}
	// - Kiểm tra một chuỗi có đúng định dạng ngày sinh cho phép (dd-mm-yyyy, ddmmyyyy)
	public boolean isDateFormat(String date) {
		if(date.length() != 8 && date.length() != 10) return false;
		if(date.length() == 8) {
			for(int i = 0; i < date.length(); i++) {
				if((int) date.charAt(i) < 48 || (int) date.charAt(i) > 57) return false;
			}
		}
		else {
			for(int i = 0; i < date.length(); i++) {
				if((i == 2 || i == 5) && (int) date.charAt(i) != 45) return false;
				if(i != 2 && i != 5 && ((int) date.charAt(i) < 48 || (int) date.charAt(i) > 57)) return false;
			}
		}
		return true;
	}
	// - Tạo đối tượng Date thông qua chuỗi hiển thị ngày dd-mm-yyyy, ddmmyyyy
	public Date getDateFromDateFormat(String dateFormat) {
		if(!isDateFormat(dateFormat)) return null;
		int day = 0; int month = 0; int year = 0;
		if(dateFormat.length() == 8) {
			day = Integer.parseInt(dateFormat.substring(0, 2));
			month = Integer.parseInt(dateFormat.substring(2, 4));
			year = Integer.parseInt(dateFormat.substring(4));
		} else {
			day = Integer.parseInt(dateFormat.substring(0, 2));
			month = Integer.parseInt(dateFormat.substring(3, 5));
			year = Integer.parseInt(dateFormat.substring(6));
		}
		Date date = new Date(day, month, year);
		return date;
	}
	// - Thay đổi việc hiển thị với đối tượng Date ở đối tượng
	// -- dd-mm-yyy mang ý nghĩa hiển thị khi show info hoặc save to file
	// -- ddmmyyyy này dùng để tạo mật khẩu cho tài khoản (Password: tennguoinngaysinh)
	public String getDateFormatByCondition(String condition) {
		if(!isDate()) return "Ngày sinh không hợp lệ";
		String day = String.valueOf(this.day);
			if(this.day <= 9) day = "0" + day;
		String month = String.valueOf(this.month);
			if(this.month <= 9) month = "0" + month;
		String year = String.valueOf(this.year);
		//Lấy được định dạng "dd/mm/yyyy"
		if(condition == "has cross")
			return day + "-" + month + "-" + year;
		if(condition == "has not cross")
			return day + month + year;
		return null;
	}
}
