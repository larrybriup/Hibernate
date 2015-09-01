package Composite;

//组件映射 联合主键映射
public class Person {
	
	private int age;
	private Address address;
	private PersonPK pk;

	public PersonPK getPk() {
		return pk;
	}

	public void setPk(PersonPK pk) {
		this.pk = pk;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
