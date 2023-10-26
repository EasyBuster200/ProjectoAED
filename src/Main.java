import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import ArtAuctions.AuctionHouse;
import ArtAuctions.AuctionHouseClass;
import ArtAuctions.Bid;
import ArtAuctions.ArtWork;
import ArtAuctions.Collector;
import ArtAuctions.Artist;
import dataStructures.Iterator;

public class Main {
    private static final String USER_REGISTERED = "\nRegisto de utilizador executado.";
    private static final String ARTIST_REGISTERED = "\nRegisto de artista executado.";
    private static final String USER_REMOVED = "\nRemocao de utilizador executada";
    private static final String WORK_REGISTERED = "\nRegisto de obra executado.";
    private static final String INFO_USER_BODY = "\n%s %s %d %s\n";
    private static final String INFO_ARTIST_BODY = "\n%s %s %s %d %s\n";
    private static final String INFO_WORK_BODY = "\n%s %s %d %d %s %s\n";
    private static final String AUCTION_REGISTERED = "\nRegisto de leilao executado";
    private static final String WORK_ADDED_AUCTION = "\nObra adicionada leilao.";
    private static final String BID_PLACED = "\nProposta aceite.";
    private static final String AUCTION_CLOSED = "\nLeilao encerrado.";
    private static final String AUCTION_CLOSED_BODY_SOLD = "\n%s %s %s %s %d\n";
    private static final String AUCTION_CLOSE_BODY_NOT_SOLD = "\n%s %s sem propostas de venda.";
    private static final String LIST_AUCTION_WORKS_BODY = "\n%s %s %d %d %s %s\n";
    private static final String LIST_ARTIST_WORKS_BODY = "\n%s %s %d %d\n";
    private static final String LIST_BIDS_BODY = "\n%s %s %d\n";
    private static final String LIST_WORKS_VALUE_BODY = "\n%s %s %d %d %s %s\n";
    private static final String QUIT_MSG = "\nObrigado. Ate a proxima.\n";
    
  
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
    		    case LIST_WORKS_BY_VALUE -> listWorksByValue(in,aH);
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

        } catch(Exception e) {
            System.out.println(e.getMessage());
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeUser(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();
            
            sys.removeUser(login);
            System.out.println(USER_REMOVED);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    private static void addWork(Scanner in, AuctionHouse sys) {
        try {
            String workId = in.next().strip();
            String authorLogin = in.next().strip();
            int year = in.nextInt();
            String name = in.nextLine();

            sys.addWork(workId, authorLogin, year, name);
            System.out.println(WORK_REGISTERED);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void infoUser(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();

            Collector c = sys.infoUser(login);
            System.out.printf(INFO_USER_BODY, c.login(), c.name(), c.age(), c.email());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void infoArtist(Scanner in, AuctionHouse sys) {
        try {
            String login = in.next().strip();

            Artist a = sys.infoArtist(login);
            System.out.printf(INFO_ARTIST_BODY, a.login(), a.name(), a.artisticName(), a.age(), a.email());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void infoWork(Scanner in, AuctionHouse sys) {
        try {
            String workId = in.next().strip();

            ArtWork w =  sys.infoWork(workId);
            System.out.printf(INFO_WORK_BODY, w.workId(), w.name(), w.year(), w.lastAuctionPrice(), w.authorLogin(), w.authorName());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();

            sys.createAuction(auctionId);
            System.out.println(AUCTION_REGISTERED);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addWorkToAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();
            String workId = in.next().strip();
            int minimumValue = in.nextInt();

            sys.addWorkAuction(auctionId, workId, minimumValue);
            System.out.println(WORK_ADDED_AUCTION);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void closeAuction(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.nextLine().strip();

            Iterator<ArtWork> it = sys.closeAuction(auctionId);
            System.out.println(AUCTION_CLOSED);

            while (it.hasNext()) {
                ArtWork a = it.next();

                if (a.beenSold())
                    System.out.printf(AUCTION_CLOSED_BODY_SOLD, a.workId(), a.name(), a.buyerLogin(), a.buyerName(), a.lastAuctionPrice());

                else    
                    System.out.printf(AUCTION_CLOSE_BODY_NOT_SOLD, a.workId(), a.name());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAuctionWorks(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.nextLine().strip();

            Iterator<ArtWork> it = sys.listAuctionWorks(auctionId);

            while(it.hasNext()) {
                ArtWork a = it.next();

                System.out.printf(LIST_AUCTION_WORKS_BODY, a.workId(), a.name(), a.year(), a.highestSoldValue(), a.authorLogin(), a.authorName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listArtistWorks(Scanner in, AuctionHouse sys) {
        try {
            String login = in.nextLine().strip();

            Iterator<ArtWork> it = sys.listArtistWorks(login);

            while (it.hasNext()) {
                ArtWork a = it.next();

                System.out.printf(LIST_ARTIST_WORKS_BODY, a.workId(), a.name(), a.year(), a.highestSoldValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   private static void listBidsWork(Scanner in, AuctionHouse sys) {
        try {
            String auctionId = in.next().strip();
            String workId = in.nextLine();
            Iterator<Bid> it = sys.listBidsWork(auctionId, workId);

            while (it.hasNext()) {
                Bid b = it.next();
                System.out.printf(LIST_BIDS_BODY, b.biddersLogin(), b.biddersName(), b.bidValue());
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
   			System.out.println("input out put exception");
   		}catch(ClassNotFoundException e) {
   			System.out.println("class not found Exception");
   		}
   		
   		return auctionHouse;
	}
   
   private static void save(AuctionHouse aH) {
		try {
			System.out.println("here");
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
			file.writeObject(aH);
			file.flush();
			file.close();
		}catch(IOException e) {
			//System.out.println("input output exception");
		}
	}
   
   private static void listWorksByValue(Scanner in, AuctionHouse aH) {
		// TODO Auto-generated method stub
	}
   
   
   
}
//TODO: The save method is saving the AuctionHouseClass object, but its not saving anything inside it?
//TODO: Review and finish comments on 
//TODO? When an auction is closed, if an artWork is in another auction what happens? Does it get sold twice? or to the highest bidder of both auctions? or just gets removed from any other auction? Tecnicamente acaba por n interessar, pois n temos de saber as obras q uma dada pessoa tem a qqlr instante.
//TODO! When running the third add in the first test something is going wrong, literally nothing happens