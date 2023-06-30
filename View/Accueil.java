package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import Model.Joueur;
import Model.Plateau;

public class Accueil  extends JFrame{
	
	public Accueil() {
		
		super();
		
		GridLayout layout = new GridLayout(1, 1,50 , 50);
		
		JButton playButton = new JButton("JOUER");
		this.add(playButton,BorderLayout.CENTER);
		this.setLayout(layout);
		
		playButton.setPreferredSize(new Dimension(20,20));
		
		playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	removeAlls();
                Joueur joueurRandom = new Joueur("Joueur Aleatoire", true);
                Joueur joueurHumain = new Joueur("Joueur Humain", false);
                Plateau plateau = new Plateau(joueurHumain,joueurRandom);
                new BatailleGUI(plateau);
            }
        });
		
		this.setSize(new Dimension(1200,700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void removeAlls(){
        this.removeAll();
        this.repaint();
        this.dispose();
    }
}
