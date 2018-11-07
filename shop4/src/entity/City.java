package entity;

public class City {
	private Integer id;
	private Integer CityId;
	private String city;
	private Integer father;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return CityId;
	}

	public void setCityId(Integer cityId) {
		CityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getFather() {
		return father;
	}

	public void setFather(Integer father) {
		this.father = father;
	}

}
