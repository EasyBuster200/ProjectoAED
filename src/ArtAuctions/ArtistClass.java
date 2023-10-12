package ArtAuctions;

public class ArtistClass extends Collector implements Artist {
	
	protected String artisticName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		// TODO Auto-generated constructor stub
		super(login,name,age,email);
		this.artisticName = artisticName;
	}

	@Override
	public String artisticName() {
		// TODO Auto-generated method stub
		return this.artisticName ;
	}

}
