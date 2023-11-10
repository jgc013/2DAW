package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtils {
private static BasicDataSource dataSource = null;
	
	/**
     * Obtiene el DataSource para la conexión a la base de datos.
     * Si el DataSource aún no se ha inicializado, lo crea con la configuración predefinida.
     * @return El DataSource configurado para la conexión a la base de datos.
     */
	 
	 private static DataSource getDataSource() {
	  if (dataSource == null) {
	   dataSource = new BasicDataSource();
	   dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	   dataSource.setUsername("root");
	   dataSource.setPassword("ordenador4");
	   dataSource.setUrl("jdbc:mariadb://localhost:3306/control_nominas");
	  
	  }
	  return dataSource;
	 }
	 /**
	     * Obtiene una conexión a la base de datos utilizando el DataSource configurado.
	     * @return Una conexión a la base de datos MariaDB.
	     * @throws SQLException Si ocurre un error al establecer la conexión.
	 */
	 
	 public static Connection getConnection() throws SQLException {
	  return getDataSource().getConnection();
	 }
}
