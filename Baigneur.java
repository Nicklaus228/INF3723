import java.awt.*; // Container, Color
import javax.swing.*;
import java.io.*;

//
/**
 * chaque Baigneur est un thread qui doit se synchroniser avec les autres
 * 
 * @author Cornelus Madjri & Sorelle Ngouoko
 *
 */
public class Baigneur extends Thread {
	static Semaphore panier; // attribut de classe
	static Semaphore cabine; // attribut de classe
	static PipedOutputStream po = null; // attribut de classe
	int numero; // numéro du Baigneur

	/**
	 * à faire une fois pour initialiser les sémaphores et le tube
	 * 
	 * @param nbPanier
	 * @param nbCabine
	 */
	static void init(int nbPanier, int nbCabine) {
		panier = new Semaphore(nbPanier);
		cabine = new Semaphore(nbCabine);
		try {
			po = new PipedOutputStream(BaigneursCreator.pi);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * accède aux variables de classe panier, cabine (de type Semaphore) et po
	 * (pipe output) : le tube dans lequel chaque Baigneur écrit ses résultats
	 * (son numéro et son état)
	 * 
	 * @param numero
	 */
	Baigneur(int numero) {
		this.numero = numero;
		start(); // on démarre le thread dans le constructeur
	}

	//
	/**
	 * envoi de numero et etat dans le tube po
	 * 
	 * @param etat
	 */
	void message(int etat) {
		try {
			po.write(numero);
			po.write(etat);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	//
	/**
	 * Initialisation d'une attente aléatoire
	 * 
	 * @param n
	 *            attente aléatoire
	 */
	void attente(int n) {
		Utile.attente(n);
	}

	/**
	 * l’activité d’un Baigneur
	 */
	public void run() {
		while (true) {
			attente(100);
			message(0); // arrive
			attente(60);
			panier.P();

			message(1); // prend un panier
			attente(80);
			cabine.P();

			message(2); // se déshabille
			attente(60);

			message(3); // se baigne
			cabine.V();
			attente(150);
			cabine.P();

			message(4); // se rhabille
			attente(80);

			message(5); // quitte
			cabine.V();
			attente(40);
			panier.V();
			int nb = 0;
			nb--;
			// numero--;
			/*
			 * if (nb == 0) { try { po.close(); } catch (Exception e) {
			 * System.out.println(e); } }
			 */
		}
	}
}// Fin class Baigneur