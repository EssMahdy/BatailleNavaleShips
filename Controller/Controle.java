package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.InfoElement;
import Model.Joueur;
import Model.Plateau;
import View.ButtonElement;
import View.GrilleGUI;

public class Controle implements ActionListener {

	private Plateau plateau;
	private GrilleGUI grille;
	private Joueur joueur; 
	private boolean debut;
	
	public Controle(Plateau plateau, GrilleGUI grille, Joueur joueur, boolean debut) {
		this.plateau = plateau;
		this.grille = grille;
		this.joueur = joueur;
		this.debut = debut;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		InfoElement info = new InfoElement();
		ButtonElement clickedBox = (ButtonElement) e.getSource();
		clickedBox.setInfoElement(info);
		if(!debut) //dépot des bateaux au debut du jeu
		{
			if(grille.getCptNbrBateauxPlacer() < grille.getNbrBateaux()){

				clickedBox.setEnabled(false);

				plateau.remplissageAlea(ButtonElement.getInfoElements(grille.getInfoJButtons()), clickedBox.getInfoElement());
				grille.setCptNbrBateauxPlacer(grille.getCptNbrBateauxPlacer()+1);

				grille.update(); //changer les couleurs des cases
			}
			else { //si le compteur > nbBateau alors on arrête le placement
				grille.setAllBoatPlaces(true);
			}
		}
		else
		{
			if (clickedBox.getInfoElement().getTypeCase() == 0) {

				miseAJourListeBouton(clickedBox);
				clickedBox.getInfoElement().setTypeCase(1);

			} else if (clickedBox.getInfoElement().getTypeCase() == 1) {

				miseAJourListeBouton(clickedBox);
				clickedBox.getInfoElement().setTypeCase(2);

			}

			grille.getPlateau().bateauTouche(ButtonElement.getInfoElements(grille.getInfoJButtons()), clickedBox.getInfoElement().getCompteur());

			if (grille.getPlateau().getJoueurCourant() == this.joueur) {
				grille.getPlateau().setJoueurCourant(grille.getPlateau().getJoueur2());
			} else {
				grille.getPlateau().setJoueurCourant(this.joueur);
			}
		}

		grille.getPlateau().bateauCouler(grille.getPlateau().getPlateauAdversaire(this.joueur), clickedBox.getInfoElement());
        grille.update();
		plateau.fireChangement();
		
	}
	
	public void miseAJourListeBouton(ButtonElement jButton) {
        grille.getDisableButton().add(jButton);
        grille.getEnableButton().remove(jButton);
    }
}
