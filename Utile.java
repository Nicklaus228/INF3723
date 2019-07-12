/**
 * 
 * @author Cornelus Madjri & Sorelle Ngouoko
 *
 */
public class Utile {
	/**
	 * g�n�re un nombre entre 0 et N-1 inclus
	 * 
	 * @param N
	 *            Limite
	 * @return le nombre all�aoire
	 */
	public static int aleat(int N) {
		return (int) ((Math.random() * 997) % N);
	}

	/**
	 * le thread s�endort pendant n millisecondes
	 * 
	 * @param n
	 *            dur�e du someil du thread
	 */
	public static void pause(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	 * attente d�un nombre al�atoire de ms entre 0 et n
	 * 
	 * @param n
	 *            dur�e de l'attete
	 */
	public static void attente(int n) {
		try {
			Thread.sleep(aleat(n));
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

} // Fin Class Utile
