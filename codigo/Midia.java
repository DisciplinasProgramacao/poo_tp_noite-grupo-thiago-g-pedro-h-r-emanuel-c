package codigo;

public abstract class Midia {
    protected int id;
    protected String Nome;
    protected String DataDeLancamento;
    protected String Genero;
    protected String Idioma;

    public Midia(int id, String Nome, String DataDeLancamento, String Genero, String Idioma){
        this.id = id;
        this.Nome = Nome;
        this.DataDeLancamento = DataDeLancamento;
        this.Genero = Genero;
        this.Idioma = Idioma;
    }

    abstract int retornaId();

    abstract String retornaNome();

    abstract void printaMidia();
}
