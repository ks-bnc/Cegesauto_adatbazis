import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;


public class Autolista extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Autotable atable;
	
	public Autolista(JFrame f, Autotable b_atable){
		super (f, "Autók listája", true);
		atable = b_atable;
	
		
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton btnBezar = new JButton("Bez\u00E1r");
			btnBezar.setBounds(192, 427, 89, 23);
			btnBezar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				dispose();
			}
			}
			);
			getContentPane().add(btnBezar);
		
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 484, 461);
		getContentPane().add(scrollPane);
		
		table = new JTable(atable);
		scrollPane.setViewportView(table);

	}
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

