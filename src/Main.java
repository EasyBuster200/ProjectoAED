import java.util.Scanner;
import ArtAuctions.AuctionHouse;
import ArtAuctions.Bid;
import ArtAuctions.ArtWork;
import ArtAuctions.Collector;
import ArtAuctions.Artist;
import dataStructures.Iterator;

public class Main {
    private static final String USER_REGISTERED = "Registo de utilizador executado.";
    private static final String ARTIST_REGISTERED = "Registo de artista executado.";
    private static final String USER_REMOVED = "Remocao de utilizador executada";
    private static final String WORK_REGISTERED = "Registo de obra executado.";
    private static final String INFO_USER_BODY = "%s %s %d %s\n";
    private static final String INFO_ARTIST_BODY = "%s %s %s %d %s\n";
    private static final String INFO_WORK_BODY = "%s %s %d %d %s %s\n";
    private static final String AUCTION_REGISTERED = "Registo de leilao executado";
    private static final String WORK_ADDED_AUCTION = "Obra adicionada leilao.";
    private static final String BID_PLACED = "Proposta aceite.";
    private static final String AUCTION_CLOSED = "Leilao encerrado.";
    private static final String AUCTION_CLOSED_BODY_SOLD = "%s %s %s %s %d\n";
    private static final String AUCTION_CLOSE_BODY_NOT_SOLD = "%s %s sem propostas de venda.";
    private static final String LIST_AUCTION_WORKS_BODY = "%s %s %d %d %s %s\n";
    private static final String LIST_ARTIST_WORKS_BODY = "%s %s %d %d\n";
    private static final String LIST_BIDS_BODY = "%s %s %d\n";
    private static final String LIST_WORKS_VALUE_BODY = "%s %s %d %d %s %s\n";
    private static final String EXIT_MSG = "Obrigado. Ate a proxima.";

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    private static void newUser(Scanner in, AuctionHouse sys) {
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

    private static void newArtist(Scanner in, AuctionHouse sys) {
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
                    System.out.printf(AUCTION_CLOSED_BODY_SOLD, a.workId(), a.name(), a.buyerLogin(), a.buyerName(), a.lastAuctionPrice()); //TODO: Depends if the last auction price was the one it sold for

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
}