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


public class AutoDel extends JDialog {
	private Autotable atable;
	private JTable table;
	private Checker check = new Checker(); 
	private AutoMethods dbm = new AutoMethods(); 
	private final JPanel contentPanel = new JPanel(); 
	public AutoDel(JFrame f, Autotable b_atable){
		super (f, "Autók törlése", true);
		atable = b_atable;
	
		
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					dispose();
			}
		});
		btnBezr.setBounds(324, 216, 117, 23);
		getContentPane().add(btnBezr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 31, 452, 150);
		getContentPane().add(scrollPane);
		
		table = new JTable(atable);
		scrollPane.setViewportView(table);
		
		JButton btnAdatTrlse = new JButton("T\u00F6rl\u00E9s");
		btnAdatTrlse.setBounds(85, 216, 129, 23);
		btnAdatTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<atable.getRowCount();x++)
					if ((Boolean)atable.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) check.SM("Nincs kijelölve törlendõ rekord!", 0);
					if (db>1) check.SM("Több rekord van kijelölve! (Egyszerre csak egy törölhetõ!)", 0); 
					if (db==1) {
						String ID= atable.getValueAt(Jel, 1).toString();
						dbm.Connect();
						dbm.DelData(ID);
						dbm.DisConnect();
						atable.removeRow(Jel);
						check.SM("A rekord törölve",1);
					}
			}
		});
		getContentPane().add(btnAdatTrlse);
		contentPanel.setLayout(null);
		
		
		TableColumn tc = null; 
		for (int i = 0; i < 6; i++){
			tc = table.getColumnModel().getColumn(i);
			if (i==0 || i==1) tc.setPreferredWidth(30);
			else if (i==5) tc.setPreferredWidth(60);
			else if (i==2 || i==4) tc.setPreferredWidth(90);
			else {tc.setPreferredWidth(140);
			table.setAutoCreateRowSorter(true);
			TableRowSorter<Autotable> trs = (TableRowSorter<Autotable>)table.getRowSorter();
			trs.setSortable(0, false);
		}
			
		}
		}
	

}

