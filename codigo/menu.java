package codigo;

import java.io.IOException;
import java.util.Scanner;

public class menu {
    private Plataforma plataforma;
    private Scanner input;
    private boolean sair = false;

    public menu() throws IOException {
        plataforma = new Plataforma();
        input = new Scanner(System.in);
        exibirMenuSemLogin();
    }

    public void limparTela(){
        System.out.print("\033[H\033[2J");
    }

    public void confirmarLimparTela(){
        System.out.println("\u001B[47mPara sair, pressione enter.\u001B[40m");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.print("\033[H\033[2J");
    }

    public void exibirMenuSemLogin() {
        limparTela();
        while (!sair) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         \u001B[36mStreaming\u001B[37m            ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║\u001B[32m 1. Efetuar login \u001B[37m            ║");
            System.out.println("║\u001B[31m 2. Sair	\u001B[37m               ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opção: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    efetuarLogin();
                    break;
                case 2:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        System.out.println();
    }

    public void exibirMenuLogado() {
        while (!sair) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║          \u001B[36mMenu\u001B[37m            ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║\u001B[32m1. Adicionar série futura\u001B[37m ║");
            System.out.println("║\u001B[32m2. Adicionar série assist.\u001B[37m║");
            System.out.println("║\u001B[31m3. Remover série futura\u001B[37m   ║");
            System.out.println("║\u001B[32m4. Adicionar filme futuro\u001B[37m ║");
            System.out.println("║\u001B[32m5. Adicionar filme assist.\u001B[37m║");
            System.out.println("║\u001B[31m6. Remover filme futuro\u001B[37m   ║");
            System.out.println("║\u001B[32m7. Adicionar avaliação\u001B[37m    ║");
            System.out.println("║\u001B[35m8. Buscar mídia por idioma\u001B[37m║");
            System.out.println("║\u001B[35m9. Buscar mídia por gênero\u001B[37m║");
            System.out.println("║\u001B[35m10. Buscar mídia por nome\u001B[37m ║");
            System.out.println("║\u001B[33m11.Informações sobre mídia\u001B[37m║");
            System.out.println("║\u001B[34m12. Listar avaliações\u001B[37m     ║");
            System.out.println("║\u001B[31m13. Efetuar logout\u001B[37m        ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Opção: ");

            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da série futura: ");
                    String nomeSerieFutura = input.nextLine();
                    plataforma.adicionarMidiaFutura(nomeSerieFutura);
                    confirmarLimparTela();
                    break;
                case 2:
                    System.out.println("Digite o nome da série assistida: ");
                    String nomeSerieAssistida = input.nextLine();
                    plataforma.adicionarMidiaAssistida(nomeSerieAssistida);
                    confirmarLimparTela();
                    break;
                case 3:
                    System.out.println("Digite o nome da série futura a ser removida: ");
                    String nomeSerieFuturaRemover = input.nextLine();
                    plataforma.removerlMidiaFutura(nomeSerieFuturaRemover);
                    confirmarLimparTela();
                    break;
                case 4:
                    System.out.println("Digite o nome do filme futuro: ");
                    String nomeFilmeFuturo = input.nextLine();
                    plataforma.adicionarMidiaFutura(nomeFilmeFuturo);
                    confirmarLimparTela();
                    break;
                case 5:
                    System.out.println("Digite o nome do filme assistido: ");
                    String nomeFilmeAssistido = input.nextLine();
                    plataforma.adicionarMidiaAssistida(nomeFilmeAssistido);
                    confirmarLimparTela();
                    break;
                case 6:
                    System.out.println("Digite o nome do filme futuro a ser removido: ");
                    String nomeFilmeFuturoRemover = input.nextLine();
                    plataforma.removerlMidiaFutura(nomeFilmeFuturoRemover);
                    confirmarLimparTela();
                    break;
                case 7:
                    System.out.println("Digite o ID da mídia: ");
                    int idMidia = input.nextInt();
                    input.nextLine();
                    System.out.println("Digite a avaliação (1 a 5): ");
                    int avaliacao = input.nextInt();
                    input.nextLine();
                    //TO DO: Emanuel realizar avaliação e implementar no menu.
                    confirmarLimparTela();
                    break;
                case 8:
                    System.out.println("Digite o idioma a ser buscado: ");
                    String idioma = input.nextLine();
                    String midiasPorIdioma = plataforma.buscaIdiomaGeneroString(idioma, "idioma");
                    System.out.println(midiasPorIdioma);
                    confirmarLimparTela();
                    break;
                case 9:
                    System.out.println("Digite o gênero a ser buscado: ");
                    String genero = input.nextLine();
                    String midiasPorGenero = plataforma.buscaIdiomaGeneroString(genero, "genero");
                    System.out.println(midiasPorGenero);
                    confirmarLimparTela();
                    break;
                case 10:
                    System.out.println("Digite o nome a ser buscado: ");
                    String nome = input.nextLine();
                    String midiasPorNome = plataforma.buscaIdiomaGeneroString(nome, "nome");
                    System.out.println(midiasPorNome);
                    confirmarLimparTela();
                    break;
                case 11:
                    System.out.println("Digite o ID da série: ");
                    int idSerie = input.nextInt();
                    input.nextLine();
                    plataforma.infoMidia(idSerie);
                    confirmarLimparTela();
                    break;
                case 12:
                    plataforma.getListaAvaliacoes();
                    break;
                case 13:
                    System.out.println("Efetuando logout...");
                    efetuarLogout();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    confirmarLimparTela();
                    break;
            }
        }
        System.out.println();
    }

    private void efetuarLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String usuario = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        boolean loginSucesso = plataforma.efetuarLogin(usuario, senha);
        if (loginSucesso) {
            System.out.println("Login realizado com sucesso!");
            limparTela();
            exibirMenuLogado();
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }

    private void efetuarLogout() {
        plataforma.efetuarLogout();
        System.out.println("Logout realizado com sucesso!");
        limparTela();
        exibirMenuSemLogin();
    }

    public static void main(String[] args) throws IOException {
        menu menu = new menu();
    }
}