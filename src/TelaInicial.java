import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField ip;
	public static JTextField porta;


	public Gerenciador gerenciador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
					//Random r = new Random();
					//String sp = String.format("%04d", r.nextInt(10000));
					//port = Integer.parseInt(sp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(57, 59, 56, 16);
		contentPane.add(lblIp);

		JLabel lblPorta = new JLabel("PORTA");
		lblPorta.setBounds(57, 111, 56, 16);
		contentPane.add(lblPorta);

		ip = new JTextField();
		ip.setBounds(135, 56, 116, 22);
		contentPane.add(ip);
		ip.setColumns(10);

		porta = new JTextField();
		porta.setBounds(135, 108, 116, 22);
		contentPane.add(porta);
		porta.setColumns(10);

		JButton btnLogar = new JButton("CONECTAR");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnLogar) {
					
					
					gerenciador = new Gerenciador(ip.getText());
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);	
					gerenciador.startup(ip.getText());
					
					System.out.println(gerenciador.porta);
					telaPrincipal.randomDoor.setText(Integer.toString(gerenciador.porta));
					
					dispose();
					
				}

			}
		});
		btnLogar.setBounds(268, 161, 97, 25);
		contentPane.add(btnLogar);
	}
}
