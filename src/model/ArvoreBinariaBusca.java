package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class ArvoreBinariaBusca {

    private No raiz;

    public boolean estaVazia() {
        return raiz == null;
    }

    public boolean inserir(int valor) {
        if (buscar(valor)) {
            return false;
        }
        raiz = inserirRecursivo(raiz, valor);
        return true;
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }
        if (valor < atual.getValor()) {
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), valor));
        } else {
            atual.setDireita(inserirRecursivo(atual.getDireita(), valor));
        }
        return atual;
    }


    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No atual, int valor) {
        if (atual == null) {
            return false;
        }
        if (valor == atual.getValor()) {
            return true;
        }
        if (valor < atual.getValor()) {
            return buscarRecursivo(atual.getEsquerda(), valor);
        }
        return buscarRecursivo(atual.getDireita(), valor);
    }


    public List<Integer> preOrdem() {
        List<Integer> visitados = new ArrayList<>();
        preOrdemRecursivo(raiz, visitados);
        return visitados;
    }

    private void preOrdemRecursivo(No atual, List<Integer> visitados) {
        if (atual == null) {
            return;
        }
        visitados.add(atual.getValor());
        preOrdemRecursivo(atual.getEsquerda(), visitados);
        preOrdemRecursivo(atual.getDireita(), visitados);
    }


    public List<Integer> emOrdem() {
        List<Integer> visitados = new ArrayList<>();
        emOrdemRecursivo(raiz, visitados);
        return visitados;
    }

    private void emOrdemRecursivo(No atual, List<Integer> visitados) {
        if (atual == null) {
            return;
        }
        emOrdemRecursivo(atual.getEsquerda(), visitados);
        visitados.add(atual.getValor());
        emOrdemRecursivo(atual.getDireita(), visitados);
    }


    public List<Integer> posOrdem() {
        List<Integer> visitados = new ArrayList<>();
        posOrdemRecursivo(raiz, visitados);
        return visitados;
    }

    private void posOrdemRecursivo(No atual, List<Integer> visitados) {
        if (atual == null) {
            return;
        }
        posOrdemRecursivo(atual.getEsquerda(), visitados);
        posOrdemRecursivo(atual.getDireita(), visitados);
        visitados.add(atual.getValor());
    }


    public List<Integer> bfs() {
        List<Integer> visitados = new ArrayList<>();
        if (raiz == null) {
            return visitados;
        }
        Queue<No> fila = new ArrayDeque<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            No atual = fila.remove();
            visitados.add(atual.getValor());
            if (atual.getEsquerda() != null) {
                fila.add(atual.getEsquerda());
            }
            if (atual.getDireita() != null) {
                fila.add(atual.getDireita());
            }
        }
        return visitados;
    }


    public List<Integer> dfs() {
        List<Integer> visitados = new ArrayList<>();
        if (raiz == null) {
            return visitados;
        }
        Deque<No> pilha = new ArrayDeque<>();
        pilha.push(raiz);
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            visitados.add(atual.getValor());
            if (atual.getDireita() != null) {
                pilha.push(atual.getDireita());
            }
            if (atual.getEsquerda() != null) {
                pilha.push(atual.getEsquerda());
            }
        }
        return visitados;
    }

    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(No atual) {
        if (atual == null) {
            return -1;
        }
        int alturaEsquerda = alturaRecursiva(atual.getEsquerda());
        int alturaDireita = alturaRecursiva(atual.getDireita());
        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    public String estrutura() {
        StringBuilder sb = new StringBuilder();
        montarEstrutura(raiz, 0, sb);
        return sb.toString();
    }

    private void montarEstrutura(No atual, int espaco, StringBuilder sb) {
        if (atual == null) {
            return;
        }
        final int ESPACOS = 5;
        espaco += ESPACOS;

        montarEstrutura(atual.getDireita(), espaco, sb);

        sb.append("\n");
        for (int i = ESPACOS; i < espaco; i++) {
            sb.append(" ");
        }
        sb.append(atual.getValor()).append("\n");

        montarEstrutura(atual.getEsquerda(), espaco, sb);
    }
}