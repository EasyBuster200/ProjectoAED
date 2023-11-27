package ArtAuctions;

public interface BidReadOnly {
    
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

    /**
     * Return the user object of the person who placed the bid
     * @return the user who placed the bid
     */
    User bidder();
}
