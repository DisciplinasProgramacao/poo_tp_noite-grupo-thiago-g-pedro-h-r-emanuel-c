package codigo;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import codigo.EGeneros.Genero;
import codigo.EIdioma.Idioma;
import codigo.EProfissao.Profissao;

public class Plataforma {
    private Scanner input = new Scanner(System.in);
    private Map<String, Espectador> listaEspectadores;
    private List<Midia> listaMidia;
    private Espectador espectadorLogado;

    /**
     * Construtor da classe Plataforma.
     */
    public Plataforma() {
        try {
            this.listaMidia = new LinkedList<Midia>();
            this.listaMidia.addAll(carregaArqSerie());
            this.listaMidia.addAll(carregaArqFilmes());
            this.listaEspectadores = new LinkedHashMap<>();
            this.listaEspectadores = carregarArqEspectador();
            this.carregaListFuturaEAssistida(listaEspectadores);
            this.espectadorLogado = null;
        } catch (IOException e) {
            System.err.println("Erro ao carregar os arquivos: " + e.getMessage());
        }
    }

    /**
     * Carrega o arquivo CSV de séries e retorna uma lista de objetos do tipo Serie.
     *
     * @return Uma lista de objetos do tipo Serie contendo as informações carregadas
     *         do arquivo.
     * @throws IOException Se ocorrer um erro na leitura do arquivo.
     */
    public static List<Midia> carregaArqSerie() throws IOException {
        // Caminho do arquivo CSV
        Path arquivo = Path.of("./docs/arquivos/POO_Series1.csv");

        try (Stream<String> linhas = Files.lines(arquivo)) {
            // Mapeia cada linha do arquivo para um objeto Serie
            return linhas.map(linha -> {
                // Divide a linha em campos separados por ";"
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeSerie = campos[1];
                String dataLancamento = campos[2];
                String genero = generoAleatorio(); // Função para obter um gênero aleatório
                String idioma = idiomaAleatorio(); // Função para obter um idioma aleatório
                boolean lancamento = campos[3].equals("S"); // Verifica se o campo indica lançamento
                Serie serie = new Serie(idCod, nomeSerie, dataLancamento, genero, idioma, lancamento);
                return serie;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Carrega o arquivo CSV de filmes e retorna uma lista de objetos do tipo Filme.
     *
     * @return Uma lista de objetos do tipo Filme contendo as informações carregadas
     *         do arquivo.
     * @throws IOException Se ocorrer um erro na leitura do arquivo.
     */
    public static List<Midia> carregaArqFilmes() throws IOException {
        // Caminho do arquivo CSV
        Path arquivo = Path.of("./docs/arquivos/POO_Filmes1.csv");

        try (Stream<String> linhas = Files.lines(arquivo)) {
            // Mapeia cada linha do arquivo para um objeto Filme
            return linhas.map(linha -> {
                // Divide a linha em campos separados por ";"
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeFilme = campos[1];
                String dataLancamento = campos[2];
                int duracaoMin = Integer.parseInt(campos[3]);
                String genero = generoAleatorio(); // Função para obter um gênero aleatório
                String idioma = idiomaAleatorio(); // Função para obter um idioma aleatório
                boolean lancamento = campos[4].equals("S"); // Verifica se o campo indica lançamento
                Filme filme = new Filme(idCod, nomeFilme, dataLancamento, duracaoMin, genero, idioma, lancamento);
                return filme;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Carrega informações sobre mídias futuras e assistidas a partir de um arquivo
     * CSV
     * e atualiza a lista de espectadores com as mídias correspondentes.
     *
     * @param listaEspectadores O mapa de espectadores, onde a chave é o login do
     *                          espectador e o valor é o objeto Espectador.
     * @throws IOException Se ocorrer um erro na leitura do arquivo.
     */
    public void carregaListFuturaEAssistida(Map<String, Espectador> listaEspectadores) throws IOException {
        // Caminho do arquivo CSV
        Path arquivo = Path.of("./docs/arquivos/POO_Audiencia2.csv");

        try (Stream<String> linhas = Files.lines(arquivo)) {
            // Percorre sobre cada linha do arquivo
            linhas.forEach(linha -> {
                // Divide a linha em campos separados por ";"
                String[] campos = linha.split(";");
                String login = campos[0];
                String serie = campos[1];
                int id = Integer.parseInt(campos[2]);

                if (serie.equals("A")) {
                    // Verifica se o login existe na lista de espectadores
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia midia = this.buscaMidia(id);
                        if (midia == null) {
                            System.out.println("Série com ID " + id + " inexistente");
                        } else {
                            // Adiciona a mídia à lista de mídias assistidas do espectador
                            espectador.adicionarMidiasArquivoAssistidas(midia);
                        }
                    } else {
                        System.out.println("Entrada inválida, login inexistente: " + login);
                    }
                } else if (serie.equals("F")) {
                    // Verifica se o login existe na lista de espectadores
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia midia = this.buscaMidia(id);
                        if (midia == null) {
                            System.out.println("Mídia com ID " + id + " inexistente");
                        } else {
                            // Adiciona a mídia à lista de mídias futuras do espectador
                            espectador.adicionarMidiasArquivoFuturas(midia);
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

    /**
     * Carrega o arquivo CSV de espectadores e retorna um mapa de objetos do tipo
     * Espectador.
     *
     * @return Um mapa onde a chave é o login do espectador e o valor é o objeto
     *         Espectador correspondente.
     * @throws IOException Se ocorrer um erro na leitura do arquivo.
     */
    public static Map<String, Espectador> carregarArqEspectador() throws IOException {
        // Caminho do arquivo CSV
        Path arquivo = Path.of("./docs/arquivos/POO_Espectadores.csv");

        try (Stream<String> linhas = Files.lines(arquivo)) {
            // Mapeia cada linha do arquivo para um objeto Espectador
            return linhas.map(linha -> {
                // Divide a linha em campos separados por ";"
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                boolean profissao = profissionalAleatorio(); // Função para obter um valor booleano aleatório
                Espectador espectador = new Espectador(nome, login, senha, profissao);
                return Map.entry(login, espectador);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Realiza uma busca por idioma, gênero ou nome em uma lista de mídias e retorna
     * os resultados encontrados.
     *
     * @param valor  O valor a ser buscado (idioma, gênero ou nome).
     * @param filtro O tipo de filtro a ser aplicado (idioma, gênero ou nome).
     * @return Uma string contendo os resultados da busca ou uma mensagem indicando
     *         que nenhum resultado foi encontrado.
     */
    public String buscaIdiomaGeneroString(String valor, String filtro) {
        StringBuilder sb = new StringBuilder();
        Comparator<String> filtroComparator = Comparator.comparing(String::toLowerCase);

        // Filtra a lista de mídias com base no valor e filtro fornecidos
        List<Midia> listaRetorno = listaMidia.stream()
                .filter(midia -> filtro.equalsIgnoreCase("idioma")
                        ? filtroComparator.compare(midia.retornaIdioma().toLowerCase(), valor.toLowerCase()) == 0
                        : filtro.equalsIgnoreCase("genero")
                                ? filtroComparator.compare(midia.retornaGenero().toLowerCase(),
                                        valor.toLowerCase()) == 0
                                : midia.retornaNome().toLowerCase().contains(valor.toLowerCase()))
                .collect(Collectors.toList());

        // Adiciona as representações em string das mídias encontradas ao StringBuilder
        listaRetorno.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        // Verifica se a busca retornou algum resultado
        if (sb.toString().equals("")) {
            return "Não foram encontrados resultados para sua busca. Verifique se informou corretamente.";
        }

        return sb.toString();
    }

    /**
     * Retorna a mídia correspondente ao nome fornecido.
     *
     * @param nome O nome da mídia a ser buscada.
     * @return A mídia encontrada ou null se não houver correspondência.
     */
    public Midia retornaMidiaPorNome(String nome) {
        for (Midia midia : listaMidia) {
            if (midia.retornaNome().equals(nome.toLowerCase())) {
                return midia;
            }
        }
        return null;
    }

    /**
     * Retorna as informações da mídia correspondente ao ID fornecido.
     *
     * @param id O ID da mídia a ser buscada.
     * @return As informações da mídia ou uma mensagem indicando que o ID não foi
     *         encontrado.
     */
    public String infoMidia(int id) {
        for (Midia midia : this.listaMidia) {
            if (midia.id == (id)) {
                return midia.toString();
            }
        }
        return "Id da mídia não encontrado.";
    }

    /**
     * Efetua o login do espectador com o login e senha fornecidos.
     *
     * @param login O login do espectador.
     * @param senha A senha do espectador.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
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

    /**
     * Efetua o logout do espectador atualmente logado.
     */
    public String efetuarLogout() {
        this.espectadorLogado = null;
        return "Conta deslogada";
    }

    /**
     * Adiciona uma mídia à lista de mídias futuras do espectador logado.
     *
     * @param nomeMidia O nome da mídia a ser adicionada.
     */
    public String adicionarMidiaFutura(String nomeMidia) {
        boolean adicionado = false;
        boolean encontrado = false;
        for (Midia midia : this.listaMidia) {
            if (midia.retornaNome().toLowerCase().equals(nomeMidia.toLowerCase())) {
                encontrado = true;
                if (this.espectadorLogado.adicionarMidiaMenuFuturas(midia) == true) {
                    adicionado = true;
                    return "Mídia adicionada com sucesso!";
                } else {
                    return "Mídia já adicionada a lista.";
                }
            }
        }
        return "Mídia não existe, favor digitar o nome corretamente!";
    }

    /**
     * Adiciona uma mídia à lista de mídias assistidas do espectador logado.
     *
     * @param nomeMidia O nome da mídia a ser adicionada.
     */
    public String adicionarMidiaAssistida(String nomeMidia) {
        boolean adicionado = false;
        boolean encontrado = false;
        for (Midia midia : this.listaMidia) {
            if (midia.retornaNome().toLowerCase().equals(nomeMidia.toLowerCase())) {
                encontrado = true;
                if (this.espectadorLogado.adicionarMidiaMenuAssistidas(midia) == true) {
                    adicionado = true;
                    return "Mídia adicionada com sucesso!";
                } else {
                    return "Mídia já adicionada a lista.";
                }
            }
        }
        return "Mídia não existe, favor digitar o nome corretamente!";

    }

    /**
     * Identifica o espectador que assistiu a maior quantidade de mídias e exibe o
     * resultado.
     */
    public String espectadorAssistiuMaisMidias() {
        String espectadorMaisMidia = null;
        int quantidadeMaisMidia = -1;

        for (Map.Entry<String, Espectador> entry : listaEspectadores.entrySet()) {
            Espectador espectador = entry.getValue();
            int quantidadeMidiaAssistida = espectador.retornaQuantidadeMidiaAssistida();

            if (quantidadeMidiaAssistida > quantidadeMaisMidia) {
                quantidadeMaisMidia = quantidadeMidiaAssistida;
                espectadorMaisMidia = espectador.retornaNome();
            }
        }

        return "Espectador que assistiu mais mídias: " + espectadorMaisMidia + "\n Quantidade de mídias assistidas: "
                + quantidadeMaisMidia;
    }

    /**
     * Identifica o espectador que fez a maior quantidade de avaliações e exibe o
     * resultado.
     */
    public String espectadorMaisAvaliou() {
        String espectadorMaisAvaliou = null;
        int quantidadeMaisAvaliacao = -1;

        for (Map.Entry<String, Espectador> entry : listaEspectadores.entrySet()) {
            Espectador espectador = entry.getValue();
            int quantidadeAvaliacao = espectador.retornaQuantidadeAvaliacao();

            if (quantidadeAvaliacao > quantidadeMaisAvaliacao) {
                quantidadeMaisAvaliacao = quantidadeAvaliacao;
                espectadorMaisAvaliou = espectador.retornaNome();
            }
        }
        if (quantidadeMaisAvaliacao != 0) {
            return "Espectador que mais avaliou : " + espectadorMaisAvaliou + "\nQuantidade de avaliaçoes : "
                    + quantidadeMaisAvaliacao;
        } else {
            return "Não existem avaliações no momento.";
        }
    }

    /**
     * Calcula a porcentagem de clientes que possuem 15 ou mais avaliações em
     * relação ao total de espectadores e exibe o resultado.
     */
    public String porcetagemClientesComMenos15Avaliacao() {
        int totalEspectadores = listaEspectadores.size();
        int espectadoresComAvaliacoes15OuMais = 0;

        // Percorre o mapa de espectadores
        for (Map.Entry<String, Espectador> entry : listaEspectadores.entrySet()) {
            Espectador espectador = entry.getValue();
            int quantidadeAvaliacoes = espectador.retornaQuantidadeAvaliacao();
            if (quantidadeAvaliacoes >= 15) {
                espectadoresComAvaliacoes15OuMais++;
            }
        }

        // Calcula a porcentagem
        double porcentagem = (double) espectadoresComAvaliacoes15OuMais / totalEspectadores * 100;

        DecimalFormat formato = new DecimalFormat("0.00");
        String porcentagemFormatada = formato.format(porcentagem);
        if (porcentagem != 0) {
            return "Porcentagem de clientes com pelo menos 15 avaliações: " + porcentagemFormatada + "%";
        } else {
            return "Não existem avaliações no momento, logo sem porcentagens de clientes para o relatório.";
        }
    }

    /**
     * Retorna os nomes das 10 mídias melhor avaliadas, com pelo menos 100
     * visualizações, em ordem decrescente de nota média.
     * Se não houver avaliações no momento, retorna uma mensagem informando.
     *
     * @return os nomes das mídias melhor avaliadas
     */
    public String melhoresAvaliadas() {
        List<String> nomesMidiasOrdenados = listaMidia.stream()
                .filter(midia -> midia.getQuantidadeVisualizacoes() >= 100)
                .filter(midia -> midia.retornaTemAvaliacoes() != 0)
                .sorted(Comparator.comparingDouble(Midia::retornaNotaMedia).reversed())
                .limit(10)
                .map(Midia::retornaNome)
                .collect(Collectors.toList());

        if (nomesMidiasOrdenados.size() == 0) {
            return "Não existem avaliações no momento.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nomesMidiasOrdenados.size(); i++) {
            sb.append(i + 1).append(". ").append(nomesMidiasOrdenados.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Retorna os nomes das 10 mídias mais assistidas em ordem decrescente de
     * quantidade de visualizações.
     *
     * @return os nomes das mídias mais assistidas
     */
    public String midiasMaisAssistidas() {
        List<String> nomesMidiasOrdenados = listaMidia.stream()
                .sorted(Comparator.comparingInt(Midia::getQuantidadeVisualizacoes).reversed())
                .limit(10)
                .map(Midia::retornaNome)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nomesMidiasOrdenados.size(); i++) {
            sb.append(i + 1).append(". ").append(nomesMidiasOrdenados.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Retorna os nomes das 10 mídias melhor avaliadas do gênero especificado, com
     * pelo menos 100 visualizações, em ordem decrescente de nota média.
     * Se não houver avaliações no momento para o gênero especificado, retorna uma
     * mensagem informando.
     *
     * @param genero o gênero das mídias a serem avaliadas
     * @return os nomes das mídias melhor avaliadas do gênero especificado
     */
    public String melhoresAvaliadasGenero(String genero) {
        List<String> nomesMidiasOrdenados = listaMidia.stream()
                .filter(midia -> midia.retornaGenero().equalsIgnoreCase(genero))
                .filter(midia -> midia.getQuantidadeVisualizacoes() >= 100)
                .filter(midia -> midia.retornaTemAvaliacoes() != 0)
                .sorted(Comparator.comparingDouble(Midia::retornaNotaMedia).reversed())
                .limit(10)
                .map(Midia::retornaNome)
                .collect(Collectors.toList());

        if (nomesMidiasOrdenados.size() == 0) {
            return "Não existem avaliações no momento para o gênero especificado.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nomesMidiasOrdenados.size(); i++) {
            sb.append(i + 1).append(". ").append(nomesMidiasOrdenados.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Retorna os nomes das 10 mídias mais assistidas do gênero especificado, em
     * ordem decrescente de quantidade de visualizações.
     *
     * @param genero o gênero das mídias a serem consideradas
     * @return os nomes das mídias mais assistidas do gênero especificado
     */
    public String midiasMaisAssistidasGenero(String genero) {
        List<String> nomesMidiasOrdenados = listaMidia.stream()
                .filter(midia -> midia.retornaGenero().equalsIgnoreCase(genero))
                .sorted(Comparator.comparingInt(Midia::getQuantidadeVisualizacoes).reversed())
                .limit(10)
                .map(Midia::retornaNome)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nomesMidiasOrdenados.size(); i++) {
            sb.append(i + 1).append(". ").append(nomesMidiasOrdenados.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Remove a mídia futura com o nome especificado da lista de mídias futuras do
     * espectador logado.
     *
     * @param nomeMidia o nome da mídia futura a ser removida
     */
    public String removerMidiaFutura(String nomeMidia) {
        boolean remover = false;
        boolean encontrado = false;
        for (Midia midia : listaMidia) {
            if (midia.retornaNome().equalsIgnoreCase(nomeMidia)) {
                encontrado = true;
                if (this.espectadorLogado.removerMidiaFuturas(midia)) {
                    remover = true;
                    return "Mídia removida com sucesso!";
                } else {
                    return "Mídia não está na lista.";
                }
            }
        }
        return "Mídia não existe, favor digitar o nome corretamente";

    }

    /**
     * Avalia uma mídia especificada pelo nome com um comentário e uma nota de
     * avaliação.
     * A avaliação é atribuída pelo espectador logado.
     * Se o espectador for um espectador especialista, será criada uma Avaliação com
     * comentário.
     * Se o espectador for um espectador regular, será criada uma Avaliação sem
     * comentário.
     * O perfil do espectador logado é atualizado após a avaliação.
     *
     * @param nomeMidia           o nome da mídia a ser avaliada
     * @param comentarioAvaliacao o comentário da avaliação (apenas para
     *                            espectadores especialistas)
     * @param notaAvaliacao       a nota da avaliação
     * @return uma mensagem indicando o resultado da avaliação
     */
    public String avaliarMidia(String nomeMidia, String comentarioAvaliacao, int notaAvaliacao) {
        Midia midia = this.retornaMidiaPorNome(nomeMidia);
        Avaliacao avaliacaoEspecialista = new Avaliacao(comentarioAvaliacao, new Data(), notaAvaliacao,
                midia.retornaId());
        Avaliacao avaliacaoRegular = new Avaliacao(new Data(), notaAvaliacao, midia.retornaId());
        this.espectadorLogado.atualizaPerfil();
        if (espectadorPodeAvaliarEComentar(midia)) {
            midia.Avaliar(avaliacaoEspecialista);
            this.espectadorLogado.adicionarAvaliacaoEspectador(avaliacaoEspecialista);
            return "Mídia avaliada como espectador especialista!";
        } else if (espectadorPodeAvaliarSemComentar(midia)) {
            midia.Avaliar(avaliacaoRegular);
            this.espectadorLogado.adicionarAvaliacaoEspectador(avaliacaoRegular);
            return "Mídia avaliada como espectador regular!";
        } else if (espectadorLogadoJaAvaliou(midia.retornaId())) {
            return "Mídia já avaliada! Impossível avaliar novamente.";
        } else {
            return "Você ainda não assistiu essa mídia! Logo impossível avaliá-la";
        }
    }

    /**
     * Busca uma mídia pelo seu ID.
     *
     * @param idMidia o ID da mídia a ser buscada
     * @return a mídia encontrada ou null se não for encontrada
     */
    public Midia buscaMidia(int idMidia) {
        for (Midia midia : listaMidia) {
            if (midia.retornaId() == idMidia) {
                return midia;
            }
        }
        return null;
    }

    /**
     * Retorna o espectador logado.
     *
     * @return o espectador logado
     */
    public Espectador getEspectadorLogado() {
        return this.espectadorLogado;
    }

    /**
     * Retorna uma string contendo a lista de avaliações do espectador logado.
     * Se a lista estiver vazia, retorna "Sem mídias avaliadas".
     *
     * @return a lista de avaliações do espectador logado ou a mensagem "Sem mídias
     *         avaliadas"
     */
    public String printaListaAvaliacoesDoEspectador() {
        String listaAvaliacoes = this.espectadorLogado.listaAvaliacoesToString();
        if (listaAvaliacoes.isEmpty()) {
            return "Sem mídias avaliadas.";
        }
        return listaAvaliacoes;
    }

    /**
     * Verifica se o espectador logado pode fazer comentários.
     *
     * @return true se o espectador logado pode fazer comentários, false caso
     *         contrário
     */
    public boolean espectadorLogadoPodeComentar() {
        return this.espectadorLogado.retornaPerfil().podeComentar();
    }

    /**
     * Verifica se o espectador logado já avaliou uma mídia com o ID especificado.
     *
     * @param idMidia o ID da mídia a ser verificada
     * @return true se o espectador logado já avaliou a mídia, false caso contrário
     */
    public boolean espectadorLogadoJaAvaliou(int idMidia) {
        return this.espectadorLogado.jaAvaliou(idMidia);
    }

    /**
     * Verifica se o espectador logado já assistiu uma determinada mídia.
     *
     * @param midia a mídia a ser verificada
     * @return true se o espectador logado já assistiu a mídia, false caso contrário
     */
    public boolean espectadorLogadoJaAssistiu(Midia midia) {
        return this.espectadorLogado.jaAssistiu(midia);
    }

    /**
     * Verifica se o espectador logado pode avaliar e comentar uma determinada
     * mídia.
     *
     * @param midia a mídia a ser verificada
     * @return true se o espectador logado pode avaliar e comentar a mídia, false
     *         caso contrário
     */
    public boolean espectadorPodeAvaliarEComentar(Midia midia) {
        return espectadorLogadoJaAssistiu(midia)
                && espectadorLogadoPodeComentar()
                && !espectadorLogadoJaAvaliou(midia.retornaId());
    }

    /**
     * Verifica se o espectador logado pode avaliar uma determinada mídia sem
     * comentar.
     *
     * @param midia a mídia a ser verificada
     * @return true se o espectador logado pode avaliar a mídia sem comentar, false
     *         caso contrário
     */
    public boolean espectadorPodeAvaliarSemComentar(Midia midia) {
        return espectadorLogadoJaAssistiu(midia)
                && !espectadorLogadoPodeComentar()
                && !espectadorLogadoJaAvaliou(midia.retornaId());
    }

    /**
     * Verifica se o espectador logado pode assistir a lançamentos.
     *
     * @return true se o espectador logado pode assistir a lançamentos, false caso
     *         contrário
     */
    public boolean espectadorPodeAssistirLancamento() {
        return this.espectadorLogado.retornaPerfil().podeAssistirLancamento();
    }

    /**
     * Gera um gênero aleatório.
     *
     * @return um gênero aleatório
     */
    public static String generoAleatorio() {
        String generoAleatorio = Genero.values()[new Random().nextInt(Genero.values().length)].toString();
        return generoAleatorio;
    }

    /**
     * Gera um idioma aleatório.
     *
     * @return um idioma aleatório
     */
    public static String idiomaAleatorio() {
        String idiomaAleatorio = Idioma.values()[new Random().nextInt(Idioma.values().length)].toString();
        return idiomaAleatorio;
    }

    /**
     * Gera um valor booleano aleatório para indicar se um espectador é um
     * profissional ou não.
     * O valor true indica que o espectador é um profissional, enquanto o valor
     * false indica que é um espectador regular.
     *
     * @return true se o espectador é um profissional, false se é um espectador
     *         regular
     */
    public static boolean profissionalAleatorio() {
        String profissionalAleatorio = Profissao
                .values()[new Random()
                        .nextInt(Profissao.values().length)]
                .toString();
        if (profissionalAleatorio.equals("REGULAR")) {
            return false;
        } else {
            return true;
        }
    }
}
