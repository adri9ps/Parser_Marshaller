import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	public Marshaller(ArrayList<Libro> lib) {
		libros = lib;
	}

	public void crearDocumento() {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// creamos una instancia de DOM
			dom = db.newDocument();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}

	public void crearArbolDOM() {

		// creamos el elemento raíz
		Element docEle = dom.createElement("biblioteca");

		Iterator it = libros.iterator();
		while (it.hasNext()) {

			Libro l = (Libro) it.next();

			Element elementoLibro = setLibro(l);

			docEle.appendChild(elementoLibro);

		}
		dom.appendChild(docEle);

	}

	private Element setLibro(Libro l) {

		Element elementoLibro = dom.createElement("libro");

		Element tituloLibro = dom.createElement("titulo");
		Text tit = dom.createTextNode(l.getTitulo());
		tituloLibro.appendChild(tit);
		tituloLibro.setAttribute("anyo", Integer.toString(l.getAnyo()));
		elementoLibro.appendChild(tituloLibro);

		Element autorLibro = dom.createElement("autor");
		// Hacemos el for para recorrer los nombres por si tuviera más de un
		// autor.
		for (int i = 0; i < l.getNombres().size(); i++) {
			String nombre_rec = l.getNombres().get(i);
			Element nombre = dom.createElement("nombre");
			Text t = dom.createTextNode(nombre_rec);
			nombre.appendChild(t);
			autorLibro.appendChild(nombre);
		}

		elementoLibro.appendChild(autorLibro);

		Element editorLibro = dom.createElement("editor");
		Text contenido = dom.createTextNode(l.getEditor());
		editorLibro.appendChild(contenido);
		elementoLibro.appendChild(editorLibro);

		Element pagLibro = dom.createElement("paginas");
		Text nump = dom.createTextNode(Integer.toString(l.getPaginas()));
		pagLibro.appendChild(nump);
		elementoLibro.appendChild(pagLibro);

		return elementoLibro;

	}

	public void escribirDocumentAXml(File file) throws TransformerException {

		// creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// especificamos donde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);

	}

}