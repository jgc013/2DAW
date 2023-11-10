package model;
/**
 * La clase Persona representa a una persona con nombre,DNI y sexo.
 */
public class Persona {

	public String nombre;
	public String dni;
	public String sexo;

	
	
	 /**
     * Crea un nuevo objeto Persona con nombre, DNI y sexo.
     *
     * @param nombre El nombre de la persona.
     * @param dni    El número de identificación (DNI) de la persona.
     * @param sexo   El sexo de la persona.
     */
	public Persona(String nombre, String dni, String sexo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
		

	}
	
	   /**
     * Creo un nuevo objeto Persona con nombre y sexo.
     *
     * @param nombre El nombre de la persona.
     * @param sexo   El sexo de la persona.
     */

	public Persona(String nombre, String sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	public void setDni(String dni) {
		this.dni = dni;

	}

	public void imprime() {
		System.out.println("El nombre es " + nombre);
		System.out.println("El Dni es  " + dni);
		System.out.println("El sexo es " + sexo);;
	}

}
