package apply.bean;

public class Students {
	private int id;
	private int sex;
	private String name;
	private String stu_id; //校园卡号
	private String cla;  //班级
	private String QQ;  
	private String tel;
	private String other;  //个人简介及兴趣爱好
	
	//获取和创建id
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//获取和创建sex
	public int getSex() {
		return this.sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	//获取和创建name
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//获取和创建name
	public String getSid() {
		return this.stu_id;
	}
	public void setSid(String sid) {
		this.stu_id = sid;
	}
	
	//获取和创建class
	public String getCla() {
		return this.cla;
	}
	public void setCla(String cla) {
		this.cla = cla;
	}
	
	//获取和创建QQ
	public String getQQ() {
		return this.QQ;
	}
	public void setQQ(String QQ) {
		this.QQ = QQ;
	}
	
	//获取和创建tel
	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	//获取和创建other
	public String getOther() {
		return this.other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
