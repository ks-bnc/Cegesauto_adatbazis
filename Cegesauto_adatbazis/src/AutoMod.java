import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class AutoMod extends JDialog {
	private Autotable atable;
	private JTable table;
	private Checker check = new Checker(); 
	private AutoMethods dbm = new AutoMethods(); 
	private final JPanel contentPanel = new JPanel(); 
	private JTextField ID;
	private JTextField Rendszam;
	private JTextField Tipus;
	private JTextField Forgalmi;
	private JTextField Fogyasztas;
	
	public AutoMod(JFrame f, Autotable b_atable){
		super (f, "Autók módosítása", true);
		atable = b_atable;
			
		setBounds(100, 100, 450, 430);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBezr.setBounds(305, 360, 89, 23);
		getContentPane().add(btnBezr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 32, 387, 256);
		getContentPane().add(scrollPane);
		
		table = new JTable(atable);
		scrollPane.setViewportView(table);
		
		JButton btnMdosts = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		int db=0, jel=0, x=0;
		for (x=0; x<atable.getRowCount(); x++)
			if ((Boolean)atable.getValueAt(x,0)) {db++; jel=x;}
		if (db==0) check.SM("Nincs kijelölve a módosító rekord!",0);
		if (db>1) check.SM("Több rekord van kijelölve!\nEgyszerre csak egy"+ " rekord módosítható!",0);
		if (db==1){
			if (modDataPc()>0){
				boolean ok= true;
				if (check.filled(ID)) ok = check.goodInt(ID, "ID");
				if (ok && check.filled(Fogyasztas)) ok = check.goodInt(Fogyasztas, "Fogyasztas");
				if (ok){
					
					String mkod = atable.getValueAt(jel, 1).toString();
					dbm.Connect();
					if (check.filled(ID)) dbm.UpdateData(mkod, "ID", check.RTF(ID));
					if (check.filled(Rendszam)) dbm.UpdateData(mkod, "Rendszam", check.RTF(Rendszam));
					if (check.filled(Tipus)) dbm.UpdateData(mkod, "Tipus", check.RTF(Tipus));
					if (check.filled(Forgalmi)) dbm.UpdateData(mkod, "Forgalmi", check.RTF(Forgalmi));
					if (check.filled(Fogyasztas)) dbm.UpdateData(mkod, "Fogyasztas", check.RTF(Fogyasztas));
					dbm.DisConnect();
					
					if (check.filled(ID)) atable.setValueAt(check.stringToInt(check.RTF(ID)), jel, 1);
					if (check.filled(Rendszam)) atable.setValueAt(check.RTF(Rendszam), jel, 2);
					if (check.filled(Tipus)) atable.setValueAt(check.RTF(Tipus), jel, 3);
					if (check.filled(Forgalmi)) atable.setValueAt(check.RTF(Forgalmi), jel, 4);
					if (check.filled(Fogyasztas)) atable.setValueAt(check.stringToInt(check.RTF(Fogyasztas)), jel, 5);
					check.SM("A rekord módosítva!",1);
				}
				else {
					check.SM("Nincs kitöltve egyetlen módosító adatmezõ sem!", 0);
				}
			}
		}
		
			}
		});
		btnMdosts.setBounds(49, 360, 89, 23);
		getContentPane().add(btnMdosts);
		
		ID = new JTextField();
		ID.setBounds(15, 327, 41, 20);
		getContentPane().add(ID);
		ID.setColumns(10);
		
		Rendszam = new JTextField();
		Rendszam.setBounds(66, 327, 72, 20);
		getContentPane().add(Rendszam);
		Rendszam.setColumns(10);
		
		Tipus = new JTextField();
		Tipus.setBounds(148, 327, 96, 20);
		getContentPane().add(Tipus);
		Tipus.setColumns(10);
		
		Forgalmi = new JTextField();
		Forgalmi.setBounds(254, 327, 86, 20);
		getContentPane().add(Forgalmi);
		Forgalmi.setColumns(10);
		
		Fogyasztas = new JTextField();
		Fogyasztas.setBounds(350, 329, 62, 20);
		getContentPane().add(Fogyasztas);
		Fogyasztas.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 310, 24, 14);
		getContentPane().add(lblId);
		
		JLabel lblRendszm = new JLabel("Rendsz\u00E1m");
		lblRendszm.setBounds(78, 310, 60, 14);
		getContentPane().add(lblRendszm);
		
		JLabel lblTpus = new JLabel("T\u00EDpus");
		lblTpus.setBounds(183, 310, 46, 14);
		getContentPane().add(lblTpus);
		
		JLabel lblForgalmi = new JLabel("Forgalmi");
		lblForgalmi.setBounds(276, 310, 62, 14);
		getContentPane().add(lblForgalmi);
		
		JLabel lblFogaszts = new JLabel("Fogyaszt\u00E1s");
		lblFogaszts.setBounds(353, 310, 74, 14);
		getContentPane().add(lblFogaszts);
	}
	
	
		public int modDataPc(){
			int pc =  0; 
			if (check.filled(ID)) pc++;
			if (check.filled(Rendszam)) pc++;
			if (check.filled(Tipus)) pc++;
			if (check.filled(Forgalmi)) pc++;
			if (check.filled(Fogyasztas)) pc++;
			return pc;
		}
		{
			
		}
		
	public void reset (int i){
		ID.setText("");
		Rendszam.setText("");
		Tipus.setText("");
		Forgalmi.setText("");
		Fogyasztas.setText("");
		atable.setValueAt(false, i, 0);
	}
}

