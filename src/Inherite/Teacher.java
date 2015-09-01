package Inherite;

public class Teacher extends Person {

	private double salary;

	public Teacher() {
	}

	public Teacher(PersonPK pk, int age, Address address, double salary) {
		super(pk, age, address);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
		// return 10001000;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
