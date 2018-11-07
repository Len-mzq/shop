package entity;

import java.util.List;

public class Product {
	private Integer id;
	private String name;
	private String price;// ��Ʒ�۸�
	private String remark;// ��Ʒ����
	private Integer inventory;// ��Ʒ�����
	private Integer sales_volume;// ��Ʒ������
	private Integer category_id;// ��Ʒ����id
	private String title;// ��Ʒ����
	private String store;// ��Ʒ����
	private String icons;// ����ͼ��
	private Img img;
	private List<Img> imgs;
	private Integer amount;// ��Ʒ����

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<Img> getImgs() {
		return imgs;
	}

	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSales_volume() {
		return sales_volume;
	}

	public void setSales_volume(Integer sales_volume) {
		this.sales_volume = sales_volume;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public Img getImg() {
		return img;
	}

	public void setImg(Img img) {
		this.img = img;
	}

}
