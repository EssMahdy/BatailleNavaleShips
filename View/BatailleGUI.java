package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import Model.Plateau;

public class BatailleGUI extends JFrame {

		private GrilleGUI pane1,pane2;
		private Plateau plateau;


	    public BatailleGUI(Plateau plateau) {


	        super("Bataille navale"); //Titre de la fenetre
	        this.setSize(new Dimension(800,800));

	        /* Les deux plateaux, récupérer quel joueur on veux pour le jeu */
			GridLayout layout = new GridLayout(0,2,80,20);
	        this.setLayout(layout);

	        pane1 = new GrilleGUI(plateau,plateau.getJoueur1());
			pane2 = new GrilleGUI(plateau,plateau.getJoueur2());

			this.add(pane1);
			this.add(pane2);
	      	this.setLocationRelativeTo(null); //La fenetre pop au milleu de l'écran
	        this.setVisible(true);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pour que la fenetre se ferme quand on appuie sur la croix
	    }

	    public static void main(String[] args) {
	    	new Accueil();
	    }
}
