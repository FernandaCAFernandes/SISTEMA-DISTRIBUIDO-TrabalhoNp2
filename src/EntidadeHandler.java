import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
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
		entidade = new Entidade(0, 0);

		// se n tiver o in/out da nullpointer exception no in size;
		try {
			in = this.socket.getInputStream();
			out = this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// sender/ buffer :
	// salver cpu/memoria
	// calcular total cpu / memoria
	// informar bloqueio
	// informar recursos reservados
	// [1]- tipo de msg ([2] - se memoria ou cpu?) [18] - qtde ??

	public void SalvarCpuMemLocal(String cpu, String mem) throws IOException {
		String qtde = cpu + "-" + mem;
		byte[] buffer = new byte[1 + qtde.length()];
		buffer[0] = 1;
		for (int i = 0; i < qtde.length(); i++) {
			buffer[i + 1] = (byte) qtde.codePointAt(i);
		}

		out.write(buffer);
	}

	public void conexao() throws IOException {
		// codigo para iniciar conexao da entidade que acabou
		// de entrar com todas as outros conectados no server q a entidade entrou;
		buffer = new byte[1];
		buffer[0] = 3;

		out.write(buffer);

	}

	public void validar() throws IOException {
		// validar que a conexao esta estabelecida com somatorio
		buffer = new byte[1];
		buffer[0] = 2;

		out.write(buffer);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int size = 0;
		try {
			while (!stop) {

				size = in.available();

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
		byte[] message = null;
		String[] tokenized = null;
		String strMsg = null;
		try {
			switch (type) {
			case 1:
				// passando o valor no buffer pra uma string codificada em utf-8
				// ler o buffer, pega o conteudo do buffer, salva na variavel da entidade seta
				// no jTextField;
				// diminuir o array pra evitar o erro
				message = Arrays.copyOfRange(buffer, 1, buffer.length);

				// System.out.println("Cpu REcebida: " +stringCpu[0]);

				tokenized = new String(message, "UTF-8").trim().split("-");

				entidade.setCpu(Integer.parseInt(tokenized[0]));
				entidade.setMemoria(Integer.parseInt(tokenized[1]));
				// TelaPrincipal.CpuLocal.setText(Integer.toString(entidade.getCpu()));
				break;



			case 3:
				// ip 15 (123.123.123.123) porta 4( 1 2 3 4)
				// pega ip e porta de cada entidade conectada ao servidor q entidade nova se
				// conectou;
				// - 1 nao conta a entidade que entrou?
				int auxIpOuPorta = 0;
				byte[] bufferConec = new byte[Gerenciador.entidades.size() * 19 + 1];
				// codigo retornando os ips/portas

				buffer[0] = 4;

				for (int i = 0; i < Gerenciador.entidades.size() - 1; i++) {

					if (this.equals(Gerenciador.entidades.get(i))) {
						continue;
					}
					String aux = Gerenciador.entidades.get(i).ip + Gerenciador.entidades.get(i).porta;
					for (int j = i * 19; j < i * 19; j++) {
						bufferConec[j + 1] = (byte) aux.codePointAt(j);

					}

				}
				out.write(bufferConec);
				break;

			case 4:
				// lere bufffer e chamar metodo p/ AddEntidade

				byte[] stringIp = new byte[15];
				byte[] stringPorta = new byte[4];
				String ip;
				int port;
				int countIp = 15;
				int countPort = 4;
				for (int i = 0; i < Gerenciador.entidades.size(); i++) {
					stringIp = Arrays.copyOfRange(buffer, 1, countIp);
					stringPorta = Arrays.copyOfRange(buffer, countIp + 1, countIp + countPort + 1);
					countIp = countIp + countIp;
					countPort = countPort + countPort;
					ip = new String(stringIp, "UTF-8").trim();
					port = Integer.parseInt(new String(stringPorta, "UTF-8").trim());
					Socket s = new Socket();
					s.connect(new InetSocketAddress(ip, port));

					System.out.println("Eu n deveria aparecer meh :v");
					Gerenciador.addEntidade(s);

				}

				break;

			default:
				break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
