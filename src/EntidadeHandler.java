import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

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

	public static void SalvarCpuLocal(String qtde) throws IOException {
		// buffer[0] = 1
		byte[] buffer = new byte[20];
		buffer[0] = 1;

		for (int i = 0; i < qtde.length(); i++) {
			buffer[i + 1] = (byte) qtde.codePointAt(i);

		}
		out.write(buffer);
	}

	public static void SalvarMemLocal(String qtde) throws IOException {
		// buffer[0] = 2
		byte[] buffer = new byte[20];
		buffer[0] = 2;

		for (int i = 0; i < qtde.length(); i++) {
			buffer[i + 1] = (byte) qtde.codePointAt(i);
		}
		out.write(buffer);
	}
	
	public static void Conexao () throws IOException {
		byte [] buffer = new byte [1];
		buffer [0] = 3;
		
		out.write(buffer);
	}
	
	public static void validar () throws IOException {
		byte[] buffer = new byte [1];
		buffer [0] = 9;
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
					AnalizadorDoBuffer();

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void AnalizadorDoBuffer() {
		// ?????
		// String.trim() - remove espacos em branco de ambos os lados da string.
		int type = buffer[0] & 255;
		try {
			switch (type) {
			case 1:
				// passando o valor no buffer pra uma string codificada em utf-8
				byte[] string = Arrays.copyOfRange(buffer, 1, buffer.length);
				entidade.setCpu(Integer.parseInt(((new String(string, "UTF-8")).trim())));
				//TelaPrincipal.CpuLocal.setText();

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
