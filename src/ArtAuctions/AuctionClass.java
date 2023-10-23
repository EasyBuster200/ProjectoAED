package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class AuctionClass implements Auction {

	private String auctionId;
	private List<WorkAuction> individualAuctions;
	private List<ArtWork> auctionWorks;
	//TODO: Maybe save the highest bidder, updating when needed. This might make it easier to sell an artwork once the auction is closed
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionClass(String auctionId) {
		this.auctionId = auctionId;
		this.individualAuctions = new DoubleList<>();
		this.auctionWorks = new DoubleList<>();
	}

	@Override
	public String auctionId() {
		return this.auctionId;
	}

	@Override
	public void addWorkAuction(ArtWork artwork, int minimumValue) {
		auctionWorks.addLast(artwork);
		individualAuctions.addLast(new WorkAuctionClass(artwork, minimumValue));
	}

	@Override
	public void addBid(Bid bid, ArtWork work) {
		WorkAuction individualAuction = getAuction(work);
		individualAuction.addBid(bid);
	}

	@Override
	public boolean hasNoWorks() {
		return auctionWorks.isEmpty();
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		return auctionWorks.iterator();
	}

	@Override
	public ArtWork getWork(String workId) {
		Iterator<ArtWork> it = auctionWorks.iterator();

		while (it.hasNext()) {
			ArtWork current = it.next();

			if (current.workId().equalsIgnoreCase(workId))
				return current;
		}

		return null;
	}

	@Override
	public Iterator<Bid> getWorkBids(ArtWork work) {
		Iterator<WorkAuction> it = individualAuctions.iterator();

		while(it.hasNext()) {
			WorkAuction current = it.next();
			ArtWork currentWork = current.getWork();

			if(currentWork.workId().equalsIgnoreCase(work.workId()))
				return current.bidsIterator();
		}

		return null;
	}

	@Override
	public int getMinimumBidValue(ArtWork work) {
		WorkAuction individualAuction = getAuction(work);
		return individualAuction.minimumBidValue();
	}

	private WorkAuction getAuction(ArtWork work) {
		Iterator<WorkAuction> it = individualAuctions.iterator();

		while (it.hasNext()) {
			WorkAuction current = it.next();
			ArtWork currentWork = current.getWork();

			if (currentWork.workId().equals(work.workId()))
				return current;
		}

		return null;
	}

	//TODO: Add a close auction, which will sell all the paintings 

}