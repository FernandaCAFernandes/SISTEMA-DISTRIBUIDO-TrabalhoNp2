import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Gerenciador {

	static Entidade entidade;
	static Server serversocket;
	static int porta;
	public static ArrayList<EntidadeHandler> entidades = new ArrayList<>();
	static Gerenciador gerenciador;
	public static int totalCPU = 0;
	public static int totalMemo = 0;
	

	@SuppressWarnings("resource")
	public Gerenciador(String ip, int porta) {
		
		this.porta = porta;
		Socket socket = new Socket();
		new Thread(serversocket).start();

		// entidade que se conecta no sistema nao e a primeira. .
		if (!ip.equals("inicial") && porta != 0) {

			try {
				socket.connect(new InetSocketAddress(ip, porta));
				addEntidade(socket);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startup(String ip, int porta) {
		if (gerenciador == null) {
			gerenciador = new Gerenciador (ip, porta);
		}
	}

	public static void addEntidade(Socket socket) {
		// localport ou port (visualizar)
		EntidadeHandler entidadeHandler = new EntidadeHandler(socket.getRemoteSocketAddress().toString(),
				socket.getLocalPort(), socket);
		entidades.add(entidadeHandler);
		new Thread(entidadeHandler).start();
	}

}