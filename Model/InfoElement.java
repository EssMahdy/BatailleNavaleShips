package Model;

public class InfoElement {
	private int typeDeCase = 0;
	private String id = "99"; //Identifiant de la case
	private int cptTirs = 99; //Compteur de tirs (par défaut, 99)
	
	public InfoElement() {
		
	}
	/**
	 * Méthode permettant de récupérer le type de la case
	 * @return type de la case
	 */
	public int getTypeCase() {
		return typeDeCase; //1 case occupé, 0 case non occupé
	}
	/**
	 * Méthode permettant de définir le type de la case
	 */
	public void setTypeCase(int typeDeCase) {
		this.typeDeCase = typeDeCase;
	}
	/**
	 * Méthode permettant de récupérer l'identifiant de la case
	 * @return l'identifiant de la case
	 */
	public String getId() {
		return id;
	}
	/**
	 * Méthode permettant de définir l'identifiant de la case
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Méthode permettant de récupérer le compteur de tirs
	 * @return le compteur de tirs
	 */
	public int getCompteur() {
		return cptTirs;
	}
	/**
	 * Méthode permettant de définir le compteur de tirs
	 * @param cpt
	 */
	public void setCompteur(Integer cpt) {
		this.cptTirs = cpt;
	}
	
	public String toString() {
		return " " + typeDeCase +" ";
	}
}
