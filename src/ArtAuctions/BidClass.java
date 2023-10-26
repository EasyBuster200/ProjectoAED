package ArtAuctions;

public class BidClass implements Bid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int bidValue;
	private  User bidder;

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

}
