package Exceptions;

public class userUnderAgeException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public userUnderAgeException() {
        super("Idade inferior a 18 anos.");
    }
}
