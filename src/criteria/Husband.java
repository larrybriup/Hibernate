package criteria;

public class Husband {
private long id;
private String name;
private Wife wife;
public Wife getWife() {
	return wife;
}
public void setWife(Wife wife) {
	this.wife = wife;
}
//public Husband() {}
//public Husband(long id, String name) {
//	super();
//	this.id = id;
//	this.name = name;
//}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
