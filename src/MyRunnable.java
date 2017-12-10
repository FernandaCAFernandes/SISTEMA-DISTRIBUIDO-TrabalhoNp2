import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MyRunnable implements Runnable {

	public void run() {
		List<EntidadeHandler> disconecteds = new LinkedList<EntidadeHandler>();
		while (true) {

			int auxMemoTotal = 0;
			int auxCpuTotal = 0;
			

			EntidadeHandler entidade;
			for (int i = 0; i < Gerenciador.entidades.size(); i++) {
				entidade = Gerenciador.entidades.get(i);
				if (!entidade.stop) {

					try {
						entidade.validar();
						
						auxCpuTotal = auxCpuTotal + entidade.entidade.getCpu();
						auxMemoTotal = auxMemoTotal + entidade.entidade.getMemoria();
					} catch (IOException e) {
						Gerenciador.entidades.get(i).stop = true;
						
						auxCpuTotal = auxCpuTotal - entidade.entidade.getCpu();
						auxMemoTotal = auxMemoTotal - entidade.entidade.getMemoria();
						
						disconecteds.add(entidade);
						System.err.println(e.toString());
					}

					

				}
			}
			
			
			if (disconecteds.size() > 0) {
				for(EntidadeHandler e : disconecteds) {
					System.out.println(Gerenciador.entidades.remove(e));
				}
				
				disconecteds.clear();
			}
			
			
			auxCpuTotal = auxCpuTotal + Gerenciador.entidade.getCpu();
			auxMemoTotal = auxMemoTotal + Gerenciador.entidade.getMemoria();

			TelaPrincipal.AttLabels(auxCpuTotal, auxMemoTotal);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

/*
 * MyRunnable r = new MyRunnable(); Thread t = new Thread (r); t.start();
 */

/*
 * This single-line version works — provided that you don’t need to access the
 * thread object later in the program. new Thread(new MyRunnable()).start();
 */
