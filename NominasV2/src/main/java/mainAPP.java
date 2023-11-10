import java.sql.SQLException;

import DAO.EmpleadoDAO;
import excepciones.DatosNoCorrectosException;

public class mainAPP {
	public static void main(String[] args) throws SQLException, DatosNoCorrectosException {
		EmpleadoDAO e = new EmpleadoDAO();
		e.obtenerEmpleados();
		
	}
}
