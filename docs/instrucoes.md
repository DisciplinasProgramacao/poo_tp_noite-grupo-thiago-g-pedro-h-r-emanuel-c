# Instruções de uso

## Arquivos

- POO_Audiencia2.csv
- POO_Espectadores.csv
- POO_Filmes1.csv
- POO_Series1.csv

# Tabelas dos arquivos

## Espectador
    String Nome, Login, Senha;
    List<Midia> MidiasFuturas;
    List<Midia> MidiasAssistidas;
    List<Avaliacao> avaliacoesEspectador;
    IPerfilEspectador perfil;

## Filmes
    int idFilme;
    String Nome;
    String DataDeLancamento;
    int DuracaoMin;
    String Genero;
    String Idioma;
    boolean Lancamento;

## Serie
    idSerie;
    Nome;
    DataDeLancamento;
    Genero;
    Idioma;
    Lancamento;

## Enum
    Generos;
    Idioma;
    Profissao;

## Interface Perfil Espectador.
    boolean podeComentar();
    String retornaTipo();
    boolean podeAssistirLancamento();

## Plataforma
    Map<String, Espectador> listaEspectadores;
    List<Midia> listaMidia;
    Espectador espectadorLogado;

## Midia
    int id;
    String nome;
    String dataDeLancamento;
    String genero;
    String idioma;
    List<Avaliacao> avaliacoes;
    float notaMedia;
    boolean lancamento;
    int quantidadeVisualizacoes;
    int quantidadeAvaliacoes;

## Menu
    Plataforma plataforma;
    Scanner input;
    boolean sair = false;

## Data
    Utilizado versão do Caram;