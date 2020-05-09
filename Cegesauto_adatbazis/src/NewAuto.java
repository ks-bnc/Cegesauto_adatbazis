import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewAuto extends JDialog {
	private JTextField ID;
	private JTextField Rendszam;
	private JTextField Tipus;
	private JTextField Forgalmi;
	private JTextField Fogyasztas;
	private AutoMethods dbm = new AutoMethods();
	
	public NewAuto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(10, 32, 46, 14);
		getContentPane().add(lblID);
		
		ID = new JTextField();
		ID.setBounds(106, 31, 86, 20);
		getContentPane().add(ID);
		ID.setColumns(10);
		
		JLabel lblRendszm = new JLabel("Rendsz\u00E1m:");
		lblRendszm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRendszm.setBounds(10, 70, 70, 20);
		getContentPane().add(lblRendszm);
		
		Rendszam = new JTextField();
		Rendszam.setBounds(106, 70, 86, 20);
		getContentPane().add(Rendszam);
		Rendszam.setColumns(10);
		
		JLabel lblTpus = new JLabel("T\u00EDpus:");
		lblTpus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTpus.setBounds(10, 114, 70, 20);
		getContentPane().add(lblTpus);
		
		Tipus = new JTextField();
		Tipus.setBounds(106, 114, 86, 20);
		getContentPane().add(Tipus);
		Tipus.setColumns(10);
		
		JLabel lblForgalmi = new JLabel("Forgalmi:");
		lblForgalmi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForgalmi.setBounds(10, 156, 86, 14);
		getContentPane().add(lblForgalmi);
		
		Forgalmi = new JTextField();
		Forgalmi.setBounds(106, 156, 86, 20);
		getContentPane().add(Forgalmi);
		Forgalmi.setColumns(10);
		
		JLabel lblFogyaszts = new JLabel("Fogyaszt\u00E1s:");
		lblFogyaszts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFogyaszts.setBounds(10, 198, 86, 20);
		getContentPane().add(lblFogyaszts);
		
		Fogyasztas = new JTextField();
		Fogyasztas.setBounds(106, 198, 86, 20);
		getContentPane().add(Fogyasztas);
		Fogyasztas.setColumns(10);
		
		JButton btnBeszur = new JButton("Besz\u00FAr\u00E1s");
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Checker c = new Checker();
				if (c.goodInt(ID, "ID"))
					if(c.filled (Rendszam, "Rendszam"))
						if (c.filled (Tipus, "Tipus"))
							if (c.goodDate (Forgalmi, "Forgalmi"))
								if (c.goodInt (Fogyasztas, "Fogyasztas")){
				dbm.Connect();
				dbm.InsertData(RTF(ID), RTF(Rendszam), RTF(Tipus), RTF(Forgalmi), RTF(Fogyasztas));
				dbm.DisConnect();	
				}
			}
		});
		btnBeszur.setBounds(296, 115, 89, 23);
		getContentPane().add(btnBeszur);
	}
		
		
		public String RTF(JTextField jtf){
		return jtf.getText();
		}
}
