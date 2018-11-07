package entity;

public class Area {
	private Integer id;
	private Integer AreaId;
	private String area;
	private Integer father;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return AreaId;
	}

	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getFather() {
		return father;
	}

	public void setFather(Integer father) {
		this.father = father;
	}

}
