import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EntidadeHandler implements Runnable {

	String ip;
	int porta;
	Entidade entidade;
	Socket socket;
	InputStream in;
	static OutputStream out;
	byte[] buffer;

	public boolean stop;

	public EntidadeHandler(String ip, int porta, Socket socket) {
		this.ip = ip;
		this.porta = porta;
		this.socket = socket;
	}

	// sender/ buffer :
	// salver cpu/memoria
	// calcular total cpu / memoria
	// informar bloqueio
	// informar recursos reservados
	// [1]- tipo de msg ([2] - se memoria ou cpu?) [18] - qtde ??

	public static void SalvarCpu(String qtde) throws IOException {
		// buffer[0] = 1
		byte[] buffer = new byte[20];
		buffer[0] = 1;

		for (int i = 0; i < qtde.length(); i++) {
			buffer[i + 1] = (byte) qtde.codePointAt(i);

		}
		out.write(buffer);
	}

	public static void SalvarMem(String qtde) throws IOException {
		// buffer[0] = 2
		byte[] buffer = new byte[20];
		buffer[0] = 2;

		for (int i = 0; i < qtde.length(); i++) {
			buffer[i + 1] = (byte) qtde.codePointAt(i);
		}
		out.write(buffer);
	}

	public static void TotalCpu() throws IOException {
		// 32?? ou 4 (4 bytes on a 32/64-bit system)??
		// buffer [0] = 3
		byte[] buffer = new byte[1 + Gerenciador.entidades.size() * 32];
		buffer[0] = 3;
		for (int i = 0; i < Gerenciador.entidades.size(); i++) {
			buffer[i + 1] = (byte) Gerenciador.entidades.get(i).entidade.getCpu();
		}
		out.write(buffer);

	}

	public static void TotalMemo() throws IOException {
		// buffer[0] = 4;
		byte[] buffer = new byte[1 + Gerenciador.entidades.size() * 32];
		buffer[0] = 4;
		for (int i = 0; i < Gerenciador.entidades.size(); i++) {
			buffer[i + 1] = (byte) Gerenciador.entidades.get(i).entidade.getMemoria();
		}
		out.write(buffer);

	}

	public static void BloqueadoCpu() throws IOException {
		// buffer[0] = 5;
	}

	public static void BloqueadoMemo() throws IOException {
		// buffer[0] = 6;
	}

	public static void ReservadoCpu() throws IOException {
		// buffer[0] = 7;
	}

	public static void ReservadoMemo() throws IOException {
		// buffer[0] = 8;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!stop) {

				int size = in.available();

				if (size > 0) {
					buffer = new byte[size];
					in.read(buffer);
					AnalizadorBuffer();

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void AnalizadorBuffer() {
		// ?????
		// String.trim() - remove espacos em branco de ambos os lados da string.	
		int type = buffer[0] & 255;

		switch (type) {
		case 1:

		}
	}

}
