package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Joueur;

public class Vainqueur extends JPanel  {
	private Joueur gagnant;

    public Vainqueur(Joueur gagnant){
        super();

        JLabel win = new JLabel("Bravo "+gagnant.getName()+" tu as gagné");
        win.setBorder(new EmptyBorder(0,10,0,10));
        win.setFont(new Font("arial", Font.PLAIN, 22));
        JButton exitButton = new JButton("EXIT");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(win);
        this.add(exitButton);
    }
}
