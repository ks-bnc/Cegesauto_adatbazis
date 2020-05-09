import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;


public class AutoMethods {
	private Statement s = null; 
	private Connection conn = null;
	private ResultSet rs = null; 
	
	public void Reg(){
		try{
			Class.forName("org.sqlite.JDBC");
			//SM("Sikeres driver regisztráció!", 1);
		}
		catch (ClassNotFoundException e){
			SM("Hibás driver regisztráció!"+e.getMessage(), 0);
		}
	}
	
	public void SM (String msg, int tipus){
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public void Connect(){
		try{
			String url = "jdbc:sqlite:C:/jdbc_uj/Cegesauto";
			conn = DriverManager.getConnection(url);
			//SM("Connection OK!", 1);
		}catch (SQLException e){
			SM("JDBC Connect: "+e.getMessage(), 0);}
	}
	public void DisConnect(){
		try{
			conn.close();
			//SM("DisConnection OK!", 1);
		}catch (SQLException e){SM(e.getMessage(), 0);}
	}
	
	public Autotable ReadData() {
		Object emptmn[] = {"Jel", "ID", "Rendszam", "Tipus", "Forgalmi", "Fogyasztas"};
		Autotable etm = new Autotable(emptmn, 0);
		String rendszam="", tipus="", forgalmi="", x="\t"; 
		int id=0, fogyasztas=0; 
		String sqlp= "Select id, rendszam, tipus, forgalmi, fogyasztas from Cegesauto";
		try{
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()){
				id = rs.getInt("id");
				rendszam = rs.getString("rendszam");
				tipus = rs.getString("tipus");
				forgalmi = rs.getString("forgalmi");
				fogyasztas = rs.getInt("fogyasztas");
				etm.addRow(new Object[] {false, id, rendszam, tipus, forgalmi, fogyasztas});
				System.out.println(id+x+rendszam+x+tipus+x+forgalmi+x+fogyasztas);
			}
			rs.close();
		}catch (SQLException e) {SM(e.getMessage(), 0);
	
		}
		return etm;
	}
	public void InsertData(String id, String rendszam, String tipus, String forgalmi, String fogyasztas ) {
		
		try (PreparedStatement statement = conn.prepareStatement("insert into Cegesauto values(?,?,?,?,?)")) {
			statement.setString(1, id);
			statement.setString(2, rendszam);
			statement.setString(3, tipus);
			statement.setString(4, forgalmi);
			statement.setString(5, fogyasztas);
			statement.executeUpdate();
			SM("Új jármû sikeresen létrehozva!",1);
			} catch (SQLException e) {
			SM("JDBC insert: " + e.getMessage(), 0);
			}

		
	}
	public void DelData (String ID){
		String sqlp = "delete from Cegesauto where ID="+ ID; 
		try{
			s = conn.createStatement();
			s.execute(sqlp);
		}catch (SQLException e){
			SM("JDBC Delete: "+e.getMessage(), 0);
		}
	}
	public void UpdateData (String ID, String mnev, String madat ){
		String sqlp = "update Cegesauto set "+mnev+ "='" +madat+"'where ID="+ID;
		try{
			s = conn.createStatement();
			s.execute(sqlp);
		}catch (SQLException e){
			SM("JDBC Update: "+e.getMessage(),0);
		}
	}

}
