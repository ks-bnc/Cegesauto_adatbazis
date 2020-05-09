import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;


public class Program extends JFrame {

	private JPanel contentPane;
	private AutoMethods dbm = new AutoMethods();
	private Autotable atable;
	private Checker check = new Checker();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	/**
	 * Create the frame.
	 */

	
	
	public Program() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Aut\u00F3k list\u00E1ja");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbm.Connect();
				atable = dbm.ReadData();
				dbm.DisConnect();
				Autolista el = new Autolista(Program.this, atable);
				el.setVisible(true);
			}
		});
		btnLista.setBounds(24, 29, 195, 23);
		contentPane.add(btnLista);
		
		Object emptmn[] = {"Jel", "ID", "Rendszam", "Tipus", "Forgalmi", "Fogyasztas" };
		atable = new Autotable(emptmn, 0);
		
		JButton btnUjAdat = new JButton("\u00DAj adatsor");
		btnUjAdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewAuto ne = new NewAuto();
				ne.setVisible(true);
			}
		});
		btnUjAdat.setBounds(24, 76, 195, 23);
		contentPane.add(btnUjAdat);
		
		JButton btnTrls = new JButton("Aut\u00F3 t\u00F6rl\u00E9se");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dbm.Connect();
				atable = dbm.ReadData();
				dbm.DisConnect();
				AutoDel ed = new AutoDel(Program.this, atable);
				ed.setVisible(true);
				
			}
		});
		btnTrls.setBounds(24, 123, 195, 23);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("Aut\u00F3 m\u00F3dos\u00EDt\u00E1sa");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbm.Connect();
				atable = dbm.ReadData();
				dbm.DisConnect();
				AutoMod em = new AutoMod(Program.this, atable);
				em.setVisible(true);
			}
		});
		btnMdosts.setBounds(24, 171, 195, 23);
		contentPane.add(btnMdosts);
		
		JTextPane txtpnAzAlbbiProgram = new JTextPane();
		txtpnAzAlbbiProgram.setBackground(UIManager.getColor("Panel.background"));
		txtpnAzAlbbiProgram.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 13));
		txtpnAzAlbbiProgram.setText("Az al\u00E1bbi program a K\u00DCber Kft. c\u00E9gesaut\u00F3 adatb\u00E1zis\u00E1nak kezel\u00E9s\u00E9re k\u00E9sz\u00FClt. \rK\u00E9rem, v\u00E1lasszon az al\u00E1bbi opci\u00F3k k\u00F6z\u00FCl!");
		txtpnAzAlbbiProgram.setBounds(229, 65, 195, 149);
		contentPane.add(txtpnAzAlbbiProgram);
		
	


}
}


