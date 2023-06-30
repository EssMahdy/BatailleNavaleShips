package Model;

public class Joueur {
	
	private String name;
	private Boolean humain;

	public Joueur(String name, Boolean humain) {
		this.name = name;
		this.humain = humain;
	}

	public Boolean getHumain() {
		return humain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
