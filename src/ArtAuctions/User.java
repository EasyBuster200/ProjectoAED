package ArtAuctions;

import java.io.Serializable;

public interface User extends Serializable {

	/**
	 * Returns the login of the user.
	 * @return login.
	 */
	String login();
	
	/**
	 * Return the name of the user.
	 * @return name.
	 */
	String name();
	
	/**
	 * Return the age of the user.
	 * @return
	 */
	int age();
	
	/**
	 * Return the email of the user.
	 * @return email.
	 */
	String email();
	
}
