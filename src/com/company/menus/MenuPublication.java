package com.company.menus;

import com.company.Screen;
import com.company.db.Database;
import com.company.models.Library;
import com.company.models.Publication;
import com.company.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPublication {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();
    public static void menuOptions() throws SQLException {
        while(true){
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if(choice.equals("1")){
                createPublication();
            }else if(choice.equals("2")){
                editPublication();
            }else if(choice.equals("3")){
                showAllPublications();
            }else if(choice.equals("4")){
                deletePublication();
            }else if(choice.equals("0")){
                Screen.MainMenu();
                break;
            }
        }
    }

    public static void createPublication() throws SQLException {
        System.out.print("Qual o titulo da publicação?: ");
        String title = scanner.nextLine();
        System.out.print("Qual o autor da publicação?: ");
        String author = scanner.nextLine();
        System.out.print("Qual a quantidade dessa publicação?: ");
        String quantityStr = scanner.nextLine();
        int quantity = Integer.parseInt(quantityStr);

        if(confirmOperation()){
            Publication publication = new Publication(title, author,quantity);
            Database.insertPublication(publication);
            System.out.println("Publicação cadastrada com sucesso!");
        }else{
            System.out.println("Operação cancelada!");
        }
        returnToMenu();
    }

    public static void editPublication() throws SQLException {
        Publication publication = choosePublication();
        System.out.print("Digite o novo titulo (antigo = " + publication.title + "): ");
        String newTitle = scanner.nextLine();
        System.out.print("Digite o novo nome do autor (antigo = " + publication.author + "): ");
        String newAuthor = scanner.nextLine();
        System.out.print("Digite a nova quantidade (antiga = " + publication.quantity + "): ");
        String newQuantityStr = scanner.nextLine();
        int newQuantity = Integer.parseInt(newQuantityStr);
        if (confirmOperation()) {
            publication.setTitle(newTitle);
            publication.setAuthor(newAuthor);
            publication.setQuantity(newQuantity);
            Database.updatePublication(publication);
            System.out.println("Publicação editada com sucesso");
        } else {
            System.out.println("Operação Cancelada!");
        }
        returnToMenu();
    }

    public static void showAllPublications() throws SQLException {
        ArrayList<Publication> publications = library.getAllPublications();

        for (Publication publication : publications) {
            System.out.println("------ Publicação " + publication.id + " ------");
            System.out.println("Titulo: " + publication.title);
            System.out.println("Autor: " + publication.author);
            System.out.println("Quantidade: " + publication.quantity);
            System.out.println("---------------------------");
        }
        returnToMenu();
    }

    public static void deletePublication() throws SQLException {
        Publication publication = choosePublication();
        if(confirmOperation()){
            Database.deletePublication(publication.id);
            System.out.println("Publicação apagada com sucesso!");
        }else{
            System.out.println("Operação Cancelada!");
        }
        returnToMenu();
    }

    public static Publication choosePublication() throws SQLException {
        ArrayList<Publication> publications = library.getAllPublications();
        int biggerId = 0;

        for (Publication publication : publications) {
            System.out.println("Publicação Nº " + publication.id);
            System.out.println("Titulo: " + publication.title);
            System.out.println("Autor: " + publication.author);
            System.out.println("Quantidade: " + publication.quantity);
            System.out.println("---------------------------");

            if (publication.id > biggerId) {
                biggerId = publication.id;
            }
        }

        while(true){
            System.out.print("Digite o número da publicação para selecionar:");
            String choiceString = scanner.nextLine();
            int choice = Integer.parseInt(choiceString);
            if(choice > 0 && choice <= biggerId){
                Publication publication = library.getPublicationById(choice);
                if(publication != null){
                    return publication;
                }else{
                    System.out.println("Não existe publicação com esse número!");
                }
            }else{
                System.out.println("Número inválido, escolha outro!");
            }
        }

    }


    public static void returnToMenu(){
        System.out.println();
        System.out.println("Aperte enter para voltar ao menu");
        scanner.nextLine();
        Screen.PublicationMenu();
    }

    public static boolean confirmOperation() {
        System.out.println("Deseja confirmar esta operação? [S/N]");
        String continueAnswer = scanner.nextLine();
        if (continueAnswer.toUpperCase().equals("S")) {
            return true;
        } else {
            return false;
        }
    }
}
