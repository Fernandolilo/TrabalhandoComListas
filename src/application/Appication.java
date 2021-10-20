package application;

import jdk.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Appication {
    public static void main(String[] args) {
        List<Gato> meusGatos = new ArrayList<>(){{
            add(new Gato("jon", 18, "preto"));
            add(new Gato("Simba", 6, "rajado"));
            add(new Gato("Jon", 12, "branco"));
        }};

        System.out.println("---\tOrdem de Inserção\t---");
        System.out.println(meusGatos);

        /*
         * Para imprimir aleatória podemos fazer o uso da class shufle
         */
        System.out.println("---\tOrdem de aleatória\t---");
        Collections.shuffle(meusGatos);
        System.out.println(meusGatos);

        /*
        * Para efetura a impreção na ordem natural, vamos fazer uso da class compareble
        */

        System.out.println("---\tOrdem de Natural (Nome)\t---");
        Collections.sort(meusGatos);
        System.out.println(meusGatos);

        /*
         * Para efetura a impreção na ordem da idade criando uma nova class que
         * implementa o Comparator da class <Gato>.
         */

        System.out.println("---\tOrdem idade\t---");
        // tando posso fazer esta comparação como a proxima abaixo
        // Collections.sort(meusGatos, new ComparetorIdade());
        meusGatos.sort(new ComparetorIdade());
        System.out.println(meusGatos);

        System.out.println("---\tOrdem de cor\t---");
        meusGatos.sort(new ComparetorCor());
        System.out.println(meusGatos);

        System.out.println("---\tOrdem Nome/cor/Idade\t---");
        meusGatos.sort(new ComparatorNomeCorIdade());
        System.out.println(meusGatos);
    }

}
class Gato implements Comparable<Gato>{
    private String nome;
    private Integer idade;
    private String cor;

    public Gato(String nome, Integer idade, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cor='" + cor + '\'' +
                '}';
    }

    @Override
    public int compareTo(Gato gato) {
        /*
        *  o metodo compareTo retorna um int, nesca caso ele vai verificar se
        *  o nome for igual a zero e o outro nome comparado tambem for igual a zero, então eles ficaram proximo.
        *  se o nome for igual a 1 então ficará apos o nome na posição zero;
        *  se o nome coparado for igual a menos 1 por exemplo ficará anterios ao nome na posição zero
        *  neste caso esta posicionando de A a Z
        */
        return this.getNome().compareToIgnoreCase(gato.getNome());
    }
}
class ComparetorIdade implements Comparator<Gato> {
    /*
     * Estamos criando uma nova class para implementar o comparator, para analisar a idade
     * neste estamos criando uma sobre carga abaixo, esta sobre carga da Clas gato
     * esta analizando um integer, para posicionar ele, se for == -1 ficará primeiro,
     * e assim continua verificando se ==0 proximo, e assim segue a lista até o final.
     */

    @Override
    public int compare(Gato g1, Gato g2) {
        return Integer.compare(g1.getIdade(), g2.getIdade());
    }
}

class ComparetorCor implements Comparator<Gato> {
    /*
     * Estamos criando uma nova class para implementar o comparator, para analisar a cor
     * neste estamos criando uma sobre carga abaixo, esta sobre carga da Clas gato
     * esta analizando um integer, para posicionar ele, se for == -1 ficará primeiro,
     * e assim continua verificando se ==0 proximo, e assim segue a lista até o final.
     */

    @Override
    public int compare(Gato g1, Gato g2) {
        /*
         * Como estamos trabalhando com string agora, e estamos gora da class gato nao
         * há a possibilidade de fazer uso do this.
         * entao passamos por paramentro como abaixo.
         */
        return g1.getCor().compareToIgnoreCase(g2.getCor());

    }
}
class ComparatorNomeCorIdade implements Comparator<Gato>{

    @Override
    public int compare(Gato g1, Gato g2) {
        //estamos comarano se nomes são ==
        int nome = g1.getNome().compareToIgnoreCase(g2.getNome());
        // efetua o teste como comparator retorna um int, se tudo == 0 são iguais
        // por isto testamos a != 0 se for retorna o nome
        if(nome != 0){
            return nome;
        }
        // segundo teste e feito na cor
        int cor = g1.getCor().compareToIgnoreCase(g2.getCor());
        if(cor != 0){
            return cor;
        }
        // terceiro teste feito na idade.
        return Integer.compare(g1.getIdade(), g2.getIdade());
    }
}