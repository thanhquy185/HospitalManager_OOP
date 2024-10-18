package MedicalRecord;

    //  Chứa các thông tin của bệnh
public class Sick {
    //	Properties 
	private String idSick;
	private String nameSick;
	private String levelSick;

    // Constructor
	public Sick() {
		idSick = "";
		nameSick = "";
		levelSick = "";
	}

	public Sick(String idSick, String nameSick, String levelSick) {
		this.idSick = idSick;
		this.nameSick = nameSick;
		this.levelSick = levelSick;
	}
	public Sick (Sick sick)
	{
		this.idSick = sick.idSick;
		this.nameSick = sick.nameSick;
		this.levelSick = sick.levelSick;
	}
    // Setter-Getter
	public void setidSick(String idSick) {
		this.idSick = idSick;
	}

	public void setnameSick(String nameSick) {
		this.nameSick = nameSick;
	}

	public void setlevelSick(String levelSick) {
		this.levelSick = levelSick;
	}

	public String getidSick() {
		return this.idSick;
	}

	public String getnameSick() {
		return this.nameSick;
	}

	public String getlevelSick() {
		return this.levelSick;
	}
}
