package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class AuctionClass implements Auction {

	private String auctionId;
	private List<ArtWork> works;
	//TODO? Maybe have an array with the art works in the auction, easier to access each art work individually, and easier to get the iterators 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionClass(String auctionId) {
		this.auctionId = auctionId;
		this.works = new DoubleList<>();
	}

	@Override
	public String auctionId() {
		return this.auctionId;
	}

	@Override
	public void addWorkAuction(ArtWork artwork) {
		works.addLast(artwork);
	}

	@Override
	public void addBid(Bid bid) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addBid'");
	}

	@Override
	public boolean hasNoWorks() {
		return works.isEmpty();
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		return works.iterator();
	}

	@Override
	public ArtWork getWork(String workId) {
		Iterator<ArtWork> it = works.iterator();

		while(it.hasNext()) {
			ArtWork current = it.next();

			if(current.workId().equalsIgnoreCase(workId))
				return current;
		}

		return null;
	}

}
