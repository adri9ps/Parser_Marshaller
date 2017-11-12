import java.util.ArrayList;

public class Libro {
	
	private String titulo;
	private String editor;
	private int paginas;
	private int anyo;
	private ArrayList<String> nombres;
	
	public Libro(String titulo,ArrayList<String> lista, String e,int p,int anyo) {
		this.titulo=titulo;
		editor=e;
		paginas=p;
		nombres=lista;
		this.anyo=anyo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public ArrayList<String> getNombres() {
		return nombres;
	}
	public void setNombres(ArrayList<String> nombres) {
		this.nombres = nombres;
	}
	
	public void print() {
		
		System.out.println("Titulo: "+titulo+"\nEditor: "+editor+"\nPaginas: "+paginas+
				"\nAnyo: "+anyo);
		System.out.println("Autores: ");
		for(int i=0;i<nombres.size();i++)
			System.out.println(nombres.get(i));
		
	}
	
	

}
