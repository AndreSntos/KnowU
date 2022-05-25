package com.example.knowuproject.modelo;


public class PilhaObj<T> {

    // Atributos
    private T[] pilha;
    private int topo;
    private int tamanho;

    // Construtor
    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
        tamanho = capacidade;
    }

    // Métodos

    public Boolean isEmpty() {
        return topo == -1;

    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T elemento) {
        if (isFull()) {
            System.out.println("exercicio1.Pilha cheia!");
        } else {
//            topo++;
//            pilha[topo] = info;
            // as 2 instruções acima equivalem a esta abaixo:
            pilha[++topo] = elemento;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
//        int retorno = pilha[topo];
//        topo--;
//        return retorno;

        return pilha[topo--];
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("exercicio1.Pilha vazia");
        } else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}