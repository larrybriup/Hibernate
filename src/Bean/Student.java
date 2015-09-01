package Bean;

public class Student {
	private long id;
	private String name;
	private int age;

	// 只要是java.bean就要有无参构造器
	public Student() {
	}

	public Student(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "id=" + id + " name=" + name + " age=" + age;
	}

}
