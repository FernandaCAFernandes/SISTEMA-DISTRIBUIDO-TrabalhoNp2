import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Gerenciador {

	static Entidade entidade = new Entidade(0, 0);
	public static Server serversocket;
	public static int porta;
	public static ArrayList<EntidadeHandler> entidades;
	static Gerenciador gerenciador;
	public static int totalCPU = 0;
	public static int totalMemo = 0;
	MyRunnable myRunnable = new MyRunnable();

	public Gerenciador(String ip) {
		Random r = new Random();
		porta = r.nextInt(9000 - 8500 + 1) + 8500;

		serversocket = new Server(Gerenciador.porta);
		Socket socket = new Socket();
		entidades = new ArrayList<EntidadeHandler>();
		new Thread(serversocket).start();

		// entidade que se conecta no sistema nao e a primeira. .
		if (!ip.equals("")) {
			try {
				socket.connect(new InetSocketAddress("", Integer.parseInt(TelaInicial.porta.getText())));
				addEntidade(socket);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void startup(String ip) {
		if (gerenciador == null) {
			new Thread(myRunnable).start();

		}
	}

	public static void changeCpuMemo(String cpu, String memoria) {

		// System.out.println(cpu + " /// " + memoria);

		entidade.setCpu(Integer.parseInt(cpu));
		entidade.setMemoria(Integer.parseInt(memoria));

		try {

			for (int i = 0; i < entidades.size(); i++) {
				entidades.get(i).SalvarCpuMemLocal(cpu, memoria);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static void addEntidade(Socket socket) {
		EntidadeHandler entidadeHandler = new EntidadeHandler(socket.getRemoteSocketAddress().toString(),
				socket.getLocalPort(), socket);
		entidades.add(entidadeHandler);
		new Thread(entidadeHandler).start();

	}

}