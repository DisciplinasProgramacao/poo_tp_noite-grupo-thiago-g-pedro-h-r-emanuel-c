package codigo;

public class Serie extends Midia{

    public Serie(int idSerie, String Nome, String DataDeLancamento, String Genero, String Idioma) {
        super(idSerie, Nome, DataDeLancamento, Genero, Idioma);
    }

    public int retornaId() {
        return this.id;
    }

    public String retornaNome() {
        return this.Nome;
    }

    public void printaMidia() {
        System.out.println("Id da Série: " + this.id + "\nNome da Série: " + this.Nome + "\nData de Lançamento: "
                + this.DataDeLancamento);
    }
}