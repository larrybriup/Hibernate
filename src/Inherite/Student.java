package Inherite;

public class Student extends Person{
	private double score;
public Student() {}
public Student(PersonPK pk,int age,Address address,double score) {
	super(pk,age,address);
	this.score= score;
}
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
}
