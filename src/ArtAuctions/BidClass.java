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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'bidValue'");
	}

	@Override
	public String biddersName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'biddersName'");
	}

	@Override
	public String biddersLogin() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'biddersLogin'");
	}

}
