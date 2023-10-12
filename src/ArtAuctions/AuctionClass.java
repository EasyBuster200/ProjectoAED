package ArtAuctions;

public class AuctionClass implements Auction {

	protected String auctionId;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionClass(String auctionId) {
		// TODO Auto-generated constructor stub
		this.auctionId = auctionId;
	}

	@Override
	public String auctionId() {
		// TODO Auto-generated method stub
		return this.auctionId;
	}

	@Override
	public void addWorkAuction(ArtWork artwork) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBid(Bid bid, ArtWork artWork) {
		// TODO Auto-generated method stub
		
	}

}
