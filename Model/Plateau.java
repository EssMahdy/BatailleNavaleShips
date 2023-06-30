package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.AbstractModeleEcoutable;
public class Plateau extends AbstractModeleEcoutable {
	private InfoElement[][] plateau1, plateau2; // Les deux plateaux de jeu
	private final int ROW; // On definie des constantes pour ROW et COL
	private final int COL; // On definie des constantes pour ROW et COL
	private Bateau[] listeBateau1; // Liste des bateaux du joueur 1
	private Bateau[] listeBateau2; // Liste des bateaux du joueur 2
	private Joueur joueur1, joueur2, joueurCourant; // Les deux joueurs et le joueur courant
	
	public Plateau(Joueur joueur1, Joueur joueur2) {

		/**
		 * Initialisation des constantes
		 */
		this.ROW = 9;
		this.COL = 9;
				
		/**
		 * Intialisation des joueurs
		 */
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.joueurCourant = joueur1;
		
		/**
		 * Initialisation des plateaux
		 */
		this.plateau1 = new InfoElement[ROW][COL];
		this.plateau2 = new InfoElement[ROW][COL];
		
		/**
		 * Initialisation de la liste des bateaux des joueurs
		 */
		this.listeBateau1 = new Bateau[5];
		this.listeBateau2 = new Bateau[5];
		
		// Initialisation des plateaux (Appel de la methode initPlateau)
		initPlateau(ROW, COL);
		
	}
	public InfoElement[][] getPlateau1() {
		return plateau1;
	}

	public void setPlateau1(InfoElement[][] plateau1) {
		this.fireChangement();
		this.plateau1 = plateau1;
	}

	public InfoElement[][] getPlateau2() {
		return plateau2;
	}

	public void setPlateau2(InfoElement[][] plateau2) {
		this.fireChangement();
		
		this.plateau2 = plateau2;
	}

	public Bateau[] getListeBateau1() {
		return listeBateau1;
	}

	public void setListeBateau1(Bateau[] listeBateau1) {
		this.fireChangement();
		this.listeBateau1 = listeBateau1;
	}

	public Bateau[] getListeBateau2() {
		return listeBateau2;
	}

	public void setListeBateau2(Bateau[] listeBateau2) {
		this.fireChangement();
		this.listeBateau2 = listeBateau2;
	}

	@Override
	public String toString() {
		String res = "------------------------PLATEAU 1:------------------------\n";
		for(int i=0; i<ROW; i++) {
			for(int j=0; j<COL; j++) {
				res += plateau1[i][j].toString() + " ";
			}
			res += "\n";
		}
		res += "------------------------PLATEAU 2 :------------------------\n";
		for(int i=0; i<ROW; i++) {
			for(int j=0; j<COL; j++) {
				res += plateau2[i][j].toString() + " ";
			}
			res += "\n";
		}
		return res;			
	}

	// Getteur et setteur
	public int getROW() {
		return ROW;
	}
	public int getCOL() {
		return COL;
	}
	public Joueur getJoueur1() {
		return joueur1; // Retourne le joueur 1
	}
	
	public Joueur getJoueur2() {
		return joueur2; // Retourne le joueur 2
	}
	
	public InfoElement[][] getPlateau(Joueur j){ 	
		return (j == joueur1) ? plateau1 : plateau2; // Récupère le plateau du joueur	
	}
	
	public InfoElement[][] getPlateauAdversaire(Joueur j){
		return (j == joueur1) ? plateau2 : plateau1; // Récupère le plateau où le joueur joue ses coups
	}
	/*(Si le joueur est le même que celui associé à l'objet Plateau, la méthode renvoie 
	le plateau du joueur, sinon elle renvoie le plateau de l'adversaire)*/

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;// Definir le joueur courant
		this.fireChangement();
	}

	public Joueur getJoueurCourant() {
		return joueurCourant; // Retourner le joueur courant 
	}
	
	public Joueur getJoueurAdversaire(Joueur j) {
		return (j== joueur1) ? joueur2 : joueur1;
	}
	
	public void changerJoueurCourant() {
		joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
		this.fireChangement();
	}
	
	/**
	 * Initialiser les plateaux
	 * @param ROW
	 * @param COL
	 */
	public void initPlateau(int ROW, int COL) {  
		Integer cpt = 0;
		plateau1 = new InfoElement[ROW][COL];
		plateau2 = new InfoElement[ROW][COL];
		
		for (int i = 0; i < ROW; i++){
			for (int j = 0; j < COL; j++) {
				plateau1[i][j] = new InfoElement();
				plateau1[i][j].setCompteur(cpt);
				plateau2[i][j] = new InfoElement();
				plateau2[i][j].setCompteur(cpt);	
				cpt+=1;
			}
		}
	}
	
	/**
	 * Creer cinq bateaux de tailles PREDIFINI
	 * @param list
	 */
	public void creerBateau() {  

		this.listeBateau1[0] = new Bateau(2);
		this.listeBateau1[1] = new Bateau(3);
		this.listeBateau1[2] = new Bateau(3);
		this.listeBateau1[3] = new Bateau(4);
		this.listeBateau1[4] = new Bateau(5);
		
		this.listeBateau2[0] = new Bateau(2);
		this.listeBateau2[1] = new Bateau(3);
		this.listeBateau2[2] = new Bateau(3);
		this.listeBateau2[3] = new Bateau(4);
		this.listeBateau2[4] = new Bateau(5);
		
	}
	
	/**
	 * Remplir une case particuliere (b) d'un plateau avec un bateau aleatoire
	 * @param p Plateau
	 * @param b Bouton d'informations
	 */
	public void remplissageAlea (InfoElement[][]p, InfoElement b) { 
		for (int i=0; i < p.length; i++) { // Le parametre (p) est le plateau de jeu, un tableau a deux dimensions
			for (int j=0; j<p[i].length; j++) {
				if (p[i][j]==b) { // Le parametre (b) est un bouton d'informations specifique qui doit etre rempli avec un bateau
					int rand = new Random().nextInt(5)+1;
					Bateau bateau = new Bateau(rand);
					ajouterBateau(bateau, p, true, b);
					/* Cette methode utilise une double boucle for pour parcourir tous les boutons 
					de la grille et verifie si chaque bouton est le meme que le bouton specifie. 
					Si oui, elle utilise Random pour générer une taille aléatoire pour le bateau 
					et cree ensuite un objet Bateau avec cette taille. 
					Enfin, elle appelle la methode ajouterBateau pour placer le bateau sur le bouton 
					specifie */
				}
			}
		}
		this.fireChangement();
	}
	
	/**
	 * Reinitialiser le plateau
	 * @param p
	 */
	public void reinitialiserPlateau (InfoElement[][]p) {
		for (int i=0; i < p.length; i++) {
			for (int j=0; j<p[i].length; j++) {
				if (p[i][j].getTypeCase()==1) {
					p[i][j].setTypeCase(0);
				}
			}
		}
		this.fireChangement();
	}
	
	/**
	 * Placer un bateau
	 * @param p plateau
	 * @param b bateau
	 */
	public void placerBateau (InfoElement[][]p, int x, int y, int taille, boolean horizontale) {
		if(horizontale) {
			if(y+taille < p[0].length) {
				for(int i=0; i<taille; i++) {
					p[x][y+i].setTypeCase(1);
					this.fireChangement();
				}
			}
		}
		else {
			if(x+taille < p.length) {
				for(int i=0; i<taille; i++) {
					p[x+i][y].setTypeCase(1);
					this.fireChangement();
				}
			}
		}
	}
	
	/**
	 * Verifier si la case (i,j) du plateau est occupé et si c'est possible de placer le bateau la bas 
	 * @param tailleBateau
	 * @param i l'abscisse 
	 * @param j l'ordonnee
	 * @param HoriVerti
	 * @param p plateau
	 * @return occuper ou non 
	 */
	public boolean occuperPlateau (Integer tailleBateau, Integer i, Integer j, String HoriVerti, InfoElement[][] p) {
		if(HoriVerti.equals("Hori")) {
			for (int l=0; l < tailleBateau; l++) {
				if (j+l >= p[i].length || p[i][j + l].getTypeCase()==1) {
					return true;
				}
			}
		} 
		else {
			for(int l=0; l < tailleBateau; l++) {
				if (i+l >= p.length || p[i][j].getTypeCase()==1) {
					return true;
				}
			}
		}
		return false;
	}
	/*La methode commence par verifier si la direction du bateau est horizontale ou verticale. Si elle est horizontale
	elle parcourt chaque case horizontalement en verifiant si la case est hors des limites du tableau p 
	ou si elle est deja occupee par un autre bateau. Si elle est verticale, elle fait la même chose mais verticalement
	NB: false = on peut placer le bateau / true = on peut pas placer le bateau*/

	public void ajouterBateau(Bateau bateau, InfoElement[][] p, Boolean debut, InfoElement b) {
		String id = bateau.getID();
	    int taille = bateau.getTaille();
	    int i,j;
	    int HoriVerti = (int) (Math.random() * 2);
	    if(!debut) {
	    	int nbAlea = (int) (Math.random() * ROW*COL-1);
	    	i = nbAlea / ROW;
	        j = nbAlea % COL;
	    }
	    else {
	    	i = b.getCompteur() / ROW;
	        j = b.getCompteur() % COL;
	    }
	    
	    if (HoriVerti == 0) { //Horizontal

            if (j + taille > ROW-1 || occuperPlateau(taille, i, j, "Hori", p)) {
            	ajouterBateau(bateau, p, false, null);
            } else {
                for (int k = 0; k < taille; k++) {
                    p[i][j+k].setTypeCase(1);
                    p[i][j+k].setId(id);
                    this.fireChangement();
                }
            }
        } else { // Vertical

            if (i + taille > COL-1 || occuperPlateau(taille, i, j, "Verti", p)) {
            	ajouterBateau(bateau, p, false, null);
            } else {
                for (int k = 0; k < taille; k++) {
                    p[i+k][j].setTypeCase(1);
                    p[i+k][j].setId(id);
                    this.fireChangement();
                }
            }
        }
	}
	
	public void ajouterBateaux(Bateau[] listeB, InfoElement[][] p) {
        for (Bateau bateau : listeB) {
        	ajouterBateau(bateau, p, false, null);
        }
        
	}
	
	public boolean coupValide(InfoElement[][] p,int coup) {
		//verifier si le coup est a l'interieur du plateau
	    if (coup < 0 && coup > ROW*COL-1)
	    	return false;
	    for (InfoElement[] ROW : p) {
	        for (InfoElement button : ROW) {
	            if (button.getCompteur() == coup) {
	                if (button.getTypeCase() == 1 || button.getTypeCase() == 0) {
	                    return true;
	                } else {
	                    return false;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	public boolean bateauTouche(InfoElement[][] p, int coup) {
	    //verifier si le coup a atteint une case préoccupé
		//tirer vers la case de compteur coup
	    for (InfoElement[] ROW : p) {
	        for (InfoElement button : ROW) {
	            if (button.getCompteur() == coup) {
	                if (button.getTypeCase() == 1) {
	                	button.setTypeCase(2);
	                	this.fireChangement();
						if (bateauCouler(p, button)) {
							System.out.println("Le bateau " + button.getId() + " est coulé !");
						}
	                    return true;
	                } else {
	                	button.setTypeCase(3);
	                	this.fireChangement();
	                	
	                    return false;
	                }
	            }
	        }
	    }
	    return false;
	}

	public void jouerAlea(InfoElement[][] p) {
		int nbAlea = (int) (Math.random() * ROW*COL-1);
    	System.out.println("X : " + nbAlea%ROW + ", Y : " + nbAlea/COL);
		if(this.coupValide(p,nbAlea)) {
			this.bateauTouche(p, nbAlea);
		}
		this.fireChangement();
	}

	public boolean bateauCouler(InfoElement[][] p, InfoElement b) {
		ArrayList<InfoElement> bateau = new ArrayList<>();
		for (InfoElement[] row : p) {
			for (InfoElement button : row) {
				if (button.getId().equals(b.getId())) {
					bateau.add(button);
				}
			}
		}

		for (InfoElement button : bateau) {
			if (button.getCompteur() != 2) {
				return false;
			}
		}

		for (InfoElement button : bateau) {
			button.setCompteur(4);
		}

		this.fireChangement();
		System.out.println("Bateau coulé !");
		return true;
	}

	public void bateauCouler(ArrayList<InfoElement> bateau) {
		for (InfoElement b : bateau) {
			b.setCompteur(4);
		}
		this.fireChangement();
	}
	
	public int tailleBateaux(Bateau[] listeBateau) {
		int count = 0;
		for(Bateau b: listeBateau) count += b.getTaille();
		return count;
	}
	
	public Joueur vainqueur() {
		int cpt1 = 0, cpt2 = 0;
		
		for(InfoElement[] ROW: this.plateau1) {
			for(InfoElement button : ROW) {
				
				if(button.getCompteur() == 4) cpt1 ++;
			}
		}
		
		for(InfoElement[] ROW: this.plateau2) {
			for(InfoElement button : ROW) {
				
				if(button.getCompteur() == 4) cpt2 ++;
			}
		}
		
		if(cpt1 == this.tailleBateaux(this.listeBateau1)) return this.joueur1;
		else if( cpt2 == this.tailleBateaux(this.listeBateau2))return this.joueur2;
		else return null;
	}
	
	public boolean partieFini() {
		return this.vainqueur() != null;
	}
	
	public void notifier() {
		this.fireChangement();
	}
}










































