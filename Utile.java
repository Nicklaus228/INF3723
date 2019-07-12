/**
 * 
 * @author Cornelus Madjri & Sorelle Ngouoko
 *
 */
public class Utile {
	/**
	 * génère un nombre entre 0 et N-1 inclus
	 * 
	 * @param N
	 *            Limite
	 * @return le nombre alléaoire
	 */
	public static int aleat(int N) {
		return (int) ((Math.random() * 997) % N);
	}

	/**
	 * le thread s’endort pendant n millisecondes
	 * 
	 * @param n
	 *            durée du someil du thread
	 */
	public static void pause(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	 * attente d’un nombre aléatoire de ms entre 0 et n
	 * 
	 * @param n
	 *            durée de l'attete
	 */
	public static void attente(int n) {
		try {
			Thread.sleep(aleat(n));
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

} // Fin Class Utile
