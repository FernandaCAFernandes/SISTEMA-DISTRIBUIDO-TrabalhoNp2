import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	public static JPanel contentPane;
	public static JTextField cpuBloqueado;
	public static JTextField memoriaBloqueado;
	public static JTextField cpuReservado;
	public static JTextField memoriaReservado;
	public static JTextField CpuLocal;
	public static JTextField MemoriaLocal;
	public static JTextField CpuTotal;
	public static JTextField memoriaTotal;
	public static JTextField cederCpuQtde;
	public static JTextField receberCpuQtde;
	public static JTextField randomDoor;
	private JTextField cpuLiberada;
	private JTextField memoLiberada;
	private JTextField cederMemoQtde;
	private JTextField receberMemoQtde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void AttLabels(int qtdeTotalCpu, int qtdeTotalMemo) {
		// System.out.println("Tela: " + qtdeTotalCpu + " // " + qtdeTotalMemo);

		CpuTotal.setText(Integer.toString(qtdeTotalCpu));
		memoriaTotal.setText(Integer.toString(qtdeTotalMemo));

	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBloqueados = new JLabel("Bloqueados");
		lblBloqueados.setBounds(231, 120, 83, 16);
		contentPane.add(lblBloqueados);

		JLabel lblRecursosReservados = new JLabel("Recursos Reservados");
		lblRecursosReservados.setBounds(395, 120, 129, 16);
		contentPane.add(lblRecursosReservados);

		JLabel lblCpu = new JLabel("CPU:");
		lblCpu.setBounds(203, 149, 56, 16);
		contentPane.add(lblCpu);

		cpuBloqueado = new JTextField();
		cpuBloqueado.setEditable(false);
		cpuBloqueado.setBounds(273, 146, 61, 22);
		contentPane.add(cpuBloqueado);
		cpuBloqueado.setColumns(10);

		JLabel lblMemoria = new JLabel("Memoria:");
		lblMemoria.setBounds(203, 190, 56, 16);
		contentPane.add(lblMemoria);

		memoriaBloqueado = new JTextField();
		memoriaBloqueado.setEditable(false);
		memoriaBloqueado.setBounds(273, 187, 61, 22);
		contentPane.add(memoriaBloqueado);
		memoriaBloqueado.setColumns(10);

		JLabel lblCpu_1 = new JLabel("CPU:");
		lblCpu_1.setBounds(382, 149, 56, 16);
		contentPane.add(lblCpu_1);

		JLabel lblMemoria_1 = new JLabel("Memoria:");
		lblMemoria_1.setBounds(382, 187, 56, 16);
		contentPane.add(lblMemoria_1);

		cpuReservado = new JTextField();
		cpuReservado.setEditable(false);
		cpuReservado.setBounds(450, 146, 61, 22);
		contentPane.add(cpuReservado);
		cpuReservado.setColumns(10);

		memoriaReservado = new JTextField();
		memoriaReservado.setEditable(false);
		memoriaReservado.setBounds(450, 184, 61, 22);
		contentPane.add(memoriaReservado);
		memoriaReservado.setColumns(10);

		JLabel lblCpu_2 = new JLabel("CPU:");
		lblCpu_2.setBounds(12, 13, 56, 16);
		contentPane.add(lblCpu_2);

		JLabel lblMemoria_2 = new JLabel("Memoria:");
		lblMemoria_2.setBounds(12, 42, 56, 16);
		contentPane.add(lblMemoria_2);

		CpuLocal = new JTextField();
		CpuLocal.setBounds(80, 10, 61, 22);
		contentPane.add(CpuLocal);
		CpuLocal.setColumns(10);

		MemoriaLocal = new JTextField();
		MemoriaLocal.setBounds(80, 39, 61, 22);
		contentPane.add(MemoriaLocal);
		MemoriaLocal.setColumns(10);

		JButton btnSalvarQtde = new JButton("Salvar Qtde");
		btnSalvarQtde.setBounds(90, 73, 111, 25);
		contentPane.add(btnSalvarQtde);

		btnSalvarQtde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String qtdeCpu = CpuLocal.getText();
				String qtdeMemo = MemoriaLocal.getText();
				Gerenciador.changeCpuMemo(qtdeCpu, qtdeMemo);

			}
		});

		JLabel lblCpuTotal = new JLabel("CPU Total:");
		lblCpuTotal.setForeground(Color.RED);
		lblCpuTotal.setBounds(307, 13, 96, 16);
		contentPane.add(lblCpuTotal);

		JLabel lblNewLabel = new JLabel("Memoria Total:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(307, 42, 111, 16);
		contentPane.add(lblNewLabel);

		CpuTotal = new JTextField();
		CpuTotal.setEditable(false);
		CpuTotal.setBounds(430, 10, 116, 22);
		contentPane.add(CpuTotal);
		CpuTotal.setColumns(10);

		memoriaTotal = new JTextField();
		memoriaTotal.setEditable(false);
		memoriaTotal.setBounds(430, 39, 116, 22);
		contentPane.add(memoriaTotal);
		memoriaTotal.setColumns(10);

		JLabel lblReceber = new JLabel("Receber");
		lblReceber.setBounds(414, 225, 56, 16);
		contentPane.add(lblReceber);

		JLabel lblDar = new JLabel("Ceder");
		lblDar.setBounds(63, 225, 56, 16);
		contentPane.add(lblDar);

		JCheckBox cpuCederCheck = new JCheckBox("CPU");
		cpuCederCheck.setBounds(90, 250, 113, 25);
		contentPane.add(cpuCederCheck);

		JCheckBox memoriaCederCheck = new JCheckBox("Memoria");
		memoriaCederCheck.setBounds(88, 285, 113, 25);
		contentPane.add(memoriaCederCheck);

		cederCpuQtde = new JTextField();
		cederCpuQtde.setBounds(12, 254, 63, 22);
		contentPane.add(cederCpuQtde);
		cederCpuQtde.setColumns(10);

		JButton okCeder = new JButton("Ok");
		okCeder.setBounds(88, 319, 97, 25);
		contentPane.add(okCeder);
		okCeder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * String qtdeAuxCpu; String qtdeAuxMemo; liberar recurso test... erro comentar
				 * tudo :v if (cpuCederCheck.isSelected() == true &&
				 * memoriaCederCheck.isSelected() == false) { qtdeAuxCpu =
				 * cederCpuQtde.getText(); qtdeAuxMemo = Integer.toString(0);
				 * Gerenciador.changeCpuMemoLiberada(qtdeAuxCpu, qtdeAuxMemo);
				 * 
				 * } else { if (cpuCederCheck.isSelected() == false &&
				 * memoriaCederCheck.isSelected() == true) {
				 * 
				 * qtdeAuxCpu = Integer.toString(0); qtdeAuxMemo = cederMemoQtde.getText();
				 * Gerenciador.changeCpuMemoLiberada(qtdeAuxCpu, qtdeAuxMemo);
				 * 
				 * } else { if (cpuCederCheck.isSelected() == true &&
				 * memoriaCederCheck.isSelected() == true) { qtdeAuxCpu =
				 * cederCpuQtde.getText(); qtdeAuxMemo = cederMemoQtde.getText();
				 * Gerenciador.changeCpuMemoLiberada(qtdeAuxCpu, qtdeAuxMemo);
				 * 
				 * } } }
				 */
			}
		});

		receberCpuQtde = new JTextField();
		receberCpuQtde.setBounds(362, 254, 73, 22);
		contentPane.add(receberCpuQtde);
		receberCpuQtde.setColumns(10);

		JCheckBox cpuReceberCheck = new JCheckBox("CPU");
		cpuReceberCheck.setBounds(443, 250, 113, 25);
		contentPane.add(cpuReceberCheck);

		JCheckBox memoriaReceberCheck = new JCheckBox("Memoria");
		memoriaReceberCheck.setBounds(443, 280, 113, 25);
		contentPane.add(memoriaReceberCheck);

		JButton okReceber = new JButton("Ok");
		okReceber.setBounds(437, 314, 97, 25);
		contentPane.add(okReceber);

		randomDoor = new JTextField();
		randomDoor.setEditable(false);
		randomDoor.setBounds(231, 314, 96, 22);
		contentPane.add(randomDoor);
		randomDoor.setColumns(10);

		JLabel lblLivre = new JLabel("Livre");
		lblLivre.setBounds(45, 120, 56, 16);
		contentPane.add(lblLivre);

		JLabel cpuLivre = new JLabel("CPU:");
		cpuLivre.setBounds(12, 149, 56, 16);
		contentPane.add(cpuLivre);

		JLabel memoriaLivre = new JLabel("Memoria:");
		memoriaLivre.setBounds(12, 190, 56, 16);
		contentPane.add(memoriaLivre);

		cpuLiberada = new JTextField();
		cpuLiberada.setEditable(false);
		cpuLiberada.setBounds(80, 146, 61, 22);
		contentPane.add(cpuLiberada);
		cpuLiberada.setColumns(10);

		memoLiberada = new JTextField();
		memoLiberada.setEditable(false);
		memoLiberada.setBounds(80, 187, 61, 22);
		contentPane.add(memoLiberada);
		memoLiberada.setColumns(10);

		cederMemoQtde = new JTextField();
		cederMemoQtde.setBounds(12, 286, 61, 22);
		contentPane.add(cederMemoQtde);
		cederMemoQtde.setColumns(10);

		receberMemoQtde = new JTextField();
		receberMemoQtde.setBounds(362, 281, 72, 22);
		contentPane.add(receberMemoQtde);
		receberMemoQtde.setColumns(10);

	}
}
