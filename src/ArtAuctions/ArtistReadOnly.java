package ArtAuctions;

/**
 * Interface representing a read-only view of an Artist object 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface ArtistReadOnly extends UserReadOnly {

    /**
	 * Returns the artistic name of the artist.
	 * @return the artistic name
	 */
	String artisticName();
    
}
