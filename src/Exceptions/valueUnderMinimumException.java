package Exceptions;

public class valueUnderMinimumException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public valueUnderMinimumException() {
        super("\nValor proposto abaixo do valor minimo.");
    }
}
