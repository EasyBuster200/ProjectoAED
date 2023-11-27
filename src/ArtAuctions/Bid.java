package ArtAuctions;

import java.io.Serializable;

/**
 * Bid Entity 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface Bid extends BidReadOnly, Serializable {

    /**
     * Sets the bid to closed, and removes it from the bidder
     */
    void bidClosed();

}
