package com.company.menus;

import com.company.Screen;
import com.company.db.Database;
import com.company.models.Library;
import com.company.models.Publication;
import com.company.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuReports {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();
    public static void menuOptions() throws SQLException {
        label:
        while(true){
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userCard();
                    break;
                case "2":
                    collectionBalance();
                    break;
                case "3":
                    movementsBalance();
                    break;
                case "0":
                    Screen.MainMenu();
                    break label;
            }
        }
    }

    public static void userCard() throws SQLException {
        User user = MenuUser.chooseUser();
        ArrayList<Publication> allPublications = Database.getAllPublicationsFromUserId(user.id);
        System.out.println("-------------------------------------");
        System.out.println("Matrícula - "+ user.registration);
        System.out.println("Nome - "+ user.name);
        System.out.println("Total de empréstimos = "+ allPublications.size());
        System.out.println("-------------------------------------");

        System.out.println("-------- Todos os empréstimos -------");

        for(Publication publication : allPublications){
            System.out.println(publication.id + "- " + publication.title + "- "+publication.author);
            System.out.println("-------------------------------------");
        }
    }

    public static void collectionBalance() throws SQLException {
        System.out.println("XYZ COMERCIO DE PRODUTOS LTDA.");
        System.out.println("SISTEMA DE CONTROLE DE BIBLIOTECA");
        System.out.println();
        System.out.println("BALANÇO DE ACERVO");
        ArrayList<Publication> publications = library.getAllPublications();
        System.out.println("COD |   TITULO   |   AUTOR   | QUANTIDADE");
        for (Publication publication : publications) {
            System.out.println(publication.id + "- " + publication.title + "- "+publication.author+"- " + publication.quantity);
            System.out.println("-------------------------------------");
        }
        System.out.println("Total: "+ publications.size());
        returnToMenu();
    }
    public static void movementsBalance() throws SQLException {
        System.out.println("XYZ COMERCIO DE PRODUTOS LTDA.");
        System.out.println("SISTEMA DE CONTROLE DE BIBLIOTECA");
        System.out.println();
        System.out.println("BALANÇO DE MOVIMENTAÇÕES");

        int totalUsers = library.getTotalUsers();
        int totalPublications = library.getTotalPublications();
        int totalLoans = Database.getTotalLoans();
        float averageLoans = (float)totalLoans / totalUsers;
        float averageLateDays = Database.getAverageLateDays();

        System.out.println("Total de usuarios: "+ totalUsers);
        System.out.println("Total de publicações no acervo: "+ totalPublications);
        System.out.println("Total de empréstimos no mês: "+ totalLoans);
        System.out.printf("Média de movimentações por usuario: "+ "%.2f",averageLoans);
        System.out.println();
        System.out.printf("Média de tempo de atraso na devolução dos livros: "+ "%.2f", averageLateDays);
        System.out.println();
        System.out.println("10 LIVROS MAIS EMPRESTADOS:");
        ArrayList<Publication> top10Publications = Database.getTop10Publications();

        for(int i = 0; i < top10Publications.size();i++){
            int TotalLocations = Database.getTotalLoanByPublicationId(top10Publications.get(i).id);
            System.out.println((i+1)+" - CODIGO: "+top10Publications.get(i).id+" - "+top10Publications.get(i).title+" - "+top10Publications.get(i).author+" - Nº de empréstimos: "+TotalLocations);
        }

        returnToMenu();

    }

    public static void returnToMenu(){
        System.out.println();
        System.out.println("Aperte enter para voltar ao menu");
        scanner.nextLine();
        Screen.ReportsMenu();
    }
}
