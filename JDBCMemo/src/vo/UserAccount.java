package vo;

public class UserAccount {

	private String name;
	private String id;
	private String password;

	public UserAccount(String name, String id, String password) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
	}

	public UserAccount() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name=" + name + ", ID=" + id + ", PW=" + password;
	}

}
