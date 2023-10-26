package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.List;

public class Collector implements User {

	private String login, name, email;
	private int age;
	private List<Bid> bids;
	//TODO: There's gotta be a better way to know if a given user has open bids

	private static final long serialVersionUID = 1L;

	public Collector(String login, String name, int age,String email) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
		this.bids = new DoubleList<>();
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
	public void addBid(Bid bid) {
		this.bids.addLast(bid);
	}

	@Override
	public boolean hasBids() {
		return !this.bids.isEmpty();
	}

}
