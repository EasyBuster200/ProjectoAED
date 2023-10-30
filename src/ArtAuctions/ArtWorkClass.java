package ArtAuctions;

public class ArtWorkClass implements ArtWork {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String workId,name;
	private int year, lastAuctionPrice, highestSoldValue;
	private boolean beenSold;
	private Artist author;
	private User buyer;
	
	public ArtWorkClass(String workId, String name, int year, Artist author) {
		this.workId = workId;
		this.name = name;
		this.year = year;
		this.author = author;
		this.lastAuctionPrice = 0;
		this.highestSoldValue = 0;
		this.beenSold = false;
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
		return this.highestSoldValue;
	}

	@Override
	public void setHighestSoldValue(int newHighestSoldValue) {
		this.highestSoldValue = newHighestSoldValue;
	}

	@Override
	public void setLastSoldPrice(int newLastSoldPrice) {
		this.lastAuctionPrice = newLastSoldPrice;
	}

	@Override
	public void addedToAuction() {
		author.workAddedToAuction();
	}

	@Override
	public void removedFromAuction() {
		author.workRemovedFromAuction();
	}

	

}
