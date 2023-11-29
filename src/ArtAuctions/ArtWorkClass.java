package ArtAuctions;

/**
 * Artwork implementation
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class ArtWorkClass implements ArtWork {

	/**
	 * Serial version UID of the Class
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique Id of the work
	 */
	private String workId;

	/**
	 * Name of the ArtWork
	 */
	private String name;

	/**
	 * The year of creation of the ArtWork
	 */
	private int year;

	/**
	 * The last price the ArtWork was sold for in auction
	 */
	private int lastAuctionPrice;

	/**
	 * The highest price the ArtWork has ever been sold for in auction
	 */
	private int highestSoldValue;

	/**
	 * The Artist that made the ArtWork
	 */
	private Artist author;

	/**
	 * The last User to buy the ArtWork
	 */
	private User buyer;
	
	/**
	 * ArtWorkClass constructor that creates a new artwork
	 * @param workId - the work id
	 * @param name - the work name 
	 * @param year - the work release year 
	 * @param author - the artwork author 
	 */
	public ArtWorkClass(String workId, String name, int year, Artist author) {
		this.workId = workId;
		this.name = name;
		this.year = year;
		this.author = author;
		this.lastAuctionPrice = 0;
		this.highestSoldValue = 0;
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
	public User getBuyer() {
		return buyer;
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

	@Override
	public void sold(User buyer) {
		this.buyer = buyer;
	}

	/** 
	 * Compares the artwork, with another given artwork return an integer represing greater than, smaller than, and equal
	 * @param work other work to compare to
	 * @return 0 if both works have the same price and name, 
	 * > 0 if this.work > work and < 0 if this.work < work.
	 */
	@Override
	public int compareTo(ArtWork work) {
		int comp = work.highestSoldValue() - this.highestSoldValue();
        
        if (comp == 0) 
			return this.name().compareTo(work.name());

        return comp;
    
	}
	
}
