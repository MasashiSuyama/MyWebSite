package beans;

public class ItemDataBeans {
	private int id;
	private String name;
	private String detail;
	private boolean allergyEgg;
	private boolean allergyWheat;
	private boolean allergyMilk;
	private String allergyOther;
	private int price;
	private String fileName;
	private int stock;
	private int buySum;
	private int buyCount;

	public ItemDataBeans() {

	}

	public ItemDataBeans(int id, String name, String detail, boolean allergyEgg, boolean allergyWheat, boolean allergyMilk,
			String allergyOther, int price, String fileNamme, int stock, int buySum) {

		this.id = id;
		this.name = name;
		this.detail = detail;
		this.allergyEgg = allergyEgg;
		this.allergyWheat = allergyWheat;
		this.allergyMilk = allergyMilk;
		this.allergyOther = allergyOther;
		this.price = price;
		this.fileName = fileNamme;
		this.stock = stock;
		this.buySum = buySum;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Boolean getAllergyEgg() {
		return allergyEgg;
	}
	public void setAllergyEgg(Boolean allergyEgg) {
		this.allergyEgg = allergyEgg;
	}
	public Boolean getAllergyWheat() {
		return allergyWheat;
	}
	public void setAllergyWheat(Boolean allergyWheat) {
		this.allergyWheat = allergyWheat;
	}
	public Boolean getAllergyMilk() {
		return allergyMilk;
	}
	public void setAllergyMilk(Boolean allergyMilk) {
		this.allergyMilk = allergyMilk;
	}
	public String getAllergyOther() {
		return allergyOther;
	}
	public void setAllergyOther(String allergyOther) {
		this.allergyOther = allergyOther;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getBuySum() {
		return buySum;
	}
	public void setBuySum(int buySum) {
		this.buySum = buySum;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

}
