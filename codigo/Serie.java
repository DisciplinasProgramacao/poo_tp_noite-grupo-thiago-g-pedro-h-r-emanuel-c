package codigo;
public class Serie {
    public int idSerie;
    private String Nome;
    private String DataDeLancamento;

    public Serie(int idSerie, String Nome, String DataDeLancamento) {
        this.idSerie = idSerie;
        this.Nome = Nome;
        this.DataDeLancamento = DataDeLancamento;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void printaSerie() {
        System.out.println("Id da Série: " + this.idSerie + "\nNome da Série: " + this.Nome + "\nData de Lançamento: "
                + this.DataDeLancamento);
    }
}