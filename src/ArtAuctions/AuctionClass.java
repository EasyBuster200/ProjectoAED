package ArtAuctions;

import dataStructures.Iterator;

public class AuctionClass implements Auction {

	protected String auctionId;
	//TODO? Maybe have an array with the art works in the auction, easier to access each art work individually, and easier to get the iterators 
	
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
	public void addBid(Bid bid) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addBid'");
	}

	@Override
	public boolean hasNoWorks() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'hasNoWorks'");
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'worksIterator'");
	}

	@Override
	public ArtWork getWork(String workId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getWork'");
	}

}
