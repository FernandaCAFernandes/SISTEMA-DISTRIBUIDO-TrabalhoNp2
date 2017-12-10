import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Gerenciador {

	static Entidade entidade = new Entidade(0, 0);
	public static Server serversocket;
	public static int porta;
	public static ArrayList<EntidadeHandler> entidades = new ArrayList<>();
	static Gerenciador gerenciador;
	public static int totalCPU = 0;
	public static int totalMemo = 0;
	MyRunnable myRunnable = new MyRunnable();

	@SuppressWarnings("resource")

	public Gerenciador(String ip) {
		Random r = new Random();
		porta = r.nextInt(10000);
		serversocket = new Server(this.porta);
		Socket socket = new Socket();
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
			gerenciador = new Gerenciador(ip);
			new Thread(myRunnable).start();

		}
	}

	public static void changeCpuMemo(String cpu, String memoria) {
		entidade.setCpu(Integer.parseInt(cpu));
		entidade.setMemoria(Integer.parseInt(memoria));

	}

	public static void addEntidade(Socket socket) {
		EntidadeHandler entidadeHandler = new EntidadeHandler(socket.getRemoteSocketAddress().toString(),
				socket.getLocalPort(), socket);
		entidades.add(entidadeHandler);
		new Thread(entidadeHandler).start();

	}

}