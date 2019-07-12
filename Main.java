/**
 * 
 * @author Cornelus Madjri & Sorelle Ngouoko
 *
 */
public class Main {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int nbr_Baigneur = 6; // nombre de baigneurs
		final int nbr_Panier = 3; // nombre de paniers
		final int nbr_Cabine = 4; // nombre de cabines
		// initialise les sémaphores
		// et ouvre en écriture le tube des résultats
		Baigneur.init(nbr_Panier, nbr_Cabine);

		// creer les threads baigneurs
		Baigneur[] baigneur = new Baigneur[nbr_Baigneur];
		for (int i = 0; i < nbr_Baigneur; i++)
			baigneur[i] = new Baigneur(i);

		// creer le thread DessinBaigneurs
		BaigneursCreator dessin = new BaigneursCreator(nbr_Baigneur);
		Thread tdessin = new Thread(dessin);
		tdessin.start();

		// attendre la fin de tous les threads Baigneur
		// et du thread de DessinBaigneurs
		try {
			for (int i = 0; i < nbr_Baigneur; i++)
				baigneur[i].join();
			tdessin.join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		// le thread initial main se termine
		System.out.println("C'est fini");

	}
}// Fin classe main