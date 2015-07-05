package Inherite;

//继承映射
public class Person {
	private int age;
	private Address address;
	private PersonPK pk;
	
	public Person() {}
	public Person(PersonPK pk, int age,Address address ) {
		this.age = age;
		this.address = address;
		this.pk = pk;
	}
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
