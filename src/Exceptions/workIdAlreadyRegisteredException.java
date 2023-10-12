package Exceptions;

public class workIdAlreadyRegisteredException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public workIdAlreadyRegisteredException() {
        super("Obra existente");
    }
}
