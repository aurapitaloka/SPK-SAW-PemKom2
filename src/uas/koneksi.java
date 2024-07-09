package uas;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Aura Pitaloka
 */
public class koneksi {

    // Metode untuk menghubungkan ke database
    public static Connection hubungkan() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setDatabaseName("uas-pemkom2");
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setPortNumber(3306);
            dataSource.setServerName("localhost");
            dataSource.setServerTimezone("Asia/Jakarta");

            Connection connection = dataSource.getConnection();
            System.out.println("Sukses terhubung!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Gagal terhubung!");
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Gagal terhubung ke database", e);
        }
    }

    // Metode untuk mengambil data dari tabel kriteria
    public static ResultSet ambildata(String sql) {
        try {
            Connection conn = hubungkan();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data!");
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Gagal mengambil data dari database", e);
        }
    }

    public static void simpandata(String sql) throws SQLException {
        Connection conn = hubungkan();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
    }

}
