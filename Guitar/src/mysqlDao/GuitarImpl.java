package mysqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DbUtil;
import dao.IGuitarDao;
import model.*;

public class GuitarImpl implements IGuitarDao{
	
	@Override
	public boolean LoadGuitars(){
		Inventory inventory = new Inventory();
		// ≥ı ºªØinventory
		String sql = "select * from Guitars";
		Connection Conn = DbUtil.getMySqlConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Builder builder = Builder.valueOf(rs.getString("builder").toUpperCase());
				String model = rs.getString("model");
				Type type = Type.valueOf(rs.getString("type").toUpperCase());
				int numStrings = Integer.parseInt(rs.getString("numStrings"));
				Wood backWood = Wood.valueOf(rs.getString("backWood").toUpperCase());
				Wood topWood = Wood.valueOf(rs.getString("topWood").toUpperCase());
				GuitarSpec Spec = new GuitarSpec(builder, model, type, numStrings, backWood, topWood);
				inventory.addGuitar(rs.getString("serialNumber"), Double.parseDouble(rs.getString("price")), Spec);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return true;
	}

	@Override
	public boolean addGuitar(String serialNumber, double price, GuitarSpec spec) {
		Connection Conn = DbUtil.getMySqlConnection();
		String sql = "insert into Guitars(serialNumber,builder,model,type,backWood,topWood,price) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			pstmt.setString(1, serialNumber);
			pstmt.setString(2, spec.getBuilder().toString());
			pstmt.setString(3, spec.getModel());
			pstmt.setString(4, spec.getType().toString());
			pstmt.setString(5, spec.getBackWood().toString());
			pstmt.setString(6, spec.getTopWood().toString());
			pstmt.setDouble(7, price);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	

	
	@Override
	public void deleteGuitar(String serialNumber){
		Connection conn = DbUtil.getMySqlConnection();
		String sql = "delete from Guitars where serialNumber='"+serialNumber+"'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("…æ≥˝“Ï≥££∫"+e.getMessage());
		}
	}
}
