import java.io.*;
import java.io.PipedInputStream;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.*;
import java.util.concurrent.*;

/**
 * la classe BaigneursCreator est un thread qui lit dans le tube et dessine les
 * états des baigneurs
 * 
 * // * @author Cornelus Madjri & Sorelle Ngouoko
 * 
 * @version
 *
 */
public class BaigneursCreator extends JFrame implements Runnable {
	int nbProc; // nombre de processus Baigneur
	int[] etatB; // tableau de l’état courant de chaque Baigneur
	JLabel[][] tE; // tableau de libellés

	static PipedInputStream pi = new PipedInputStream();

	// les noms des états d’un Baigneur
	String[] tEtat = { " Arrive ", // 0
			" Obtient Panier ", // 1
			" Se déshabille ", // 2
			"Se baigne ", // 3
			" Se rhabille ", // 4
			" Quitte " // 5
	};

	int nbEtat = tEtat.length; // nombre d’états
	String[] nomb = { // Noms des baigneurs (A à F), mais on pourrai mettre plus
	"A", "B", "C", "D", "E", "F" };

	/**
	 * constructeur de BaigneursCreator
	 * 
	 * @param nbProc
	 *            représente le nombre de processus
	 */
	BaigneursCreator(int nbProc) {
		Container f = getContentPane();
		this.nbProc = nbProc;
		etatB = new int[nbProc];
		tE = new JLabel[nbEtat][nbProc];
		for (int i = 0; i < etatB.length; i++)
			etatB[i] = 0;
		setTitle("La piscine");
		f.setLayout(new GridLayout(0, nbProc + 1));
		// Ligne de titre du haut
		JLabel l1 = new JLabel("BAIGNEURS");
		f.add(l1);

		for (int j = 0; j < nbProc; j++) {
			JLabel b = new JLabel(nomb[j], JLabel.CENTER);
			b.setOpaque(true);
			b.setBackground(Color.LIGHT_GRAY);// colonne des différents baigneur
			f.add(b);
		}

		for (int j = 0; j < nbEtat; j++) {
			JLabel l2 = new JLabel(tEtat[j]);
			l2.setOpaque(true);
			l2.setBackground(Color.LIGHT_GRAY); // colonne 1 des noms d’états
			f.add(l2);
			for (int i = 0; i < nbProc; i++) {
				JLabel l3 = new JLabel(" ", JLabel.CENTER);
				l3.setOpaque(true);
				tE[j][i] = l3;
				f.add(l3);
			}
		}

		setBounds(20, 20, 650, 300);
		setVisible(true);
	}

	/**
	 * Lit dans le tube pi et affiche suivant les valeurs lues
	 */
	public void run() {
		int numero; // numéro du baigneur
		try {
			while ((numero = pi.read()) != -1) {
				int etat = pi.read();
				// on modifie l’ancien état du baigneur
				tE[etatB[numero]][numero].setText(".");
				tE[etatB[numero]][numero].setBackground(Color.white);
				// on modifie le nouvel état du baigneur
				etatB[numero] = etat;
				tE[etat][numero].setText("*");
				tE[etat][numero].setBackground(Color.magenta);
				Utile.pause(500);

			}
			pi.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}// Fin class DessinBaigneurs