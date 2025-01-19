package com.antonioProjeto;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Main {
    public static void main(String[] args) {
        Random gerador = new Random();
        for(int i = 1; i<101;i++){
            int qtdNos = gerador.nextInt(10000, 1000000);
            DescriptiveStatistics stats = new DescriptiveStatistics();
                for (int x = 0; x < 100; x++) {
                    ArvAVL arvore = new ArvAVL();
                        for (int j = 0; j < qtdNos; j++) {
                            boolean b;
                            do {
                                int noRandom = gerador.nextInt(1000000);
                                b = arvore.inserir(noRandom);
                            } while (!b);
                        }
                        stats.addValue(arvore.obterAltura());
                }
                System.out.println("Quantidade de nós - " + qtdNos + " Altura esperada - " + (int) (Math.log(qtdNos) * 1.44 / Math.log(2)) + " Média das alturas - "+ stats.getMean() + " Desvio padrão - " + (float)stats.getStandardDeviation());
        }
    }
}