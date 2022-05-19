package com.example.knowuproject.modelo;

public class ListaObj<T> {

    // Atributos

    private T[] vetor;
    private int nroElem;

    // Construtor

    public ListaObj(Integer tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    // Getters

    public int getTamanho() {
        return this.nroElem;
    }

    public T getElemento(int indice) {

        if (nroElem > indice && indice >= 0) {
            return vetor[indice];
        }
        return null;
    }

    // Métodos

    public Boolean adicionar(T elemento) {

        if (nroElem < vetor.length) {

            vetor[nroElem++] = elemento;
            return true;
        }

        return false;
    }

    public void exibir() {
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public int buscar(T elemento) {
        int indiceEncontrado = -1;

        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elemento)) {
                indiceEncontrado = i;
            }
        }
        return indiceEncontrado;
    }

    public Boolean removerPeloIndice(int indice) {
        if (nroElem > indice && indice >= 0) {

            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            nroElem--;
            return true;

        }

        return false;
    }

    public void removerPeloElemento(T elemento) {
        System.out.println(removerPeloIndice(buscar(elemento)));

    }

    public void limparLista() {
        this.nroElem = 0;
    }

}
