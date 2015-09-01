package annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_student")
public class Student {
	
	private long id;
	private String name;
	private Set<Teacher> teachers = new LinkedHashSet<Teacher>();

//	@Transient
	@ManyToMany(mappedBy="students")
	@JoinTable(name="students_teachers",
					joinColumns= {
							@JoinColumn(name="s_id")
					},
					inverseJoinColumns= {
							@JoinColumn(name="t_id")
					}
	)
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Id
	@GeneratedValue
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
