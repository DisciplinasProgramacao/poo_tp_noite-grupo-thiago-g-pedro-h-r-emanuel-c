package codigo;

public class Avaliacao {

    // Atributos da classe Avaliacao
    private int idMidiaAvaliada; // ID da mídia avaliada
    private String comentarioAvaliacao; // Comentário da avaliação
    private Data dataAvaliacao; // Data da avaliação
    private int notaAvaliacao; // Nota da avaliação

    final private int NOTA_MINIMA = 1; // Nota mínima possível
    final private int NOTA_MAXIMA = 5; // Nota máxima possível

    // Construtor da classe Avaliacao
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

    // Construtor alternativo da classe Avaliacao
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

    // Retorna a nota da avaliação
    public int retornaNota() {
        return this.notaAvaliacao;
    }

    // Retorna uma representação em string da avaliação (especialista)
    public String toStringEspecialista() {
        if(this.comentarioAvaliacao.isEmpty()){
            this.comentarioAvaliacao = "Usuário não colocou comentário para o mesmo.";
        }
        return "Data da avaliação: " + this.dataAvaliacao.toString() + "\nNota: " + this.notaAvaliacao + " estrelas."
                + "\nComentário: " + this.comentarioAvaliacao + "\n ";
    }

    // Retorna uma representação em string da avaliação (regular)
    public String toStringRegular() {
        return "Data da avaliação: " + this.dataAvaliacao.toString() + "\nNota: " + this.notaAvaliacao + " estrelas." + "\n ";
    }

    // Retorna a data da avaliação
    public Data retornaData() {
        return this.dataAvaliacao;
    }

    // Retorna o comentário da avaliação
    public String retornaComentario() {
        return this.comentarioAvaliacao;
    }

    // Retorna o ID da mídia avaliada
    public int retornaIdMidiaAvaliada(){
        return this.idMidiaAvaliada;
    }
}
