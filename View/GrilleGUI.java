package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Controller.Controle;
import Controller.EcouteurModele;
import Controller.ModeleEcoutable;
import Model.Joueur;
import Model.Plateau;

public class GrilleGUI extends JPanel implements EcouteurModele  {
	
	private Plateau plateau;
	private ButtonElement[][] infoJButtons;
	
	private Joueur joueur;

    private ArrayList<ButtonElement> disableButton;
    private ArrayList<ButtonElement> enableButton;

    private Integer nbrBateaux;
    private Integer cptNbrBateauxPlacer;
    private Boolean createdHumanGrid;
    private Boolean allBoatPlaces;
	private Object scanner;
    
    public GrilleGUI(Plateau plateau, Joueur joueur) {
        this.createdHumanGrid = false;
        this.allBoatPlaces = false;
        this.plateau = plateau;
        this.joueur = joueur;
        this.cptNbrBateauxPlacer = 0;
        this.nbrBateaux = 5; // pour le placement
        this.infoJButtons = this.createButtons();
        disableButton = new ArrayList<>();
        enableButton = new ArrayList<>();

        createGrid();

        this.plateau.ajoutEcouteur(this); // On ajoute l'écouteur ICI
        this.setLayout(new GridLayout(this.plateau.getROW(), this.plateau.getCOL()));
    }
    
	public ButtonElement[][] createButtons() {
    	ButtonElement[][] resultat = new ButtonElement[this.plateau.getROW()][this.plateau.getCOL()];
    	for (int i = 0; i < this.plateau.getROW(); i++) {
            for (int j = 0; j < this.plateau.getCOL(); j++) {
            	resultat[i][j] = new ButtonElement(plateau.getPlateauAdversaire(joueur)[i][j]); // Car on joue sur la plateau du joueur adverse
            }
    	}
    	return resultat;
    }
    
    public void createGrid() {
        for (int i = 0; i < this.plateau.getROW(); i++) {
            for (int j = 0; j < this.plateau.getCOL(); j++) {
                (infoJButtons[i][j]).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                this.add(infoJButtons[i][j]);
                if (!this.getAllBoatPlaces()) {
                    plateau.reinitialiserPlateau(plateau.getPlateauAdversaire(joueur));
                    this.setAllBoatPlaces(false);
                    infoJButtons[i][j].addActionListener(new Controle(plateau, this, joueur, false));
                    infoJButtons[i][j].setEnabled(true);
                    //Pour que le joueur humain puisse placer ses bateaux
                }
            }
        }
        update();
    }
    
    public void createGridClickable() {
        for (int i = 0; i < this.plateau.getROW(); i++) {
            for (int j = 0; j < this.plateau.getCOL(); j++) {
                infoJButtons[i][j].addActionListener(new Controle(plateau, this, joueur, true));
                infoJButtons[i][j].setEnabled(true);
                enableButton.add(infoJButtons[i][j]);
                createdHumanGrid = true;
            }
        }
        update();
    }
    
    public void activeButton() {
        for (ButtonElement e : enableButton) {
            e.setEnabled(true);
        }
    }
    
    public Boolean aSonTour() {
        if (plateau.getJoueurCourant() == joueur) {
            return true;
        } else {
            return false;
        }
    }
    
    public void update() {
        for (int i = 0; i < this.plateau.getROW(); i++) {
            for (int j = 0; j < this.plateau.getCOL(); j++) {
                if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 0) {
                    this.infoJButtons[i][j].setBackground(new Color(0, 70, 128)); //La mer
                } else if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 1) { //Pour voir mes bateaux dans le plateau de l'adversaire
                    this.infoJButtons[i][j].setBackground(new Color(75, 0, 131)); //Mes bateaux dans le plateau adverse
                } else if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 1) { //Les bateaux adverses sont invisibles
                    this.infoJButtons[i][j].setBackground(new Color(0, 70, 128));
                } else if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 2) { //Bateau touché
                    this.infoJButtons[i][j].setBackground(Color.red);
                } else if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 3) { //Tir raté
                    this.infoJButtons[i][j].setBackground(new Color(5, 238, 9));
                } else if (this.infoJButtons[i][j].getInfoElement().getTypeCase() == 4) { //Bateau coulé
                    this.infoJButtons[i][j].setBackground(Color.black);
                }
            }
        }
    }
    
    // getters setters
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public ButtonElement[][] getInfoJButtons() {
		return infoJButtons;
	}

	public void setInfoJButtons(ButtonElement[][] infoJButtons) {
		this.infoJButtons = infoJButtons;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public ArrayList<ButtonElement> getDisableButton() {
		return disableButton;
	}

	public void setDisableButton(ArrayList<ButtonElement> disableButton) {
		this.disableButton = disableButton;
	}

	public ArrayList<ButtonElement> getEnableButton() {
		return enableButton;
	}

	public void setEnableButton(ArrayList<ButtonElement> enableButton) {
		this.enableButton = enableButton;
	}

	public Integer getNbrBateaux() {
		return nbrBateaux;
	}

	public void setNbrBateaux(Integer nbrBateaux) {
		this.nbrBateaux = nbrBateaux;
	}

	public Integer getCptNbrBateauxPlacer() {
		return cptNbrBateauxPlacer;
	}

	public void setCptNbrBateauxPlacer(Integer cptNbrBateauxPlacer) {
		this.cptNbrBateauxPlacer = cptNbrBateauxPlacer;
	}

	public Boolean getCreatedHumanGrid() {
		return createdHumanGrid;
	}

	public void setCreatedHumanGrid(Boolean createdHumanGrid) {
		this.createdHumanGrid = createdHumanGrid;
	}
	
	public Boolean getAllBoatPlaces() {
		return allBoatPlaces;
	}

	public void setAllBoatPlaces(Boolean allBoatPlaces) {
		this.allBoatPlaces = allBoatPlaces;
	}

	@Override
	public void modeleMisAJour(ModeleEcoutable source) {
		// TODO Auto-generated method stub
		if (getAllBoatPlaces()) {
            play();
        }
	}

	public void play() {
		if (getAllBoatPlaces() && !createdHumanGrid) {
            createGridClickable();
        }
        else if (!plateau.partieFini()) {
            if (!joueur.getHumain() && aSonTour()) {
            	//activer les bouttons
                this.activeButton();
            } else if (joueur.getHumain() && aSonTour()) {
            	//changer de joueur et de plateau
                //joueur.doExecute(this.plateau);
            	Scanner scanner = new Scanner(System.in);
            	String coup = ((Scanner) this.scanner).next();
                while (!plateau.coupValide(plateau.getPlateauAdversaire(this.joueur),Integer.parseInt(coup))) {
                    System.out.println("Le coup n'est pas valide, recommence");
                    coup = ((Scanner) this.scanner).next();
                }
                Integer coupp = Integer.parseInt(coup);
                plateau.bateauTouche(plateau.getPlateauAdversaire(this.joueur), coupp);
                //jeu.changeJoueur_courant();
                this.joueur = (plateau.getJoueur1().equals(joueur))?plateau.getJoueur2():plateau.getJoueur1();
            }
            update();

        } else {

            this.removeAll();
            this.revalidate();
            this.repaint();
            this.add(new Vainqueur(plateau.vainqueur()));
        }
	}
}
