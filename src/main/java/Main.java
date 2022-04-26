import java.sql.*;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/lab1?createDatabaseIfNotExist=true";
		String getCompanySharesSQL = "SELECT val FROM shares WHERE company = ?";
		String getCompanyNameSQL = "SELECT name FROM company WHERE name = ?";
		String putCompanyNameSQL = "INSERT INTO company(name) VALUES (?)";
		String putCompanySharesSQL = "INSERT INTO shares(val,company) VALUES (?,?)";
		try {
			Connection con = DriverManager.getConnection(url, "root", "root");
			PreparedStatement getCompanyShares = con.prepareStatement(getCompanySharesSQL);
			PreparedStatement getCompanyName = con.prepareStatement(getCompanyNameSQL);
			getCompanyShares.setString(1,"RandCo");
			getCompanyName.setString(1,"RandCo");
			ResultSet rs1 = getCompanyName.executeQuery();
			rs1.next();
			String name = rs1.getString("name");
			Company c = new Company(name);
			ResultSet rs2 = getCompanyShares.executeQuery();
			Portfolio p = new Portfolio();
			while (rs2.next()) {
				int val = rs2.getInt("val");
				Share s = new Share(val,c);
				p.addShare(s);
			}
			System.out.println(p.computeSum());
			
			PreparedStatement putCompanyShares = con.prepareStatement(putCompanySharesSQL);
			PreparedStatement putCompanyName = con.prepareStatement(putCompanyNameSQL);
			
			putCompanyName.setString(1, "RivalCo");
			putCompanyName.executeUpdate();
			putCompanyShares.setInt(1, 400);
			putCompanyShares.setString(2,  "RivalCo");
			putCompanyShares.executeUpdate();
			putCompanyShares.setInt(1, 600);
			putCompanyShares.executeUpdate();
			putCompanyShares.setInt(1, 100);
			putCompanyShares.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
