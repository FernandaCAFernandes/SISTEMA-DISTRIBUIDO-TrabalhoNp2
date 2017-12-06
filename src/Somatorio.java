import java.io.IOException;

public class Somatorio {

	public class MyRunnable implements Runnable {

		public void run() {

			while (true) {
				try {
					int auxMemoTotal = 0;
					int auxCpuTotal = 0;

					for (int i = 0; i < Gerenciador.entidades.size(); i++) {
						EntidadeHandler.validar();
						auxCpuTotal = auxCpuTotal + Gerenciador.entidades.get(i).entidade.getCpu();
						auxMemoTotal = auxMemoTotal + Gerenciador.entidades.get(i).entidade.getMemoria();
					}
					auxCpuTotal = auxCpuTotal + Gerenciador.entidade.getCpu();
					auxMemoTotal = auxMemoTotal + Gerenciador.entidade.getMemoria();

					TelaPrincipal.AttLabels(auxCpuTotal, auxMemoTotal);
					// 5 s = 5000 milis (milisegundos)

					// Thread.sleep(5000);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		/*
		 * Thread t = new Thread (new MyRunnable());
		 *  t.start();
		 */

		/*
		 * MyRunnable r = new MyRunnable(); 
		 * Thread t = new Thread (r); 
		 * t.start();
		 */
		
		/*This single-line version works — provided that you don’t need to access the thread object later in the program.
		 * new Thread(new MyRunnable()).start(); */

	}
}