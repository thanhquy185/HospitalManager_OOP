package MedicalRecord;

class Sick {
    // Properties
	private String id;
	private String name;
	private String level;

    // Constructor
	public Sick() {
		id = "";
		name = "";
		level = "";
	}
	public Sick(String id, String name, String level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}
	public Sick (Sick sick) {
		this.id = sick.id;
		this.name = sick.name;
		this.level = sick.level;
	}

    // Setter-Getter
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLevel() {
		return level;
	}

	// Methods
}
