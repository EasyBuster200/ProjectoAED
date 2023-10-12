package Exceptions;

public class loginAlredyRegisteredException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public loginAlredyRegisteredException() {
        super("Utilizador existente.");
    }
}
