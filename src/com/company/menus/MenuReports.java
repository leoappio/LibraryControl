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
        while(true){
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if(choice.equals("1")){
                userCard();
            }else if(choice.equals("2")){
                collectionBalance();
            }else if(choice.equals("3")){
                movementsBalance();
            }else if(choice.equals("0")){
                Screen.MainMenu();
                break;
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
        float averageLateDays = shop.getAverageLate();

        System.out.println("Total de clientes: "+ totalClients);
        System.out.println("Total de filmes: "+ totalMovies);
        System.out.println("Total de locações no mês: "+ totalLocations);
        System.out.println("Filmes 24 Horas: "+ total24hMovies);
        System.out.println("Filmes 48 Horas: "+ total48hMovies);
        System.out.printf("Média de locações por usuário: "+ "%.2f",averageLocations);
        System.out.println();
        System.out.printf("Média de tempo de atraso na devolução dos filmes (em dias): "+ "%.2f", averageLateDays);
        System.out.println();
        System.out.println();
        System.out.println("10 FILMES MAIS LOCADOS:");
        ArrayList<Movie> movies = shop.getTop10Movies();

        for(int i = 0; i<movies.size();i++){
            int movieTotalLocations = shop.getTotalLocationsByMovieId(movies.get(i).id);
            System.out.println((i+1)+" - COD: "+movies.get(i).id+" - "+movies.get(i).title+" - Nº de locações: "+movieTotalLocations);
        }

        System.out.println();
        System.out.println("10 MELHORES CLIENTES:");
        ArrayList<Client> clients = shop.getTop10Clients();

        for(int i = 0; i<clients.size();i++){
            int clientTotalLocations = shop.getTotalLocationsByClientId(clients.get(i).id);
            System.out.println((i+1)+" - COD: "+clients.get(i).id+" - "+clients.get(i).name+" - Nº de locações: "+clientTotalLocations);
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
