package Exceptions;

public class noWorksAuctionException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public noWorksAuctionException() {
        super("Leilao sem obras.");
    }

}
