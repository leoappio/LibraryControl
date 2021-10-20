package com.company.menus;

import com.company.Screen;
import com.company.db.Database;
import com.company.models.Publication;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuPublication {
    public static Scanner scanner = new Scanner(System.in);
    public static void menuOptions() throws SQLException {
        while(true){
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if(choice.equals("1")){
                createPublication();
            }else if(choice.equals("2")){

            }else if(choice.equals("3")){

            }else if(choice.equals("4")){

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
