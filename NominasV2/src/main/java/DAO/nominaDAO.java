package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.DBUtils;
import excepciones.DatosNoCorrectosException;

public class nominaDAO {	
	
	public double obtenerSalario(String dni) throws SQLException, DatosNoCorrectosException {
	    String consultaSQL = "SELECT sueldo FROM nominas WHERE dni_empleado = ? ";
	    
	    try (Connection connection = DBUtils.getConnection();
	         PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

	        // Asigna el valor del parámetro DNI
	        statement.setString(1, dni);

	        try (ResultSet rs = statement.executeQuery()) {
	            if (rs.next()) {
	                return rs.getDouble("sueldo");
	            } else {
	                throw new DatosNoCorrectosException("No se encontró un salario para el DNI proporcionado");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        throw e; // Si deseas propagar la excepción
	    }
	}


}
