package codigo;

public class Avaliacao {

    private int idMidiaAvaliada;
    private String comentarioAvaliacao;
    private Data dataAvaliacao;
    private int notaAvaliacao;

    final private int NOTA_MINIMA = 1;
    final private int NOTA_MAXIMA = 5;

    public Avaliacao(String comentarioAvaliacao, Data dataAvaliacao, int notaAvaliacao, int idMidia) {
        this.comentarioAvaliacao = comentarioAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        this.idMidiaAvaliada = idMidia;
        if (notaAvaliacao > this.NOTA_MAXIMA) {
            this.notaAvaliacao = this.NOTA_MAXIMA;
        } else if (notaAvaliacao < this.NOTA_MINIMA) {
            this.notaAvaliacao = this.NOTA_MINIMA;
        } else {
            this.notaAvaliacao = notaAvaliacao;
        }
    }

    public Avaliacao(Data dataAvaliacao, int notaAvaliacao, int idMidia) {
        this.dataAvaliacao = dataAvaliacao;
        this.idMidiaAvaliada = idMidia;
        if (notaAvaliacao > this.NOTA_MAXIMA) {
            this.notaAvaliacao = this.NOTA_MAXIMA;
        } else if (notaAvaliacao < this.NOTA_MINIMA) {
            this.notaAvaliacao = this.NOTA_MINIMA;
        } else {
            this.notaAvaliacao = notaAvaliacao;
        }
    }

    public int retornaNota() {
        return this.notaAvaliacao;
    }

    public String toStringEspecialista() {
        return "Data da avaliação: " + this.dataAvaliacao.toString() + "\nNota: " + this.notaAvaliacao + " estrelas."
                + "\nComentário: " + this.comentarioAvaliacao + "\n=============" + "\n ";
    }

    public String toStringRegular() {
        return "Data da avaliação: " + this.dataAvaliacao.toString() + "\nNota: " + this.notaAvaliacao + " estrelas." +
                "\n=============" + "\n ";
    }

    public Data retornaData() {
        return this.dataAvaliacao;
    }

    public String retornaComentario() {
        return this.comentarioAvaliacao;
    }

    public int retornaIdMidiaAvaliada(){
        return this.idMidiaAvaliada;
    }
}
