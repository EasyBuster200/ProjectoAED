package ArtAuctions;

/**
 * Bid implentation
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class BidClass implements Bid {

	/**
	 * Serial Version UID the Class
	 */
	private static final long serialVersionUID = 1L;

	private int bidValue;
	private User bidder;

	/**
	 * BidClass constructor that creates a new BidClass
	 * @param value the amount of the bid
	 * @param User the user that is bidding
	 */
	public BidClass(int value, User bidder) {
		this.bidValue = value;
		this.bidder = bidder;
	}

	@Override
	public int bidValue() {
		return this.bidValue;
	}

	@Override
	public String biddersName() {
		return this.bidder.name();
	}

	@Override
	public String biddersLogin() {
		return this.bidder.login();
	}

	@Override
	public User bidder() {
		return this.bidder;
	}

	@Override
	public void bidClosed() {
		this.bidder.removeBid();
	}

}
