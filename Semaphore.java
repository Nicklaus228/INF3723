/**
 * 
 * @author Cornelus Madjri & Sorelle Ngouoko
 *
 */
public class Semaphore {

	private int nbr_Jeton = 0; // nombre de jetons du sémaphore
	String nom; // nom du sémaphore

	/**
	 * 
	 * @param nom
	 *            nom du sémaphore
	 * @param nbr_Jeton
	 *            nombre de jetons du sémaphore
	 */
	public Semaphore(String nom, int nJeton) {
		this.nom = new String(nom);
		this.nbr_Jeton = nJeton;
	}

	/**
	 * 
	 * @param nJeton
	 *            nombre de jetons du sémaphore
	 */
	public Semaphore(int nbr_Jeton) {
		this("", nbr_Jeton);
	}

	/**
	 * Demande d'autorisation de construction. P() peut êre mis en minuscule
	 * 
	 * le thread est mis en attente sur l’objet si nJeton = 0. Quand il est
	 * réveillé, il reteste la valeur de nJeton.
	 * 
	 */
	synchronized public void P() { // Puis-je continuer ?
		try {
			while (nbr_Jeton == 0)
				wait();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		nbr_Jeton--; // il prend un jeton
	}

	/**
	 * V() peut être mis en minuscule
	 */
	synchronized public void V() { // Vas-y, continue !
		nbr_Jeton++; // on remet un jeton
		notify(); // on le fait savoir
	}

}// Fin Class Semaphore

