package ArtAuctions;

import Exceptions.workHasNoBidsException;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * Auction implementation 
 * @author Lipy Cardoso - 63542
 * @author Duarte Coelho - 65154
 */
public class AuctionClass implements Auction {

	private String auctionId;
	private List<WorkAuction> individualAuctions;
	private List<ArtWork> auctionWorks;
	
	/**
	 * Serial version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * AuctionClass constructor that creates a new Auction
	 * @param auctionId - the auction id
	 */
	public AuctionClass(String auctionId) {
		this.auctionId = auctionId;
		this.individualAuctions = new DoubleList<>(); //TODO: Should we have a dictionary so its easier to get the individual auction, from a given work
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
	public Iterator<Bid> getWorkBids(ArtWork work) throws workHasNoBidsException {
		Iterator<WorkAuction> it = individualAuctions.iterator();

		while(it.hasNext()) {
			WorkAuction current = it.next();
			ArtWork currentWork = current.getWork();

			if(currentWork.workId().equalsIgnoreCase(work.workId())) {
				if (!current.hasBids())
					throw new workHasNoBidsException();
				return current.bidsIterator();
			}
		}

		return null;
	}

	@Override
	public int getMinimumBidValue(ArtWork work) {
		WorkAuction individualAuction = getAuction(work);
		return individualAuction.minimumBidValue();
	}

	@Override
	public void closeAuction() {
		Iterator<WorkAuction> it = individualAuctions.iterator();

		while (it.hasNext())
			it.next().closeAuction();
	}
	
	/**
	 * Returns the workAuction of the given artWork.
	 * @param work the work of the auction
	 * @return the workAuction, return null if the worAuction does no exist.
	 */
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

}