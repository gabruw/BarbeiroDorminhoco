package barbeirodorminhoco;

import java.util.Random;

public class Barbeiro implements Runnable{

    public int cadeiraDeEspera;
    public boolean cadeiraOcupada = false;
    public int[] clientes;
    public boolean barbeiroDorme = false;
    public String nome;
    public int cliNovos;
    public int nClientes = 0;

    public Barbeiro(String nome, int cadeiraDeEspera, int clientes) {
        cliNovos = clientes;
        this.nome = nome;
        this.cadeiraDeEspera = cadeiraDeEspera;

        System.out.println("->> O Barbeiro " + nome + " chegou no salão.");
    }

    public void Clientes() {
        Random r = new Random();

        nClientes = r.nextInt(cliNovos);
        clientes = new int[nClientes];

        for (int i = 0; nClientes < clientes.length; i++) {
            clientes[i] = i;
        }
    }

    public void BarbeiroDorme() throws InterruptedException {
        System.out.println("\nNão existe(m) cliente(s) no salão do Barbeiro " + nome + ".");
        System.out.println("O Barbeiro " + nome + " está esperando a chegada de clientes.");
        Thread.sleep(4000);
        System.out.println("A cadeira do Barbeiro " + nome + " está livre.");

        Clientes();
    }

    public void BarbeiroAtende() throws InterruptedException {
        if (nClientes != 0) {
            if (nClientes > 1 && cadeiraOcupada == false) {
                System.out.println("\n->Entrou(aram) " + nClientes + " cliente(s) no salão.");
            } else {
                System.out.println("\n->Existe(m) " + nClientes + " cliente(s) esperando atendimento " + nome);
            }

            System.out.println("Um cliente ocupou a cadeira do Barbeiro " + nome);
            nClientes--;

            System.out.println("Um cliente está sendo atendido pelo Barbeiro " + nome);
            cadeiraOcupada = true;

            Thread.sleep(2000);

            if (nClientes > cadeiraDeEspera) {
                int cli = nClientes - cadeiraDeEspera;
                nClientes = nClientes - cli;

                for (int i = 0; i < clientes.length - 1; i++) {
                    clientes[i] = 0;
                }

                for (int j = 0; j < clientes.length; j++) {
                    clientes[j] = j + 1;
                }

                System.out.println(cli + "clientes foram embora.");
                System.out.println(nClientes + "clientes estam esperando.");
            }

            System.out.println("Um cliente já foi atendido pelo Barbeiro " + nome);
        } else if (nClientes == 1) {
            System.out.println("A cadeira do Barbeiro " + nome + " está livre.");

            System.out.println("A cadeira do Barbeiro " + nome + " está ocupada. Não existe clientes aguardando.");

            Thread.sleep(2000);
            nClientes--;

            System.out.println("Um cliente já foi atendido pelo Barbeiro " + nome);
        } else {
            System.out.println("A cadeira do Barbeiro " + nome + " está livre.");

            cadeiraOcupada = false;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (nClientes <= 0) {
                try {
                    BarbeiroDorme();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    BarbeiroAtende();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}