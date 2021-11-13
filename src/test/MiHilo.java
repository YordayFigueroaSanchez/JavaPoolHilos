package test;

public class MiHilo implements Runnable{
	
	int id;
	
	public MiHilo(int pId) {
		this.id = pId;
	}
	
	private int calcular() {
		return this.id;
	}

	/**
	 * cada hilo imprime un mensaje y espera un tiempo aleatorio
	 */
	@Override
	public void run() {
		// se muestra un mensaje con el nombre del hilo
		System.out.println("El hilo " + this.id + " toma el pool " + Thread.currentThread().getName());
		
		//se bloque el hilo un tiempo aleatorio
		try {
			Thread.sleep((int)(Math.random()*200+100));
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		//mensaje indicando que va a dejar el pool
		System.out.println("El hilo " + this.id + " deja el pool " + Thread.currentThread().getName());
		
	}

}
