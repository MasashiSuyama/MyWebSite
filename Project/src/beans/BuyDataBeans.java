package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyDataBeans {
	private int id;
	private int userId;
	private int totalPrice;
	private Date arrivalDate;
	private Date buyDate;

	private String arrivalDateStr;
	private String buyDateStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getArrivalDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		this.arrivalDateStr = sdf.format(arrivalDate);
		return arrivalDateStr;
	}

	public String getBuyDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		this.buyDateStr = sdf.format(buyDate);
		return buyDateStr;
	}

}
