import java.util.Scanner;

import Model.Joueur;
import Model.Plateau;
import View.Accueil;

public class Demo {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int choice;
		do {
			System.out.println("---------- JOUER A LA BATTAIL NAVALE ! ----------\n" + "Pour jouez en console (Tapez 1),pour jouez en GUI (Tapez 2)");
			choice = scanner.nextInt();
		}while(choice != 1 && choice != 2);
		
		if(choice == 2) {
			new Accueil();
		}
		else if (choice == 1) {
		int x,y; //coordonnées des bateaux qu'on va ajouter
		boolean horizontale;
		Joueur j1 = new Joueur("Mahdi", false);
		Joueur j2 = new Joueur("Bot", true);
		Plateau p = new Plateau(j1, j2); //plateau initialisé
		p.creerBateau(); //creation des bateaux
		p.ajouterBateaux (p.getListeBateau2(), p.getPlateau2()); //ajout des bateaux aleatoires pour 2eme plateau (Bot)
		System.out.print(p); //affichage du plateau
		//ajout personnalisé des bateaux
		for(int i=0; i<5; i++) {
			System.out.println("Où vous voulez placer le bateau "+ i +"? Tapez entre 0 et 8");
			System.out.println("X ?");
			x = scanner.nextInt();
			System.out.println("Y ?");
			y = scanner.nextInt();

			if(i%2==0) horizontale = true;
			else horizontale = false;
			String h = horizontale? "Horizontale" : "Verticale";
			//verifier si on peut placer le bateau a la case x y
			//1er condition : bounderies, 2eme : chevauchement
			while((x<0 || x>p.getROW() || y<0 || y>p.getCOL()) || (p.occuperPlateau(p.getListeBateau1()[i].getTaille(), x, y, h, p.getPlateau(j1)))) {
				System.out.println("Mauvais Coordonnées! Ressaisissez les coordonnees");
				System.out.println("X ?");
				x = scanner.nextInt();
				System.out.println("Y ?");
				y = scanner.nextInt();
			}
			p.placerBateau(p.getPlateau(j1), x, y, p.getListeBateau1()[i].getTaille(), horizontale);
		}
		System.out.println("---------------------------------DÉBUT DU JEU-----------------------------------");
		while(!p.partieFini()) {
			System.out.print("affichage\n" + p);
			System.out.println("C'est le tour de " + p.getJoueur1());
			System.out.println("Le tir ?");
			System.out.println("X ?");
			x = scanner.nextInt();
			System.out.println("Y ?");
			y = scanner.nextInt();
			p.bateauTouche(p.getPlateau(j2), (y*p.getROW()) + x);
			System.out.println("C'est le tour " + p.getJoueur2());
			p.jouerAlea(p.getPlateau(j1));
		}
		System.out.print("affichage\n" + p); //affichage du plateau
		System.out.println("Le vinqueur est : " + p.vainqueur());
		}
	}
}
