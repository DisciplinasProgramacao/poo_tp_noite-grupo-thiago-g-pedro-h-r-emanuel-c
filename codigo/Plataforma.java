package codigo;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Plataforma {
    private Scanner input = new Scanner(System.in);
    private Map<String, Espectador> listaEspectadores;
    private List<Midia> listaMidia;
    private Espectador espectadorLogado;

    public Plataforma() throws IOException {
        this.listaMidia = new LinkedList<Midia>();
        this.listaMidia.addAll(carregaArqSerie());
        this.listaMidia.addAll(carregaArqFilmes());
        this.listaEspectadores = new LinkedHashMap<>();
        this.listaEspectadores = carregarArqEspectador();
        this.carregaListFuturaEAssistida(listaEspectadores);
        this.espectadorLogado = null;
    }

    public static List<Midia> carregaArqSerie() throws IOException {
        Path arquivo = Path.of(
                "C:/Users/enmc0/OneDrive/Documentos/Faculdade/3° Período/POO/TrabalhoStreamingPOO/poo_tp_noite-grupo-thiago-g-pedro-h-r-emanuel-c/docs/arquivos/POO_Series1.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeSerie = campos[1];
                String dataLancamento = campos[2];
                String genero = campos[3];
                String idioma = campos[4];
                Serie serie = new Serie(idCod, nomeSerie, dataLancamento, genero, idioma);
                return serie;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public static List<Midia> carregaArqFilmes() throws IOException {
        Path arquivo = Path.of(
                "C:/Users/enmc0/OneDrive/Documentos/Faculdade/3° Período/POO/TrabalhoStreamingPOO/poo_tp_noite-grupo-thiago-g-pedro-h-r-emanuel-c/docs/arquivos/POO_Filmes1.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeFilme = campos[1];
                String dataLancamento = campos[2];
                int duracaoMin = Integer.parseInt(campos[3]);
                String genero = campos[4];
                String idioma = campos[5];
                Filme filme = new Filme(idCod, nomeFilme, dataLancamento, duracaoMin, genero, idioma);
                return filme;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public void carregaListFuturaEAssistida(Map<String, Espectador> listaEspectadores) throws IOException {
        Path arquivo = Path.of(
                "C:/Users/enmc0/OneDrive/Documentos/Faculdade/3° Período/POO/TrabalhoStreamingPOO/poo_tp_noite-grupo-thiago-g-pedro-h-r-emanuel-c/docs/arquivos/POO_Audiencia2.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            linhas.forEach(linha -> {
                String[] campos = linha.split(";");
                String login = campos[0];
                String serie = campos[1];
                int id = Integer.parseInt(campos[2]);

                if (serie.equals("A")) {
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia midia = this.buscaMidia(id);
                        if (midia == null) {
                            System.out.println("Série com ID " + id + " inexistente");
                        } else {
                            espectador.adicionarmidiasAssistidas(midia);
                        }
                    } else {
                        System.out.println("Entrada inválida, login inexistente: " + login);
                    }
                } else if (serie.equals("F")) {
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia midia = this.buscaMidia(id);
                        if (midia == null) {
                            System.out.println("Série com ID " + id + " inexistente");
                        } else {
                            espectador.adicionarMidiasFuturas(midia);
                        }
                    } else {
                        System.out.println("Entrada inválida, login inexistente: " + login);
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return;
        }
    }

    public static Map<String, Espectador> carregarArqEspectador() throws IOException {
        Path arquivo = Path.of(
                "C:/Users/enmc0/OneDrive/Documentos/Faculdade/3° Período/POO/TrabalhoStreamingPOO/poo_tp_noite-grupo-thiago-g-pedro-h-r-emanuel-c/docs/arquivos/POO_Espectadores.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                Espectador espectador = new Espectador(nome, login, senha);
                return Map.entry(login, espectador);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public String buscaIdiomaGeneroString(String valor, String filtro) {
        StringBuilder sb = new StringBuilder();
        Comparator<String> filtroComparator = Comparator.comparing(String::toLowerCase);
        List<Midia> listaRetorno = listaMidia.stream()
                .filter(midia -> filtro.equalsIgnoreCase("idioma")
                        ? filtroComparator.compare(midia.retornaIdioma().toLowerCase(), valor.toLowerCase()) == 0
                        : filtro.equalsIgnoreCase("genero")
                                ? filtroComparator.compare(midia.retornaGenero().toLowerCase(),
                                        valor.toLowerCase()) == 0
                                : midia.retornaNome().toLowerCase().contains(valor.toLowerCase()))
                .collect(Collectors.toList());
        listaRetorno.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

    public Midia retornaMidiaPorNome(String nome) {
        for (Midia midia : listaMidia) {
            if (midia.retornaNome().equals(nome.toLowerCase())) {
                return midia;
            }
        }
        return null;
    }

    public void infoMidia(int id) {
        for (Midia midia : this.listaMidia) {
            if (midia.id == (id)) {
                midia.printaMidia();
            }
        }
    }

    public boolean efetuarLogin(String login, String senha) {
        if (this.listaEspectadores.containsKey(login)) {
            Espectador espectador_atual = listaEspectadores.get(login);
            if (espectador_atual.retornaSenha().equals(senha)) {
                this.espectadorLogado = espectador_atual;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void efetuarLogout() {
        this.espectadorLogado = null;
        System.out.println("Conta deslogada");
    }

    public void adicionarMidiaFutura(String nomeMidia) {
        boolean adicionado = false;
        for (Midia midia : this.listaMidia) {
            if (midia.retornaNome().toLowerCase().equals(nomeMidia.toLowerCase())) {
                this.espectadorLogado.adicionarMidiasFuturas(midia);
                adicionado = true;
                System.out.println("Midia adicionada com sucesso!");
            }
        }
        if (adicionado = false) {
            System.out.println("Midia nao existe, favor digitar o nome corretamente");
        }
    }

    public void adicionarMidiaAssistida(String nomeMidia) {
        boolean adicionado = false;
        for (Midia midia : this.listaMidia) {
            if (midia.retornaNome().toLowerCase().equals(nomeMidia.toLowerCase())) {
                this.espectadorLogado.adicionarmidiasAssistidas(midia);
                adicionado = true;
            }
        }
        if (adicionado = false) {
            System.out.println("Midia nao existe, favor digitar o nome corretamente");
        }
    }

    public void removerlMidiaFutura(String nomeMidia) {
        boolean remover = false;
        for (Midia midia : this.listaMidia) {
            if (midia.retornaNome().toLowerCase().equals(nomeMidia.toLowerCase())) {
                this.espectadorLogado.removerMidiaFuturas(midia);
                remover = true;
                System.out.println("Midia removida com sucesso!");
            }
        }
        if (remover = false) {
            System.out.println("Midia nao existe, favor digitar o nome corretamente");
        }
    }

    public String avaliarMidia(String nomeMidia, String comentarioAvaliacao, int notaAvaliacao) {
        Midia midia = this.retornaMidiaPorNome(nomeMidia);
        Avaliacao avaliacaoEspecialista = new Avaliacao(comentarioAvaliacao, new Data(), notaAvaliacao, midia.retornaId());
        Avaliacao avaliacaoRegular = new Avaliacao(new Data(), notaAvaliacao, midia.retornaId());
        this.espectadorLogado.atualizaPerfil();
        if (espectadorLogadoJaAssistiu(midia)
                && espectadorLogadoPodeComentar()
                && !espectadorLogadoJaAvaliou(midia)) {
            midia.Avaliar(avaliacaoEspecialista);
            this.espectadorLogado.adicionarAvaliacaoEspectador(avaliacaoEspecialista);
            return "Mídia avaliada como espectador especialista!";
        } else if (espectadorLogadoJaAssistiu(midia)
                && !espectadorLogadoPodeComentar()
                && !espectadorLogadoJaAvaliou(midia)) {
            midia.Avaliar(avaliacaoRegular);
            this.espectadorLogado.adicionarAvaliacaoEspectador(avaliacaoRegular);
            return "Mídia avaliada como espectador regular!";
        } else {
            return "Você ainda não assistiu essa mídia! Logo impossível avaliá-la";
        }
    }

    public Midia buscaMidia(int idMidia) {
        for (Midia midia : listaMidia) {
            if (midia.retornaId() == idMidia) {
                return midia;
            }
        }
        return null;
    }

    public Espectador getEspectadorLogado() {
        return this.espectadorLogado;
    }

    public String printaListaAvaliacoesDoEspectador() {
        return this.espectadorLogado.listaAvaliacoesToString();
    }

    public boolean espectadorLogadoPodeComentar(){
        return this.espectadorLogado.retornaPerfil().podeComentar();
    }
    public boolean espectadorLogadoJaAvaliou(Midia midia){
       return espectadorLogadoJaAvaliou(midia);
    }
    public boolean espectadorLogadoJaAssistiu(Midia midia){
       return this.espectadorLogado.jaAssistiu(midia);
    }

}
