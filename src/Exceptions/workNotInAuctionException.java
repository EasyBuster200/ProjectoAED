package Exceptions;

public class workNotInAuctionException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public workNotInAuctionException() {
        super("\nObra inexistente no leilao");
    }
}
