package ArtAuctions;

import java.io.Serializable;

public class Collector implements User, Serializable {

	protected String login,name,email;
	protected int age;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Collector(String login, String name, int age,String email) {
		// TODO Auto-generated constructor stub
		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
	}

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int age() {
		// TODO Auto-generated method stub
		return this.age;
	}

	@Override
	public String email() {
		// TODO Auto-generated method stub
		return this.email;
	}

}
