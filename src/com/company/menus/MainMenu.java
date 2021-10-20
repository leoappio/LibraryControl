package com.company.menus;

import com.company.Screen;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    public static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Screen.MainMenu();
        while(true){
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if(choice.equals("1")){
                Screen.ClientMenu();
                MenuUser.menuOptions();
            }else if(choice.equals("2")){
                Screen.PublicationMenu();
                MenuPublication.menuOptions();
            }else if(choice.equals("3")){
                Screen.LoanMenu();
                MenuLoan.menuOptions();
            }else if(choice.equals("4")){
                Screen.ReportsMenu();
                MenuReports.menuOptions();
            }else if(choice.equals("0")){
                System.out.println("Programa finalizado!");
                break;
            }else{
                System.out.println("Escolha inválida!");
            }
        }
    }

}
