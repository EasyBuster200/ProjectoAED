package Exceptions;

public class workIdNotRegisteredException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public workIdNotRegisteredException() {
        super("Obra inexistente.");
    }

}
