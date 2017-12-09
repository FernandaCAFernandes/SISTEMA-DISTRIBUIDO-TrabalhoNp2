import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

	int porta;
	ServerSocket server;

	public Server(int porta) {
		this.porta = porta;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			server = new ServerSocket(porta);

			while (true) {
				Gerenciador.addEntidade(server.accept());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
