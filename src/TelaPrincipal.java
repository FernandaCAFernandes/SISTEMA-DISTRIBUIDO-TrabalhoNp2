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
	public static JTextField cederQtde;
	public static JTextField receberQtde;
	public static JTextField randomDoor;

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
//		System.out.println("Tela: " + qtdeTotalCpu + " // " + qtdeTotalMemo);

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

		JLabel lblBloqueiados = new JLabel("Bloqueiados");
		lblBloqueiados.setBounds(38, 120, 83, 16);
		contentPane.add(lblBloqueiados);

		JLabel lblRecursosReservados = new JLabel("Recursos Reservados");
		lblRecursosReservados.setBounds(357, 120, 129, 16);
		contentPane.add(lblRecursosReservados);

		JLabel lblCpu = new JLabel("CPU:");
		lblCpu.setBounds(12, 149, 56, 16);
		contentPane.add(lblCpu);

		cpuBloqueado = new JTextField();
		cpuBloqueado.setEditable(false);
		cpuBloqueado.setBounds(80, 149, 61, 22);
		contentPane.add(cpuBloqueado);
		cpuBloqueado.setColumns(10);

		JLabel lblMemoria = new JLabel("Memoria:");
		lblMemoria.setBounds(12, 178, 56, 16);
		contentPane.add(lblMemoria);

		memoriaBloqueado = new JTextField();
		memoriaBloqueado.setEditable(false);
		memoriaBloqueado.setBounds(80, 184, 61, 22);
		contentPane.add(memoriaBloqueado);
		memoriaBloqueado.setColumns(10);

		JLabel lblCpu_1 = new JLabel("CPU:");
		lblCpu_1.setBounds(341, 149, 56, 16);
		contentPane.add(lblCpu_1);

		JLabel lblMemoria_1 = new JLabel("Memoria:");
		lblMemoria_1.setBounds(341, 187, 56, 16);
		contentPane.add(lblMemoria_1);

		cpuReservado = new JTextField();
		cpuReservado.setEditable(false);
		cpuReservado.setBounds(409, 146, 61, 22);
		contentPane.add(cpuReservado);
		cpuReservado.setColumns(10);

		memoriaReservado = new JTextField();
		memoriaReservado.setEditable(false);
		memoriaReservado.setBounds(409, 184, 61, 22);
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
		lblCpuTotal.setBounds(282, 13, 96, 16);
		contentPane.add(lblCpuTotal);

		JLabel lblNewLabel = new JLabel("Memoria Total:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(282, 42, 111, 16);
		contentPane.add(lblNewLabel);

		CpuTotal = new JTextField();
		CpuTotal.setEditable(false);
		CpuTotal.setBounds(395, 10, 116, 22);
		contentPane.add(CpuTotal);
		CpuTotal.setColumns(10);

		memoriaTotal = new JTextField();
		memoriaTotal.setEditable(false);
		memoriaTotal.setBounds(395, 39, 116, 22);
		contentPane.add(memoriaTotal);
		memoriaTotal.setColumns(10);

		JLabel lblReceber = new JLabel("Receber");
		lblReceber.setBounds(399, 225, 56, 16);
		contentPane.add(lblReceber);

		JLabel lblDar = new JLabel("Ceder");
		lblDar.setBounds(80, 225, 56, 16);
		contentPane.add(lblDar);

		JCheckBox cpuCederCheck = new JCheckBox("CPU");
		cpuCederCheck.setBounds(100, 250, 113, 25);
		contentPane.add(cpuCederCheck);

		JCheckBox memoriaCederCheck = new JCheckBox("Memoria");
		memoriaCederCheck.setBounds(100, 280, 113, 25);
		contentPane.add(memoriaCederCheck);

		cederQtde = new JTextField();
		cederQtde.setBounds(27, 254, 63, 22);
		contentPane.add(cederQtde);
		cederQtde.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(104, 314, 97, 25);
		contentPane.add(btnOk);

		receberQtde = new JTextField();
		receberQtde.setBounds(341, 251, 73, 22);
		contentPane.add(receberQtde);
		receberQtde.setColumns(10);

		JCheckBox cpuReceberCheck = new JCheckBox("CPU");
		cpuReceberCheck.setBounds(433, 250, 113, 25);
		contentPane.add(cpuReceberCheck);

		JCheckBox memoriaReceberCheck = new JCheckBox("Memoria");
		memoriaReceberCheck.setBounds(433, 280, 113, 25);
		contentPane.add(memoriaReceberCheck);

		JButton btnOk_1 = new JButton("Ok");
		btnOk_1.setBounds(437, 314, 97, 25);
		contentPane.add(btnOk_1);

		JButton btnLiberarCedido = new JButton("Liberar ");
		btnLiberarCedido.setBounds(0, 314, 97, 25);
		contentPane.add(btnLiberarCedido);

		JButton btnLiberarRecebido = new JButton("Liberar");
		btnLiberarRecebido.setBounds(328, 314, 97, 25);
		contentPane.add(btnLiberarRecebido);

		randomDoor = new JTextField();
		randomDoor.setEditable(false);
		randomDoor.setBounds(213, 315, 96, 22);
		contentPane.add(randomDoor);
		randomDoor.setColumns(10);

	}
}
