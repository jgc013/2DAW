package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmpleadoDAO;
import DAO.nominaDAO;
import excepciones.DatosNoCorrectosException;
import model.Empleado;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String accion2 = request.getParameter("accion2"); // Obtén el valor del parámetro "accion"

		if ("iniciarSesion".equals(accion2)) {
			String ruta = "/iniciarSesion.jsp";
			request.setAttribute("contenido", ruta);
			RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
			re.forward(request, response);
		}

//		String sesion = "no";

		String sesion = (String) request.getSession().getAttribute("sesion");
		if (sesion == null) {
			request.setAttribute("noLogado", true);
			request.setAttribute("login2", true);
			RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
			re.forward(request, response);

		} else if (sesion.equals("si")) {

			String pag = request.getParameter("accion");
			String ruta = "";

			if ("listarEmpleados".equals(pag)) {
				ruta = "/mostrarEmpleados.jsp";
				EmpleadoDAO e = new EmpleadoDAO();
				List<Empleado> listaEmpleados;

				try {
					listaEmpleados = e.obtenerEmpleados();
					request.setAttribute("empleados", listaEmpleados);
				} catch (SQLException | DatosNoCorrectosException e1) {
					e1.printStackTrace();
				}
			} else if ("mostrarSalario".equals(pag)) {
				ruta = "/buscarSalario.jsp";
			} else if ("editarEmpleado".equals(pag)) {
				ruta = "/buscarEmpleado.jsp";
			} else if ("crearEmpleado".equals(pag)) {
				ruta = "/crearEmpleado.jsp";
			} else if ("eliminarEmpleado".equals(pag)) {
				ruta = "/eliminarEmpleado.jsp";
			}

			System.out.println(ruta);

			request.setAttribute("contenido", ruta);
			RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
			re.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion2 = request.getParameter("accion2"); // Obtén el valor del parámetro "accion"

		if (accion2 != null && "iniciarSesion".equals(accion2)) {

			if (accion2.equals("iniciarSesion")) {
				String usuario = request.getParameter("usuario");
				String contraseña = request.getParameter("contrasena");
				EmpleadoDAO em = new EmpleadoDAO();

				try {
					if (em.obtenerEmpleadoPorDNI(usuario) == null) {
						request.setAttribute("errorLogin", true);
					} else if (!usuario.equals(contraseña)) {
						System.out.println(usuario + "  " + contraseña);
						request.setAttribute("errorLogin2", true);
					} else {
						// Establecer la sesión como permanente (por ejemplo, 1 día)
						int tiempoDeSesion1 = 1800; // en segundos
						request.getSession().setMaxInactiveInterval(tiempoDeSesion1);

						request.getSession().setAttribute("login2", false);
						// Recuperar el valor original del parámetro "sesion"
						request.getSession().setAttribute("sesion", "si");
						request.setAttribute("login", true);
					}
					String ruta = "/iniciarSesion.jsp";
					request.setAttribute("contenido", ruta);
					RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
					re.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		String sesion = (String) request.getSession().getAttribute("sesion");
		boolean sesion2 = (boolean) request.getSession().getAttribute("login2");

		if (!sesion2) {
			request.setAttribute("login2", true);
		}
		if (sesion == null) {
			request.setAttribute("noLogado", true);
			request.setAttribute("login2", true);
			RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
			re.forward(request, response);

		} else if (sesion.equals("si")) {

			String accion = request.getParameter("accion");

//------------------------------------------------------------------------------------------------------------------------------
			if (accion.equals("mostrarSalario")) {

				String dni = request.getParameter("dni");

				if (dni != null && !dni.isEmpty()) {
					if (!dni.matches("[0-9]{8}[A-Za-z]{1}")) {
						request.setAttribute("errorDNI", "El DNI no es válido");
						System.out.println("DNI no válido");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
						return;
					}
					nominaDAO n = new nominaDAO();
					double salario = -1;

					try {
						salario = n.obtenerSalario(dni);

						if (salario > 0) {
							request.setAttribute("salario", salario);
							request.setAttribute("contenido", "/mostrarSalario.jsp");
							RequestDispatcher re = request.getRequestDispatcher("/index.jsp");
							re.forward(request, response);
						} else {
							System.out.println("Salario no válido");
						}

					} catch (DatosNoCorrectosException e) {
						request.setAttribute("errorDNI", "El DNI no es válido");
						System.out.println("DNI no válido");
						request.setAttribute("contenido", "/buscarSalario.jsp");
						request.getRequestDispatcher("/index.jsp").forward(request, response);

					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					request.setAttribute("errorDNI", "El DNI no es válido");
					System.out.println("DNI no válido");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}

//-------------------------------------------------------------------------------------------------------------------------------------------------		

			} else if (accion.equals("editarEmpleado")) {
				String dniB = request.getParameter("dni");
				String opcion = request.getParameter("opcion");
				EmpleadoDAO em = new EmpleadoDAO();

				if (opcion.equals("noEncontrado")) {

					if (!dniB.matches("[0-9]{8}[A-Za-z]{1}")) {
						request.setAttribute("errorDNI", "El DNI no es válido");
					}

					if (dniB != null && !dniB.isEmpty() && request.getAttribute("errorDNI") == null) {
						try {
							Empleado empleado = em.obtenerEmpleadoPorDNI(dniB);
							if (empleado != null) {

								System.out.println(empleado.toString());
								request.setAttribute("empleado", empleado);
								request.setAttribute("contenido", "editarEmpleado.jsp");
								request.getRequestDispatcher("editarEmpleado.jsp").forward(request, response);
								return;
							} else {
								request.setAttribute("errorDNI", "El DNI no es válido");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					request.getRequestDispatcher("buscarEmpleado.jsp").forward(request, response);

				} else if (opcion.equals("encontrado")) {

					request.setAttribute("contenido", "buscarEmpleado.jsp");

					String dni = request.getParameter("dni");
					String nombre = request.getParameter("nombre");
					String sexo = request.getParameter("sexo");
					int categoria = Integer.parseInt(request.getParameter("categoria"));
					int anyos = Integer.parseInt(request.getParameter("anyos"));

					// Validar DNI
					if (!dni.matches("[0-9]{8}[A-Za-z]{1}")) {
						request.setAttribute("errorDNI", "El DNI no es válido");
					}

					// Validar Nombre
					if (nombre.isEmpty()) {
						request.setAttribute("errorNombre", "El nombre no puede estar vacío");
					}

					// Validar Sexo
					if (!sexo.matches("[MmFf]{1}")) {
						request.setAttribute("errorSexo", "El sexo debe ser M o F");
					}

					// Validar Categoría
					if (categoria < 1) {
						request.setAttribute("errorCategoria", "La categoría debe ser mayor a 0");
					}

					// Validar Años
					if (anyos < 0) {
						request.setAttribute("errorAnyos", "Los años deben ser un número positivo");
					}

					// Si hay errores, volver a la página con los mensajes de error
					if (request.getAttribute("errorDNI") != null || request.getAttribute("errorNombre") != null
							|| request.getAttribute("errorSexo") != null
							|| request.getAttribute("errorCategoria") != null
							|| request.getAttribute("errorAnyos") != null) {
						request.setAttribute("contenido", "editarEmpleado.jsp");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}

					// Si no hay errores, procesar los datos
					try {

						Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
						em.editarEmpleadoPorDNI(dni, empleado);

						request.setAttribute("actualizacionExitosa", true);

						// Redirigir de vuelta a la misma página
						request.getRequestDispatcher("index.jsp").forward(request, response);

					} catch (SQLException | DatosNoCorrectosException e) {
						// Manejar la excepción
						e.printStackTrace();
					}

				}
			} else if (accion.equals("crearEmpleado")) {
				request.setAttribute("contenido", "crearEmpleado.jsp");

				String dni = request.getParameter("dni");
				String nombre = request.getParameter("nombre");
				String sexo = request.getParameter("sexo");
				int categoria = Integer.parseInt(request.getParameter("categoria"));
				int anyos = Integer.parseInt(request.getParameter("anyos"));

				// Validar DNI
				if (!dni.matches("[0-9]{8}[A-Za-z]{1}")) {
					request.setAttribute("errorDNI", "El DNI no es válido");
				}

				// Validar Nombre
				if (nombre.isEmpty()) {
					request.setAttribute("errorNombre", "El nombre no puede estar vacío");
				}

				// Validar Sexo
				if (!sexo.matches("[MmFf]{1}")) {
					request.setAttribute("errorSexo", "El sexo debe ser M o F");
				}

				// Validar Categoría
				if (categoria < 1) {
					request.setAttribute("errorCategoria", "La categoría debe ser mayor a 0");
				}

				// Validar Años
				if (anyos < 0) {
					request.setAttribute("errorAnyos", "Los años deben ser un número positivo");
				}

				// Si hay errores, volver a la página con los mensajes de error
				if (request.getAttribute("errorDNI") != null || request.getAttribute("errorNombre") != null
						|| request.getAttribute("errorSexo") != null || request.getAttribute("errorCategoria") != null
						|| request.getAttribute("errorAnyos") != null) {
					request.setAttribute("contenido", "crearEmpleado.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}

				// Si no hay errores, procesar los datos
				try {
					EmpleadoDAO em = new EmpleadoDAO();

					if (em.obtenerEmpleadoPorDNI(dni) == null) {

						Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
						em.crearEmpleado(empleado);
						request.setAttribute("creacionExitosa", true);

					} else {
						request.setAttribute("empleadoExistente", true);

					}

					// Redirigir de vuelta a la misma página
					request.getRequestDispatcher("index.jsp").forward(request, response);

				} catch (Exception e) {
					// Manejar la excepción
					e.printStackTrace();
				}

			} else if (accion.equals("eliminarEmpleado")) {

				request.setAttribute("contenido", "eliminarEmpleado.jsp");

				String dni = request.getParameter("dni");

				if (dni != null && !dni.isEmpty()) {
					if (!dni.matches("[0-9]{8}[A-Za-z]{1}")) {
						request.setAttribute("errorDNI", "El DNI no es válido");
						System.out.println("DNI no válido");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
						return;
					} else {

						try {
							EmpleadoDAO em = new EmpleadoDAO();
							em.eliminarEmpleado(dni);
							request.setAttribute("eliminacionExitosa", true);
							request.getRequestDispatcher("/index.jsp").forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute("errorDNI", "El DNI no existe en la Base de Datos");
							System.out.println("DNI no válido");
							request.getRequestDispatcher("/index.jsp").forward(request, response);
						}

					}
				}
			}

		}
	}

}
