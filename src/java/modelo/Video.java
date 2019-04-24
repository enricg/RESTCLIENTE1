/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.JdbcDerbyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.*;
import java.util.Random;

public class Video {


    private int buscaNumRep(int videoid) throws SQLException {
        Connection connection = JdbcDerbyConnection.ConexionDB();
        int numrep = 0;
        String sql = "SELECT reproducciones FROM VIDEOS WHERE ID =" + videoid;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                numrep = rs.getInt("reproducciones");
            }
            pstmt.close();
            return numrep;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Error");
        }
    }

    public boolean addNumRep(int videoid) throws SQLException {
        Connection connection = JdbcDerbyConnection.ConexionDB();

        int newnumrep = buscaNumRep(videoid) + 1;

        String sql = "UPDATE VIDEOS SET REPRODUCCIONES = " + newnumrep + "WHERE ID = " + videoid;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
