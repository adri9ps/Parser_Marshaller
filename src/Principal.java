import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

public class Principal {
	static ArrayList<Libro> libros = new ArrayList<Libro>();

	public static void main(String[] args) {

		boolean fin = false;
		while (!fin) {
			switch (menu()) {
			case 1:
				Parser parser = new Parser();
				parser.parseFicheroXml(pedirFichero());
				parser.parseDocument();
				parser.print();
				break;
			case 2:
				crearLibro();
				break;
			case 3:
				guardarLibros();
				break;
			case 4:
				fin = true;
				break;
			default:
				System.out.println("Has introducido un valor no válido");

			}
		}

	}

	public static int menu() {
		Scanner seleccion = new Scanner(System.in);
		System.out.println("MENÚ");
		System.out.println("1. Mostrar libros según fichero XML");
		System.out.println("2. Crear Libro");
		System.out.println("3. Crear fichero XML según datos introducidos por pantalla");
		System.out.println("4. Salir");

		int op = 0;
		try {
			op = seleccion.nextInt();
		} catch (Exception e) {
			op = 0;
		}

		return op;
	}

	// Pedimos fichero al usuario y comprobamos si existe dicho fichero.

	public static String pedirFichero() {
		Scanner sc = new Scanner(System.in);
		String fichero = "";
		boolean bueno = false;
		while (!bueno) {
			System.out.println("Nombre del fichero XML que deseas leer: ");
			fichero = sc.nextLine();

			File archivo = new File(fichero);
			if (!archivo.exists()) {
				System.out.println("El fichero no existe");
			} else {
				bueno = true;
			}
		}

		return fichero;
	}

	public static void crearLibro() {
		Scanner sc = new Scanner(System.in);

		String titulo = "";
		String editor = "";
		int año = 0;
		int paginas = 0;
		int autores = 0;
		ArrayList<String> nombres = new ArrayList<String>();

		// Introducción de datos por el usuario.
		System.out.println("Titulo:");
		titulo = sc.nextLine();
		System.out.println("Año:");
		año = sc.nextInt();
		sc.nextLine();
		System.out.println("Editor:");
		editor = sc.nextLine();
		System.out.println("Número de páginas:");
		paginas = sc.nextInt();
		sc.nextLine();

		// Pedimos numero de autores
		boolean bueno = false;
		int cantidad = 0;

		while (!bueno) {
			System.out.println("Número de autores: ");
			try {
				cantidad = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				cantidad = 0;
			}

			if (cantidad > 0) {
				bueno = true;
			} else {
				System.out.println("Un libro no puede tener 0 autores, vuelve a escribir un número válido.");
			}
		}
		autores = cantidad;

		// Guardamos el nombre de los autores en el ArrayList
		for (int i = 0; i < autores; i++) {
			System.out.println("Autor nº " + (i + 1));
			String nombreAutor = sc.nextLine();

			nombres.add(new String(nombreAutor));
		}

		// Creamos el libro
		Libro creado = new Libro(titulo, nombres, editor, año, paginas);

		// Imprimimos el libro creado
		System.out.println("Nuevo libro creado correctamente");
		System.out.println("--------------------------------------------");

		creado.print();
		System.out.println("--------------------------------------------");

		// Se añade al arrayList
		libros.add(creado);
	}

	// Guardar libros
	public static void guardarLibros() {
		Scanner sc = new Scanner(System.in);

		Marshaller marshaller = new Marshaller(libros);

		// Entro a crear documento
		marshaller.crearDocumento();
		// Entro a crear Arbol
		marshaller.crearArbolDOM();

		System.out.println("Introduce el nombre del nuevo fichero");
		String nombreArchivo = sc.nextLine();

		File nuevoXML = new File(nombreArchivo);

		try {
			marshaller.escribirDocumentAXml(nuevoXML);
		} catch (Exception e) {
			System.out.println("Error");
		}

		System.out.println("XML generado correctamente");
	}

}
