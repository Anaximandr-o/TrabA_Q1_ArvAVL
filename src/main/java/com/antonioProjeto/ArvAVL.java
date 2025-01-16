package com.antonioProjeto;

public class ArvAVL {

    static class Node {
        int chave, altura;
        Node esquerda, direita;

        public Node(int chave) {
            this.chave = chave;
            this.altura = 1;
        }
    }

    private Node raiz;

    // Função utilitária para obter a altura da árvore
    private int altura(Node node) {
        return node == null ? 0 : node.altura;
    }

    // Função utilitária para obter o fator de balanceamento de um nó
    private int obterBalanceamento(Node node) {
        return node == null ? 0 : altura(node.esquerda) - altura(node.direita);
    }

    // Rotação à direita na subárvore enraizada em y
    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        // Realiza a rotação
        x.direita = y;
        y.esquerda = T2;

        // Atualiza as alturas
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        // Retorna a nova raiz
        return x;
    }

    // Rotação à esquerda na subárvore enraizada em x
    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        // Realiza a rotação
        y.esquerda = x;
        x.direita = T2;

        // Atualiza as alturas
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        // Retorna a nova raiz
        return y;
    }

    // Insere um nó na Árvore AVL
    public boolean inserir(int chave) {
        raiz = inserir(raiz, chave);
        return raiz != null;
    }

    private Node inserir(Node node, int chave) {
        // Realiza a inserção normal em uma BST
        if (node == null) {
            return new Node(chave);
        }

        if (chave < node.chave) {
            node.esquerda = inserir(node.esquerda, chave);
        } else if (chave > node.chave) {
            node.direita = inserir(node.direita, chave);
        } else {
            // Chaves duplicadas não são permitidas na Árvore AVL
            return null;
        }

        // Atualiza a altura do nó ancestral
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));

        // Obtém o fator de balanceamento para verificar se o nó ficou desbalanceado
        int balanceamento = obterBalanceamento(node);

        // Casos de desbalanceamento

        // Caso Esquerda-Esquerda
        if (balanceamento > 1 && chave < node.esquerda.chave) {
            return rotacaoDireita(node);
        }

        // Caso Direita-Direita
        if (balanceamento < -1 && chave > node.direita.chave) {
            return rotacaoEsquerda(node);
        }

        // Caso Esquerda-Direita
        if (balanceamento > 1 && chave > node.esquerda.chave) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }

        // Caso Direita-Esquerda
        if (balanceamento < -1 && chave < node.direita.chave) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }

        // Retorna o nó sem alterações
        return node;
    }

    // Função utilitária para imprimir a travessia em pré-ordem da árvore
    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(Node node) {
        if (node != null) {
            System.out.print(node.chave + " ");
            preOrdem(node.esquerda);
            preOrdem(node.direita);
        }
    }

    public int obterAltura() {
        return altura(raiz);
    }
}

