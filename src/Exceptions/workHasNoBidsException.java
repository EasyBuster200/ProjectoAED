package Exceptions;

public class workHasNoBidsException extends Exception{
   
	private static final long serialVersionUID = 1L;

	public workHasNoBidsException() {
        super("Obra sem propostas.");
    }
}
