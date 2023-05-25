package codigo;

public class Avaliacao {

    private String comentarioAvaliacao;
    private Data dataAvaliacao;
    private int notaAvaliacao;

    final private int NOTA_MINIMA = 1;
    final private int NOTA_MAXIMA = 5;

    public Avaliacao(String comentarioAvaliacao, Data dataAvaliacao, int notaAvaliacao) {
        this.comentarioAvaliacao = comentarioAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        if(notaAvaliacao > this.NOTA_MAXIMA){
           this.notaAvaliacao = this.NOTA_MAXIMA; 
        }else if(notaAvaliacao < this.NOTA_MINIMA){
            this.notaAvaliacao = this.NOTA_MINIMA; 
        } else {
            this.notaAvaliacao = notaAvaliacao; 
        }
    }

    public int retornaNota(){
        return this.notaAvaliacao;
    }

    public String toString() {
        return "Data da avaliação: " + this.dataAvaliacao.toString() + "\nNota: " + this.notaAvaliacao + "estrelas."
                + "\nComentário: " + this.comentarioAvaliacao;
    }

    public Data retornaData(){
        return this.dataAvaliacao;
    }
    
    public String retornaComentario(){
        return this.comentarioAvaliacao;
    }
}
