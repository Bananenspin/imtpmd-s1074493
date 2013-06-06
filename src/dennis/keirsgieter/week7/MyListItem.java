package dennis.keirsgieter.week7;

public class MyListItem {

	private int id;
	private String naam;
	
	public MyListItem(int id, String naam)
	{
		this.id = id;
		this.naam = naam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	
}
