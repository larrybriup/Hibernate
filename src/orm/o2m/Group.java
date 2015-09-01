package orm.o2m;

import java.util.LinkedHashSet;
import java.util.Set;

public class Group {
	static long id;
	private String name;
//	this will keep the insert order of user
	private Set<User> users = new LinkedHashSet<User>();

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
