package ArtAuctions;

import java.io.Serializable;
import Exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 *AuctionHouse entity. 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@capus.fct.unl.pt
 */
public interface AuctionHouse extends Serializable {
		
		/**
		 * Registers a new user(collector) in the Auction house
		 * @param login  user login to be added in the auction house 
		 * @param name  name of the user to be added in the auction house
		 * @param age  age of the user to be added in the auction house
		 * @param email  user email to be added in the auction house
		 * @throws userUnderAgeException  when the age is < 18
		 * @throws loginAlredyRegisteredException  when the login has already been registered in the auction house
		 */
		void addUser(String login, String name, int age, String email)
	        throws userUnderAgeException, loginAlredyRegisteredException;

		/**
		 * Registers a new artist into the system.
		 * @param login login of the artist to be registered into the auction house
		 * @param name name  of the artist to be registered in the auction house 
		 * @param artisticName the artistic name of the artist to be registered in the auction house
		 * @param age age of artist to be registered in the auction house
		 * @param email email of the artist to be registered in the auction house
		 * @throws userUnderAgeException when age < 18
		 * @throws loginAlredyRegisteredException when the login has already been registered in the auction house
		 */
	    void addArtist(String login, String name, String artisticName, int age, String email)
	        throws userUnderAgeException, loginAlredyRegisteredException;

	    /**
	     * Removes a user from the auction house
	     * @param login login of the user to be removed from the auction house
	     * @throws loginNotRegisteredException when the login is not registered in the auction house
	     */
	    void removeUser(String login)
	        throws loginNotRegisteredException, userHasBidsException, artistHasWorksInAuction;

	    /**
	     * Registers a new art work in the auction house 
	     * @param workId the id of the art work 
	     * @param authorLogin the login of the author
	     * @param year the year of the art work elaboration
	     * @param name the name of the art work
	     * @throws workIdAlreadyRegisteredException when the id of the artwork is already registered in the auction house
	     * @throws loginNotRegisteredException when the login of the author does not exist
	     * @throws notAnArtistException when the login is not from an artist
	     */
	    void addWork(String workId, String authorLogin, int year, String name)
	        throws workIdAlreadyRegisteredException, loginNotRegisteredException, notAnArtistException;

	    /**
	     * Consults the generic data about the given user
	     * @param login the login of the user 
	     * @return the user information
	     * @throws loginNotRegisteredException when the given user is not registered
	     */
	    UserReadOnly infoUser(String login)
	        throws loginNotRegisteredException;

	    /**
	     * Consults the information about a given artist
	     * @param login the login of the artist
	     * @return the information about the artist
	     * @throws loginNotRegisteredException when the given artist is not registered 
	     * @throws notAnArtistException when the given user is not an artist
	     */
	    ArtistReadOnly infoArtist(String login)
	        throws loginNotRegisteredException, notAnArtistException;

	    /**l
	     * Consults the information about a give work
	     * @param workId the work id 
	     * @return the information about the work
	     * @throws workIdNotRegisteredException when the work is not registered
	     */
	    ArtWorkReadOnly infoWork(String workId)
	        throws workIdNotRegisteredException;

	    /**
	     * Creates a new auction 
	     * @param auctionId the id of the auction
	     * @throws auctionIdAlreadyRegisteredException when the auction id is already registered
	     */
	    void createAuction(String auctionId)
	        throws auctionIdAlreadyRegisteredException;

	    /**
	     * Adds an artwork on a auction
	     * @param auctionId id of the auction 
	     * @param workId the work id
	     * @param minSellValue the minimum acceptable value of the artwork
	     * @throws auctionIdNotRegisteredException when the id of the auction is not registered
	     * @throws workIdNotRegisteredException when the id of the art work is not registered
	     */
	    void addWorkAuction(String auctionId, String workId, int minSellValue)
	        throws auctionIdNotRegisteredException, workIdNotRegisteredException;

	    /**
	     * Make a proposal to buy a artwork on an auction
	     * @param auctionId if of the auction
	     * @param workId id of the artwork
	     * @param login login of the user
	     * @param value the value of the proposal
	     * @throws valueUnderMinimumException when the value proposal is bellow the minimum sale value of the artwork
	     * @throws auctionIdNotRegisteredException when the id of the auction is not registered
	     * @throws loginNotRegisteredException when the login of the user is not registered
	     * @throws workNotInAuctionException
	     * @throws workIdNotRegisteredException when the id of the work is not registered 
	     */
	    void bid(String auctionId, String workId, String login, int value)
	        throws valueUnderMinimumException, auctionIdNotRegisteredException, loginNotRegisteredException, workNotInAuctionException;

	    /**
		 * Closes an open auction, and returns an Iterator with the art works in the now closed auction
	     * @param auctionId the auction id
	     * @return iterator with art works, in the closed auction
	     * @throws auctionIdNotRegisteredException when the id of the auction is not registered 
	     */
	    Iterator<ArtWorkReadOnly> closeAuction(String auctionId)
	        throws auctionIdNotRegisteredException;

	    /**
		 * Return an iterator with the art works in the given auction
	     * @param auctionId the auction id
	     * @return Iterator with the art works in the auction
	     * @throws auctionIdNotRegisteredException if the given auction id is not registered
	     * @throws noWorksAuctionException if the given auction id exists but has no works added
	     */
	    Iterator<ArtWorkReadOnly> listAuctionWorks(String auctionId)
	        throws auctionIdNotRegisteredException, noWorksAuctionException;

	    /**
		 * Return an iterator with the works of a given artist
	     * @param login login of the artist
	     * @return iterator with the artists works
	     * @throws loginNotRegisteredException if there is no user registered with the given login
	     * @throws notAnArtistException if the login is not registered to an artist
	     * @throws hasNoWorksException if the artist has no works 
	     */
	    Iterator<ArtWorkReadOnly> listArtistWorks(String login)
	        throws loginNotRegisteredException, notAnArtistException, hasNoWorksException;

	    /**
		 * Lists the bids of a work with the given workId, in an auction with the given auctionId
	     * @param auctionId id of the auction where the work is being auctioned
	     * @param workId id of the work of which the bids are going to be listed
	     * @return iterator with the bids of the work with the given id
	     * @throws auctionIdNotRegisteredException if there are no auctions with the given id registered
	     * @throws workNotInAuctionException if there is no work with the given id in the auction
	     * @throws workHasNoBidsException if there haven't been any bids placed on the work with the given id
	     */
	    Iterator<BidReadOnly> listBidsWork(String auctionId, String workId)
	        throws auctionIdNotRegisteredException, workNotInAuctionException, workHasNoBidsException;

	    /**
		 * Return an Iterator with the sold works, ordered by the value they were sold
	     * @return iterator of all registered works, ordered by their value
	     * @throws noSoldWorkdsException if no works has been sold
	     */
	    Iterator<ArtWorkReadOnly> listWorksByValue()
	        throws noSoldWorkdsException;

}
