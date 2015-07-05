package criteria;

public class Wife {
	private long id;
	private String name;
	private Husband husband;
	
	public Husband getHusband() {
		return husband;
	}
	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	public long getId() {
		return id;
	}
//	public Wife() {}
//	public Wife(long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
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
