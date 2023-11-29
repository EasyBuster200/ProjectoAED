import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import ArtAuctions.*;
import Exceptions.*;
import dataStructures.Iterator;

/**
 * Main class for the management the interpretation and the output of commands
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class Main {

    /**
     * Successful output messages
     */
    private static final String USER_REGISTERED = "\nRegisto de utilizador executado.";
    private static final String ARTIST_REGISTERED = "\nRegisto de artista executado.";
    private static final String USER_REMOVED = "\nRemocao de utilizador executada.";
    private static final String WORK_REGISTERED = "\nRegisto de obra executado.";
    private static final String INFO_USER_BODY = "\n%s %s %d %s\n";
    private static final String INFO_ARTIST_BODY = "\n%s %s %s %d %s\n";
    private static final String INFO_WORK_BODY = "\n%s %s %d %d %s %s\n";
    private static final String AUCTION_REGISTERED = "\nRegisto de leilao executado.";
    private static final String WORK_ADDED_AUCTION = "\nObra adicionada ao leilao.";
    private static final String BID_PLACED = "\nProposta aceite.";
    private static final String AUCTION_CLOSED = "\nLeilao encerrado.";
    private static final String AUCTION_CLOSED_BODY_SOLD = "\n%s %s %s %s %d";
    private static final String AUCTION_CLOSE_BODY_NOT_SOLD = "\n%s %s sem propostas de venda.";
    private static final String LIST_AUCTION_WORKS_BODY = "\n%s %s %d %d %s %s";
    private static final String LIST_ARTIST_WORKS_BODY = "%s %s %d %d\n";
    private static final String LIST_BIDS_BODY = "%s %s %d\n";
    private static final String LIST_WORKS_VALUE_BODY = "%s %s %d %d %s %s\n";
    private static final String QUIT_MSG = "Obrigado. Ate a proxima.";
    
    /**
     * Error messages
     */
    private static final String UNDER_AGE = "\nIdade inferior a 18 anos.";
    private static final String ALREADY_REGISTERED = "\nUtilizador existente.";
    private static final String NOT_REGISTERED = "\nUtilizador inexistente.";
    private static final String HAS_OPEN_BIDS = "\nUtilizador com propostas submetidas.";
    private static final String WORKS_IN_AUCTION = "\nArtista com obras em leilao.";
    private static final String WORK_ID_UNAVAILABLE = "\nObra existente.";
    private static final String NOT_AN_ARTIST = "\nArtista inexistente.";
    private static final String WORK_ID_UNREGISTERED = "\nObra inexistente.";
    private static final String AUCTION_ID_UNAVAILABLE = "\nLeilao existente.";
    private static final String AUCTION_ID_UNREGISTERED = "\nLeilao inexistente.";
    private static final String WORK_NOT_IN_AUCTION = "\nObra inexistente no leilao.";
    private static final String VALUE_UNDER_MINIMUM = "\nValor proposto abaixo do valor minimo.";
    private static final String AUCTION_HAS_NO_WORKS = "\nLeilao sem obras.";
    private static final String ARTIST_HAS_NO_WORKS = "\nArtista sem obras.";
    private static final String NO_SOLD_WORKS = "\nNao existem obras ja vendidas em leilao.";
    private static final String WORK_HAS_NO_BIDS = "\nObra sem propostas.";

    /**
     * Commands
     */
    private static final String ADD_USER = "adduser";
    private static final String ADD_ARTIST = "addartist";
    private static final String REMOVE_USER = "removeuser";
    private static final String ADD_WORK = "addwork";
    private static final String INFO_USER = "infouser";
    private static final String INFO_ARTIST = "infoartist";
    private static final String INFO_WORK = "infowork";
    private static final String CREATE_AUCTION = "createauction";
    private static final String ADD_WORK_AUCTION = "addworkauction";
    private static final String BID = "bid";
    private static final String CLOSE_AUCTION = "closeauction";
    private static final String LIST_AUCTION_WORKS = "listauctionworks";
    private static final String LIST_ARTIST_WORK = "listartistworks";
    private static final String LIST_BIDS_WORK = "listbidswork";
    private static final String LIST_WORKS_BY_VALUE = "listworksbyvalue";
    private static final String QUIT = "quit";
    
    /**
     * String for the name of the file, where all the data will be stored.
     */
    private static final String DATA_FILE = "storedAuctionHouse.dat";
    
    public static void main(String[] args) throws Exception {
    	
    	Scanner in = new Scanner(System.in);
    	AuctionHouse aH = load();
    	String cmd;

        do {
    		cmd = in.next().toLowerCase();
    		
            switch(cmd) {
    		    case ADD_USER -> addUser(in,aH);
    		    case ADD_ARTIST -> addArtist(in,aH);
    		    case REMOVE_USER -> removeUser(in,aH);
    		    case ADD_WORK -> addWork(in,aH);
    		    case INFO_USER -> infoUser(in,aH);
    		    case INFO_ARTIST -> infoArtist(in,aH);
    		    case INFO_WORK -> infoWork(in,aH);
    		    case CREATE_AUCTION -> createAuction(in,aH);
    		    case ADD_WORK_AUCTION -> addWorkToAuction(in,aH);
    		    case BID -> bid(in,aH);
    		    case CLOSE_AUCTION -> closeAuction(in,aH);
    		    case LIST_AUCTION_WORKS -> listAuctionWorks(in,aH);
                case LIST_ARTIST_WORK -> listArtistWorks(in, aH);
    		    case LIST_BIDS_WORK -> listBidsWork(in,aH);
    		    case LIST_WORKS_BY_VALUE -> listWorksByValue(aH);
    		}

    		System.out.println();

    	} while(!cmd.equals(QUIT));

    	save(aH);
        quit();
        in.close();
    }


	private static void addUser(Scanner in, AuctionHouse sys) {
        try {
             String login = in.next().strip();
             String name = in.nextLine().strip();
             int age = in.nextInt();
             String email = in.nextLine().strip();

             sys.addUser(login, name, age, email);
             System.out.println(USER_REGISTERED);

        } catch(userUnderAgeException e) {
            System.out.println(UNDER_AGE);
        } catch (loginAlredyRegisteredException e) {
            System.out.println(ALREADY_REGISTERED);
        }
    }

    private static void addArtist(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();
            String name = in.nextLine().strip();
            String artisticName = in.nextLine().strip();
            int age = in.nextInt();
            String email = in.nextLine().strip();

            sys.addArtist(login, name, artisticName, age, email);
            System.out.println(ARTIST_REGISTERED);

        } catch (userUnderAgeException e) {
            System.out.println(UNDER_AGE);
        } catch(loginAlredyRegisteredException e) {
            System.out.println(ALREADY_REGISTERED);
        }
    }

    private static void removeUser(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();
            
            sys.removeUser(login);
            System.out.println(USER_REMOVED);

        } catch (loginNotRegisteredException e) {
           System.out.println(NOT_REGISTERED);
        } catch(userHasBidsException e) {
            System.out.println(HAS_OPEN_BIDS);
        } catch(artistHasWorksInAuction e) {
            System.out.println(WORKS_IN_AUCTION);
        }
    }

    private static void addWork(Scanner in, AuctionHouse sys) {
        try {
            String workId = in.next().strip();
            String authorLogin = in.next().strip();
            int year = in.nextInt();
            String name = in.nextLine().strip();

            sys.addWork(workId, authorLogin, year, name);
            System.out.println(WORK_REGISTERED);

        } catch (workIdAlreadyRegisteredException e) {
            System.out.println(WORK_ID_UNAVAILABLE);
        } catch (loginNotRegisteredException e) {
            System.out.println(NOT_REGISTERED);
        } catch (notAnArtistException e) {
            System.out.println(NOT_AN_ARTIST);
        }
    }

    private static void infoUser(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();

            UserReadOnly c = sys.infoUser(login);
            System.out.printf(INFO_USER_BODY, c.login(), c.name(), c.age(), c.email());

        } catch (loginNotRegisteredException e) {
            System.out.println(NOT_REGISTERED);
        }
    }

    private static void infoArtist(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();

            ArtistReadOnly a = sys.infoArtist(login);
            System.out.printf(INFO_ARTIST_BODY, a.login(), a.name(), a.artisticName(), a.age(), a.email());

        } catch (loginNotRegisteredException e) {
            System.out.println(NOT_REGISTERED);
        } catch (notAnArtistException e) {
            System.out.println(NOT_AN_ARTIST);
        }
    }

    private static void infoWork(Scanner in, AuctionHouse sys) {
        try {
            String workId = in.next().strip();

            ArtWorkReadOnly w =  sys.infoWork(workId);
            System.out.printf(INFO_WORK_BODY, w.workId(), w.name(), w.year(), w.highestSoldValue(), w.authorLogin(), w.authorName());

        } catch (workIdNotRegisteredException e) {
            System.out.println(WORK_ID_UNREGISTERED);
        }
    }

    private static void createAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();

            sys.createAuction(auctionId);
            System.out.println(AUCTION_REGISTERED);

        } catch (auctionIdAlreadyRegisteredException e) {
            System.out.println(AUCTION_ID_UNAVAILABLE);
        }
    }

    private static void addWorkToAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();
            String workId = in.next().strip();
            int minimumValue = in.nextInt();

            sys.addWorkAuction(auctionId, workId, minimumValue);
            System.out.println(WORK_ADDED_AUCTION);
            
        } catch (auctionIdNotRegisteredException e) {
            System.out.println(AUCTION_ID_UNREGISTERED);
        } catch (workIdNotRegisteredException e) {
            System.out.println(WORK_ID_UNREGISTERED);
        }
    }

    private static void bid(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();
            String workId = in.next().strip();
            String login = in.next().strip();
            int valor = in.nextInt();

            sys.bid(auctionId, workId, login, valor);
            System.out.println(BID_PLACED);

        } catch (valueUnderMinimumException e) {
            System.out.println(VALUE_UNDER_MINIMUM);
        } catch (auctionIdNotRegisteredException e) {
            System.out.println(AUCTION_ID_UNREGISTERED);
        } catch (loginNotRegisteredException e) {
            System.out.println(NOT_REGISTERED);
        } catch (workNotInAuctionException e ) {
            System.out.println(WORK_NOT_IN_AUCTION);
        }
    }

    private static void closeAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.nextLine().strip();

            Iterator<ArtWorkReadOnly> it = sys.closeAuction(auctionId);
            System.out.print(AUCTION_CLOSED);

            while (it.hasNext()) {
                ArtWorkReadOnly a = it.next();

                if (a.getBuyer() != null)
                    System.out.printf(AUCTION_CLOSED_BODY_SOLD, a.workId(), a.name(), a.buyerLogin(), a.buyerName(), a.lastAuctionPrice());

                else    
                    System.out.printf(AUCTION_CLOSE_BODY_NOT_SOLD, a.workId(), a.name());
            }

            System.out.println();

        } catch (auctionIdNotRegisteredException e) {
            System.out.println(AUCTION_ID_UNREGISTERED);
        }
    }

    private static void listAuctionWorks(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.nextLine().strip();

            Iterator<ArtWorkReadOnly> it = sys.listAuctionWorks(auctionId);

            while(it.hasNext()) {
                ArtWorkReadOnly a = it.next();

                System.out.printf(LIST_AUCTION_WORKS_BODY, a.workId(), a.name(), a.year(), a.highestSoldValue(), a.authorLogin(), a.authorName());
            }
            
            System.out.println();
        } catch (auctionIdNotRegisteredException e) {
            System.out.println(AUCTION_ID_UNREGISTERED);
        } catch (noWorksAuctionException e) {
            System.out.println(AUCTION_HAS_NO_WORKS);
        }
    }

    private static void listArtistWorks(Scanner in, AuctionHouse sys) {
        try {
            String login = in.nextLine().strip();

            Iterator<ArtWorkReadOnly> it = sys.listArtistWorks(login);
            
            System.out.println();

            while (it.hasNext()) {
                ArtWorkReadOnly a = it.next();

                System.out.printf(LIST_ARTIST_WORKS_BODY, a.workId(), a.name(), a.year(), a.highestSoldValue());
            }

        } catch (loginNotRegisteredException e) {
            System.out.println(NOT_REGISTERED);
        } catch (notAnArtistException e) {
            System.out.println(NOT_AN_ARTIST);
        } catch (hasNoWorksException e) {
            System.out.println(ARTIST_HAS_NO_WORKS);
        }
    }

    private static void listBidsWork(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();
            String workId = in.nextLine().strip();
            Iterator<BidReadOnly> it = sys.listBidsWork(auctionId, workId);
            System.out.println();

            while (it.hasNext()) {
                BidReadOnly b = it.next();
                System.out.printf(LIST_BIDS_BODY, b.biddersLogin(), b.biddersName(), b.bidValue());
            }
            
        } catch (auctionIdNotRegisteredException e) {
            System.out.println(AUCTION_ID_UNREGISTERED);
        } catch (workNotInAuctionException e) {
            System.out.println(WORK_NOT_IN_AUCTION);
        } catch (workHasNoBidsException e) {
            System.out.println(WORK_HAS_NO_BIDS);
        }
    }
    
    private static void listWorksByValue(AuctionHouse aH) {
         try {
             Iterator<ArtWorkReadOnly> it = aH.listWorksByValue();
             boolean beenSold = true;
             System.out.println();
             while (it.hasNext() && beenSold) {
                ArtWorkReadOnly current = it.next();
                 
                if (current.highestSoldValue() == 0)
                    beenSold = false; //Since the it is ordered, once the first artWork with value of 0 is found all the others after will also be at 0.
                else 
                    System.out.printf(LIST_WORKS_VALUE_BODY, current.workId(), current.name(), current.year(), current.highestSoldValue(), current.authorLogin(), current.authorName());
             }
 
         } catch (noSoldWorkdsException e) {
            System.out.println(NO_SOLD_WORKS);
         }
    }

    private static void quit() {
	   System.out.println(QUIT_MSG);
    }
   
    private static AuctionHouse load() {
   		AuctionHouse auctionHouse = new AuctionHouseClass();
		
   		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(DATA_FILE));
			auctionHouse = (AuctionHouseClass) file.readObject();
            file.close();
			return auctionHouse;
   		}catch(IOException e) {
   			//System.out.println("input out put exception");
   		}catch(ClassNotFoundException e) {
   			//System.out.println("class not found Exception");
   		}
   		
   		return auctionHouse;
	}
   
    private static void save(AuctionHouse aH) {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
			file.writeObject(aH);
			file.flush();
			file.close();
		}catch(IOException e) {
			//System.out.println("input output exception");
		}
	}
   

}
//TODO: Follow up with the teacher on my email, or emmail Toninho