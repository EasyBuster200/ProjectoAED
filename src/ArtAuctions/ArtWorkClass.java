package ArtAuctions;

import Exceptions.workHasNoBidsException;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class ArtWorkClass implements ArtWork {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String workId,name;
	private int year, lastAuctionPrice, minimumBidValue, highestSoldValue;
	private boolean beenSold;
	private Artist author;
	private User buyer;
	private List<Bid> bids;
	
	public ArtWorkClass(String workId, String name, int year, Artist author) {
		this.workId = workId;
		this.name = name;
		this.year = year;
		this.author = author;
		this.lastAuctionPrice = 0;
		this.minimumBidValue = 0;
		this.highestSoldValue = 0;
		this.beenSold = false;
		this.bids = new DoubleList<>();
	}

	@Override
	public String workId() {
		return this.workId;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public int year() {
		return this.year;
	}

	@Override
	public int lastAuctionPrice() {
		return this.lastAuctionPrice;
	}

	@Override
	public void newAuctionPrice(int auctionPrice) {
		this.lastAuctionPrice = auctionPrice;	
	}

	@Override
	public String authorLogin() {
		return this.author.login();
	}

	@Override
	public String authorName() {
		return this.author.name();
	}

	@Override
	public boolean beenSold() {
		return this.beenSold;
	}

	@Override
	public String buyerLogin() {
		return this.buyer.login();
	}

	@Override
	public String buyerName() {
		return this.buyer.name();
	}

	@Override
	public int highestSoldValue() {
		return highestSoldValue;
	}

	@Override
	public int minimumBidValue() {
		return minimumBidValue;
	}

	@Override
	public void setHighestSoldValue(int newHighestSoldValue) {
		this.highestSoldValue = newHighestSoldValue;
	}

	@Override
	public void setMinimumBidValue(int newMinimumBidValue) {
		this.minimumBidValue = newMinimumBidValue;
	}

	@Override
	public Iterator<Bid> bidsIterator() throws workHasNoBidsException {
		if (bids.isEmpty())
			throw new workHasNoBidsException();

		return bids.iterator();
	}

}
