package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.List;

public class Collector implements User {

	private String login, name, email;
	private int age;
	private List<ArtWork> ownedWorks;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Collector(String login, String name, int age,String email) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
		this.ownedWorks = new DoubleList<>();
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
		ownedWorks.addLast(work);
	}

	@Override
	public void addBid(Bid bid) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addBid'");
	}

}
