package ArtAuctions;

import Exceptions.workHasNoBidsException;
import dataStructures.Dictionary;
import dataStructures.DoubleList;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.SepChainHashTable;

/**
 * Auction implementation 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class AuctionClass implements Auction {

	/**
	 * ID of the auction 
	 */
	private String auctionId;

	/**
	 * Dictionary linking the ID of a work to its Individual Auction
	 * WorkID --> WorkAuction
	 */
	private Dictionary<String, WorkAuction> individualAuctions;

	/**
	 * List of the ArtWorks in the auction
	 */
	private List<ArtWorkReadOnly> auctionWorks;
	
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
		this.individualAuctions = new SepChainHashTable<>();
		this.auctionWorks = new DoubleList<>();
	}

	@Override
	public String auctionId() {
		return this.auctionId;
	}

	@Override
	public void addWorkAuction(ArtWork artwork, int minimumValue) {
		if (auctionWorks.find(artwork) == -1) {
			auctionWorks.addLast(artwork);
			individualAuctions.insert(artwork.workId(), new WorkAuctionClass(artwork, minimumValue));
		}

	}

	@Override
	public void addBid(Bid bid, ArtWork work) {
		individualAuctions.find(work.workId()).addBid(bid);
	}

	@Override
	public boolean hasNoWorks() {
		return auctionWorks.isEmpty();
	}

	@Override
	public Iterator<ArtWorkReadOnly> worksIterator() {
		return auctionWorks.iterator();
	}

	@Override
	public ArtWork getWork(String workId) {
		Iterator<ArtWorkReadOnly> it = auctionWorks.iterator();

		while (it.hasNext()) {
			ArtWork current = (ArtWork) it.next();

			if (current.workId().equalsIgnoreCase(workId))
				return current;
		}

		return null;
	}

	@Override
	public Iterator<BidReadOnly> getWorkBids(ArtWork work) throws workHasNoBidsException {
		Iterator<Entry<String, WorkAuction>> it = individualAuctions.iterator();

		while(it.hasNext()) {
			WorkAuction current = it.next().getValue();
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
		return individualAuctions.find(work.workId()).minimumBidValue();
	}

	@Override
	public Iterator<Entry<String, WorkAuction>> getIndividualAuctions() {
		return this.individualAuctions.iterator();
	}

}