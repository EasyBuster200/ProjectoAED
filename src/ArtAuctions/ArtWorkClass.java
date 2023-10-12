package ArtAuctions;

public class ArtWorkClass implements ArtWork {

	protected String workId,name;
	protected int year, lastAuctionPrice;
	protected boolean beenSold;
	protected Artist author;
	protected User buyer;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtWorkClass(String workId, String name, int year, Artist author) {
		// TODO Auto-generated constructor stub
		this.workId = workId;
		this.name = name;
		this.year = year;
		this.author = author;
		this.lastAuctionPrice = 0;
		this.beenSold = false;
	}

	@Override
	public String workId() {
		// TODO Auto-generated method stub
		return this.workId;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int year() {
		// TODO Auto-generated method stub
		return this.year;
	}

	@Override
	public int lastAuctionPrice() {
		// TODO Auto-generated method stub
		return this.lastAuctionPrice;
	}

	@Override
	public void newAuctionPrice(int auctionPrice) {
		// TODO Auto-generated method stub
		this.lastAuctionPrice = auctionPrice;
		
	}

	@Override
	public String authorLogin() {
		// TODO Auto-generated method stub
		return this.author.login();
	}

	@Override
	public String authorName() {
		// TODO Auto-generated method stub
		return this.author.name();
	}

	@Override
	public boolean beenSold() {
		// TODO Auto-generated method stub
		return this.beenSold;
	}

	@Override
	public String buyerLogin() {
		// TODO Auto-generated method stub
		return this.buyer.login();
	}

	@Override
	public String buyerName() {
		// TODO Auto-generated method stub
		return this.buyer.name();
	}

}
