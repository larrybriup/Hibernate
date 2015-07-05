package Composite;

import java.io.Serializable;

//这个主键必须实现序列化接口
//最好重写hashCode()和equals()方法
public class PersonPK implements Serializable{
	private static final long serialVersionUID = 4088278718050160361L;
	private long id;
	private String name;
	public PersonPK() {	}
	public PersonPK(long id, String name) {
		this.id = id;
		this.name = name;
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
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (id ^ (id >>> 32));
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(!(obj instanceof PersonPK)) {
			return false;
		}
//		if (getClass() != obj.getClass())
//			return false;
		PersonPK pk = (PersonPK) obj;
		if (this.id != pk.id&&!this.name.equals(pk.name))
			return false;
//		if (name == null) {
//			if (pk.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
		return false;
	}
	
}
