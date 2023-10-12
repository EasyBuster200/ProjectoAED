package Exceptions;

public class loginNotRegisteredException extends Exception{
   
	private static final long serialVersionUID = 1L;

	public loginNotRegisteredException() {
        super("Utilizador inixistente");
    }
}
