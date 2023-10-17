package Exceptions;

public class hasNoWorksException extends Exception{
   
	private static final long serialVersionUID = 1L;

	public hasNoWorksException() {
        super("\nArtista sem obras.");
    }

}
