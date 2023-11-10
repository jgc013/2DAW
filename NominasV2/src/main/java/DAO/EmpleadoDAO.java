package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.DBUtils;
import excepciones.DatosNoCorrectosException;
import model.Empleado;
import model.Nomina;

public class EmpleadoDAO {
	public List<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {
		List<Empleado> listaEmpleados = new ArrayList<>();

		String consultaSQL = "SELECT * FROM empleados WHERE eliminado = '0'";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String sexo = rs.getString("sexo");
				int categoria = rs.getInt("categoria");
				int anyos = rs.getInt("anyos");

				Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
				listaEmpleados.add(empleado);
			}
			for (Empleado empleado : listaEmpleados) {
				System.out.println(empleado);
			}
			System.out.println("La consulta se ejecutó correctamente. La lista de empleados está vacía: "
					+ listaEmpleados.isEmpty());

		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			throw e; // Si deseas propagar la excepción
		}

		return listaEmpleados;
	}

	public Empleado obtenerEmpleadoPorDNI(String dni) throws Exception {

		String consultaSQL = "SELECT * FROM empleados WHERE dni = ? AND eliminado = '0'";
		Empleado empleado = null;

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			// Asigna el valor del parámetro DNI
			statement.setString(1, dni);

			try (ResultSet rs = statement.executeQuery()) {
				// Verifica si hay resultados
				if (rs.next()) {
					String dniE = rs.getString("dni");
					String nombre = rs.getString("nombre");
					String sexo = rs.getString("sexo");
					int categoria = rs.getInt("categoria");
					int anyos = rs.getInt("anyos");

					empleado = new Empleado(nombre, dniE, sexo, categoria, anyos);
				}
			}

		} catch (Exception e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			throw e;
		}

		return empleado;
	}

	public void actualizarSalario(Empleado e) {
		String consultaSQL = "UPDATE nominas SET sueldo = ? WHERE dni_empleado = ?";

		double sueldo = Nomina.sueldo(e); // Supongo que Nomina.sueldo(Empleado e) devuelve el nuevo salario

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			// Asignar valores a los parámetros
			statement.setDouble(1, sueldo);
			statement.setString(2, e.getDni());

			// Ejecutar la actualización
			int filasActualizadas = statement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Salario actualizado con éxito.");
			} else {
				System.out.println("No se pudo actualizar el salario.");
			}

		} catch (SQLException ex) {
			System.out.println("Error al ejecutar la actualización: " + ex.getMessage());
		}
	}

	public void editarEmpleadoPorDNI(String dni, Empleado e) throws SQLException {
		String consultaSQL = "UPDATE empleados SET dni = ?,nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			statement.setString(1, e.getDni());
			statement.setString(2, e.getNombre());
			statement.setString(3, e.getSexo());
			statement.setInt(4, e.getCategoria());
			statement.setInt(5, e.getAnyos());
			statement.setString(6, dni);

			int filasAfectadas = statement.executeUpdate();

			if (filasAfectadas == 0) {
				System.out.println("No se actualizó ningún registro.");
				// Puedes lanzar una excepción o manejar de otra forma el caso de que no se haya
				// actualizado ningún registro.
			} else {
				actualizarSalario(e);

				System.out.println("Se actualizó el empleado con DNI: " + dni);
			}

		} catch (SQLException ex) {
			System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
			throw ex;
		}
	}

	public void crearEmpleado(Empleado e) throws SQLException {
		String consultaSQL = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?);";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			statement.setString(1, e.getDni());
			statement.setString(2, e.getNombre());
			statement.setString(3, e.getSexo());
			statement.setInt(4, e.getCategoria());
			statement.setInt(5, e.getAnyos());

			int filasAfectadas = statement.executeUpdate();

			if (filasAfectadas == 0) {
				System.out.println("No se actualizó ningún registro.");
				// Puedes lanzar una excepción o manejar de otra forma el caso de que no se haya
				// actualizado ningún registro.
			} else {
				crearSalario(e);

				System.out.println("Empelado creeado con exito");
			}

		} catch (SQLException ex) {
			System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
			throw ex;
		}
	}

	public void crearSalario(Empleado e) throws SQLException {
		String consultaSQL = "INSERT INTO nominas (dni_empleado, sueldo) VALUES (?, ?);";
		double sueldo = Nomina.sueldo(e);

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			// Configurar los parámetros en el orden correcto
			statement.setString(1, e.getDni());
			statement.setDouble(2, sueldo);

			// Ejecutar la actualización
			int filasActualizadas = statement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Salario creado con éxito.");
			} else {
				System.out.println("No se pudo crear el salario.");
			}

		} catch (SQLException e2) {
			e2.printStackTrace(); // o usar un sistema de registro
			// Otra lógica de manejo de excepciones si es necesario
		}
	}

	public void eliminarEmpleado(String dni) {
		String consultaSQL = "UPDATE empleados SET eliminado = '1' WHERE dni = ? AND eliminado = '0'";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(consultaSQL)) {

			// Configurar los parámetros en el orden correcto
			statement.setString(1, dni);

			// Ejecutar la actualización
			int filasActualizadas = statement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Empleado eliminado con éxito.");
			} else {
				System.out.println("No se pudo eliminar el empleado.");
			}

		} catch (SQLException e2) {
			e2.printStackTrace(); // o usar un sistema de registro
			// Otra lógica de manejo de excepciones si es necesario
		}
	}

}
