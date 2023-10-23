package ArtAuctions;

import java.io.Serializable;

public interface Bid extends Serializable {

    /**
     * Return the value of the bid
     * @return value of the bid 
     */
    int bidValue();

    /**
     * Returns the name of the person who placed the bid
     * @return name of the bidder
     */
    String biddersName();

    /**
     * Return the login of the person who placed the bid
     * @return login for the bidder
     */
    String biddersLogin();

}
