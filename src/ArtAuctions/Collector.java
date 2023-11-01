package ArtAuctions;


public class Collector implements User {

	private String login, name, email;
	private int age, nbrBids;

	private static final long serialVersionUID = 1L;

	public Collector(String login, String name, int age,String email) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
		this.nbrBids = 0;
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
	public void addBid() {
		this.nbrBids++;
	}

	@Override
	public void removeBid() {
		this.nbrBids--;
	}

	@Override
	public boolean hasBids() {
		return nbrBids > 0;
	}

}
