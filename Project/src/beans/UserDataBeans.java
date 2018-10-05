package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataBeans {
	private int id;
	private String loginId;
	private String name;
	private Date birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	private String birthDateStr;
	private String createDateStr;
	private String updateDateStr;

	public UserDataBeans() {
		this.name = "";
		this.birthDate = null;
		this.loginId = "";
		this.password = "";
	}

	public UserDataBeans(int id, String loginId, String name) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
	}

	public UserDataBeans(String loginId, String name, Date birthDate) {
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
	}

	public UserDataBeans(int id, String loginId, String name, Date birthDate, String createDate, String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getBirthDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		this.birthDateStr = sdf.format(birthDate);
		return birthDateStr;
	}
	public String getCreateDateStr() {
		String year = createDate.substring(0, 4);
		String month = createDate.substring(5, 7);
		String day = createDate.substring(8,10);
		String time = createDate.substring(11,13);
		String minute = createDate.substring(14,16);
		createDateStr = year +"年"+ month +"月"+ day +"日 "+ time +"時"+ minute +"分";
		return createDateStr;
	}
	public String getUpdateDateStr() {
		String year = updateDate.substring(0, 4);
		String month = updateDate.substring(5, 7);
		String day = updateDate.substring(8,10);
		String time = updateDate.substring(11,13);
		String minute = updateDate.substring(14,16);
		updateDateStr = year +"年"+ month +"月"+ day +"日 "+ time +"時"+ minute +"分";
		return updateDateStr;
	}
}
