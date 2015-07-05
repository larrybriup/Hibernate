package annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity

public class Wife {
	private long id;
	private String name;
	private Husband husband;
	
//	@Transient
	//在注解映射关系的时候,只要是双向关联,一定会在一方加入一个属性值mappedBy=""
	//mappingBy=""表示将来长生的外键列是由对方来负责的
	//mappingBy=""属性的值是:当前类在对方类中做属性的名字
	@OneToOne(mappedBy="wife")
	public Husband getHusband() {
		return husband;
	}
	public void setHusband(Husband husband) {
		this.husband = husband;
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
