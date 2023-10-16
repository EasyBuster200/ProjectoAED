package ArtAuctions;

public class Collector implements User {

	protected String login,name,email;
	protected int age;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Collector(String login, String name, int age,String email) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
	}

	@Override
	public String login() {
		return this.login;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public int age() {
		return this.age;
	}

	@Override
	public String email() {
		return this.email;
	}

	@Override
	public void addBoughtWork(ArtWork work) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addBoughtWork'");
	}

}
