package com.company.menus;

import com.company.Screen;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuUser {
    public static Scanner scanner = new Scanner(System.in);

    public static void ReadClientMenu() throws SQLException {
        while (true) {
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {

            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {

            } else if (choice.equals("4")) {

            } else if (choice.equals("0")) {
                Screen.MainMenu();
                break;
            }
        }
    }

    public static void returnToMenu() {
        System.out.println();
        System.out.println("Aperte enter para voltar ao menu");
        scanner.nextLine();
        Screen.clear();
        Screen.ClientMenu();
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
