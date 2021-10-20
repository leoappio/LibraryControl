package com.company;

public class Screen {
    public static void clear(){
        for (int i = 0; i < 20; ++i)
            System.out.println();
    }

    private static void Header(){
        System.out.println("XYZ COMERCIO DE PRODUTOS LTDA");
        System.out.println("SISTEMA DE CONTROLE DE BIBLIOTECA");
        System.out.println("");

    }
    public static void MainMenu(){
        Header();
        System.out.println("Menu Principal");
        System.out.println("1 - Cadastro de usuários");
        System.out.println("2 - Cadastro de publicações");
        System.out.println("3 - Movimentação");
        System.out.println("4 - Relatórios");
        System.out.println("0 - Finalizar");
    }

    public static void ClientMenu(){
        Header();
        System.out.println("Cadastro de Usuários");
        System.out.println("1 - Inclusão");
        System.out.println("2 - Alteração");
        System.out.println("3 - Consulta");
        System.out.println("4 - Exclusão");
        System.out.println("0 - Retornar");
    }
    public static void PublicationMenu(){
        Header();
        System.out.println("Cadastro de Publicações");
        System.out.println("1 - Inclusão");
        System.out.println("2 - Alteração");
        System.out.println("3 - Consulta");
        System.out.println("4 - Exclusão");
        System.out.println("0 - Retornar");
    }
    public static void LoanMenu(){
        Header();
        System.out.println("1 - Empréstimo");
        System.out.println("2 - Devolução");
        System.out.println("3 - Retornar");
    }
    public static void ReportsMenu(){
        Header();
        System.out.println("1 - Carteira de usuário");
        System.out.println("2 - Balanço do Acervo");
        System.out.println("3 - Balanço de movimentações");
        System.out.println("0 - Retornar");
    }
}
