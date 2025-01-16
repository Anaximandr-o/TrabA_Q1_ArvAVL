package com.antonioProjeto;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random gerador = new Random();
        int qtdNos = gerador.nextInt(10000, 1000000);
        ArvAVL arvore = new ArvAVL();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < qtdNos; j++) {
                int noRandom = gerador.nextInt(1000000);
                boolean b;
                do {
                    b = arvore.inserir(noRandom);
                } while (!b);
            }
            System.out.println("Quantidade de nós - " + qtdNos + " Altura esperada - " + (int) (Math.log(qtdNos) * 1.44) + " Altura - " + arvore.obterAltura());
        }
    }
}