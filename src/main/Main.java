package main;

import java.util.List;
import java.util.Scanner;

import model.ArvoreBinariaBusca;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        construirArvoreInicial(arvore);

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            executarOpcao(opcao, arvore);
        } while (opcao != 10);

        scanner.close();
    }

    private static void construirArvoreInicial(ArvoreBinariaBusca arvore) {
        int quantidade;
        do {
            quantidade = lerInteiro("Quantos valores deseja inserir na árvore? ");
            if (quantidade < 0) {
                System.out.println("A quantidade não pode ser negativa.");
            }
        } while (quantidade < 0);

        for (int i = 1; i <= quantidade; i++) {
            int valor = lerInteiro("Digite o valor do nó " + i + ": ");
            inserirValor(arvore, valor);
        }
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("========== MENU ==========");
        System.out.println("1 - Inserir valor");
        System.out.println("2 - Buscar valor");
        System.out.println("3 - Mostrar Pré-ordem");
        System.out.println("4 - Mostrar Em ordem");
        System.out.println("5 - Mostrar Pós-ordem");
        System.out.println("6 - Mostrar BFS - Busca em Largura");
        System.out.println("7 - Mostrar DFS - Busca em Profundidade");
        System.out.println("8 - Mostrar altura da árvore");
        System.out.println("9 - Mostrar estrutura da árvore");
        System.out.println("10 - Sair");
        System.out.println("==========================");
    }

    private static void executarOpcao(int opcao, ArvoreBinariaBusca arvore) {
        switch (opcao) {
            case 1:
                inserirValor(arvore, lerInteiro("Digite o valor a inserir: "));
                break;
            case 2:
                buscarValor(arvore, lerInteiro("Digite o valor a buscar: "));
                break;
            case 3:
                mostrarPercurso("Pré-ordem (Raiz -> Esquerda -> Direita)", arvore.preOrdem());
                break;
            case 4:
                mostrarPercurso("Em ordem (Esquerda -> Raiz -> Direita)", arvore.emOrdem());
                break;
            case 5:
                mostrarPercurso("Pós-ordem (Esquerda -> Direita -> Raiz)", arvore.posOrdem());
                break;
            case 6:
                mostrarPercurso("BFS - Busca em Largura (por níveis)", arvore.bfs());
                break;
            case 7:
                mostrarPercurso("DFS - Busca em Profundidade", arvore.dfs());
                break;
            case 8:
                mostrarAltura(arvore);
                break;
            case 9:
                mostrarEstrutura(arvore);
                break;
            case 10:
                System.out.println("Encerrando o programa...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void inserirValor(ArvoreBinariaBusca arvore, int valor) {
        if (arvore.inserir(valor)) {
            System.out.println("Valor " + valor + " inserido com sucesso.");
        } else {
            System.out.println("O valor " + valor + " já existe na árvore e não foi inserido.");
        }
    }

    private static void buscarValor(ArvoreBinariaBusca arvore, int valor) {
        if (arvore.buscar(valor)) {
            System.out.println("O valor " + valor + " foi ENCONTRADO na árvore.");
        } else {
            System.out.println("O valor " + valor + " NÃO foi encontrado na árvore.");
        }
    }

    private static void mostrarPercurso(String nome, List<Integer> visitados) {
        if (visitados.isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }
        System.out.println("Percurso " + nome + ":");
        System.out.println("Nós visitados: " + visitados);
    }

    private static void mostrarAltura(ArvoreBinariaBusca arvore) {
        if (arvore.estaVazia()) {
            System.out.println("A árvore está vazia.");
            return;
        }
        System.out.println("Altura da árvore: " + arvore.altura());
    }

    private static void mostrarEstrutura(ArvoreBinariaBusca arvore) {
        if (arvore.estaVazia()) {
            System.out.println("A árvore está vazia.");
            return;
        }
        System.out.println("\n--- ESTRUTURA DA ÁRVORE ---");
        System.out.print(arvore.estrutura());
        System.out.println("----------------------------------------");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }
}