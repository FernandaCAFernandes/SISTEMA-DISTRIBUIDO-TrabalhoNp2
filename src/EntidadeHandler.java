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

	public static void conexao() throws IOException {
		// codigo para iniciar conexao da entidade que acabou
		// de entrar com todas as outros conectados no server q a entidade entrou;
		byte[] buffer = new byte[1];
		buffer[0] = 3;

		out.write(buffer);

	}

	public static void validar() throws IOException {
		// validar que a conexao esta estabelecida com somatorio
		byte[] buffer = new byte[1];
		buffer[0] = 9;
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
				// ler o buffer, pega o conteudo do buffer, salva na variavel da entidade seta
				// no jTextField;
				byte[] stringCpu = Arrays.copyOfRange(buffer, 1, buffer.length);
				entidade.setCpu(Integer.parseInt(((new String(stringCpu, "UTF-8")).trim())));
				TelaPrincipal.CpuLocal.setText(Integer.toString(entidade.getCpu()));
				break;
			case 2:
				// msm coisa
				byte[] stringMemo = Arrays.copyOfRange(buffer, 1, buffer.length);
				entidade.setMemoria(Integer.parseInt(((new String(stringMemo, "UTF-8")).trim())));
				TelaPrincipal.MemoriaLocal.setText(Integer.toString(entidade.getMemoria()));
				break;
			case 3:
				// ip 15 (123.123.123.123) porta 4( 1 2 3 4)
				// pega ip e porta de cada entidade conectada ao servidor q entidade nova se
				// conectou;
				// - 1 nao conta a entidade que entrou?
				int auxIpOuPorta = 0;
				byte[] buffer = new byte[Gerenciador.entidades.size() * 19 + 1];
				// codigo retornando os ips/portas
				buffer[0] = 4;
				int j = 0;

				// right?

				for (int i = 0; i < Gerenciador.entidades.size(); i++) {
					String aux = Gerenciador.entidades.get(i).ip + Gerenciador.entidades.get(i).porta;
					for (; j < Gerenciador.entidades.size() * 19;) {
						buffer[j + 1] = (byte) aux.codePointAt(j);
						j++;
						if (j % 19 == 0) {
							break;
						}

						// pra cada byte do aux add ip porta a string.

					}

				}
				// out.write(buffer); ?

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
