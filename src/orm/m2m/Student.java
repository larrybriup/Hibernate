package orm.m2m;

import java.util.LinkedHashSet;
import java.util.Set;

public class Student {

	private long id;
	private String name;
	private Set<Teacher> teachers = new LinkedHashSet<Teacher>();

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
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
}
