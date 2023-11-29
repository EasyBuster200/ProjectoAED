package ArtAuctions;

/**
 * A User implementation
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class Collector implements User {

	/**
	 * The unique login of the User
	 */
	private String login;

	/**
	 * The name of the User 
	 */
	private String name;

	/**
	 * The email of the User 
	 */
	private String email;

	/**
	 * The age of the User
	 */
	private int age;

	/**
	 * The number of bids the User currently has open
	 */
	private int nbrBids;

	/**
	 * Serial version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Collector constructor creates a new collector with ther given data
	 * @param login collector login
	 * @param name collector name
	 * @param age  collector age
	 * @param email collector email
	 */
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
	public boolean hasBids() {
		return nbrBids > 0;
	}

	@Override
	public void addBid() {
		this.nbrBids++;
	}

	@Override
	public void removeBid() {
		this.nbrBids--;
	}

}
