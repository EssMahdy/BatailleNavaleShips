package View;

import java.awt.Dimension;
import javax.swing.JButton;

import Model.InfoElement;

//designe un boutton du plateau
public class ButtonElement extends JButton{
	
	private InfoElement i;
	
	public ButtonElement(InfoElement i) {
		this.i = i;
		setPreferredSize(new Dimension(10, 10)); // Définit la taille préférée du bouton
	}
	
	public InfoElement getInfoElement() {
		return this.i;
	}
	
	public void setInfoElement(InfoElement i) {
		this.i = i;
	}
	
	public static InfoElement[][] getInfoElements(ButtonElement[][] b) {
		// TODO Auto-generated method stub
		InfoElement[][] resultat = new InfoElement[b.length][b[0].length];
		for(int i=0; i<b.length; i++) {
			for(int j=0; j<b[0].length; j++) {
				resultat[i][j] = b[i][j].getInfoElement();
			}
		}
		return resultat;
	}
}
