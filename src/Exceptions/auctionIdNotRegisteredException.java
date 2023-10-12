package Exceptions;

public class auctionIdNotRegisteredException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public auctionIdNotRegisteredException() {
        super("Leilao inexistente.");
    }
}
