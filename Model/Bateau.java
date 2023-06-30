package Model;

import java.util.Arrays;

public class Bateau {
	private int[] bateau;
	private int taille;
	private String id;
	public static int compte = 1;
	private boolean couler;
	
	public Bateau(int taille) {
		this.taille = taille;
		this.bateau = new int[taille];
		this.id = String.valueOf(compte);
		this.couler = false;
		Bateau.compte += 1;
	}
	
	public boolean getCouler() {
		return this.couler;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public String getID() {
		return id;
	}
	
	public void setCouler(boolean couler) {
		this.couler = couler;
	}
	
	@Override
	public String toString() {
		return "Bateau[" + "le bateau" + Arrays.toString(bateau) + "taille est " + taille + "id est " + id + '\'' + ']';
		
	}
}
