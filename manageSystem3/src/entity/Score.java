package entity;

public class Score {
	private int id;
	private Integer value;
	private String grade;
	private Employee emp;
	private Project pro;
	private Department dep;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Project getPro() {
		return pro;
	}

	public void setPro(Project pro) {
		this.pro = pro;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

}
