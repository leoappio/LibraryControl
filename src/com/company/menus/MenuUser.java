package com.company.menus;

import com.company.Screen;
import com.company.db.Database;
import com.company.models.Library;
import com.company.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuUser {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();

    public static void menuOptions() throws SQLException {
        while (true) {
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                createUser();
            } else if (choice.equals("2")) {
                editUser();
            } else if (choice.equals("3")) {
                showAllUsers();
            } else if (choice.equals("4")) {
                deleteUser();
            } else if (choice.equals("0")) {
                Screen.MainMenu();
                break;
            }
        }
    }

    public static void createUser() throws SQLException {
        System.out.print("Qual a matricula do usuario?: ");
        String registration = scanner.nextLine();
        System.out.print("Qual o nome do usuario?: ");
        String name = scanner.nextLine();

        if (confirmOperation()) {
            User user = new User(registration, name);
            Database.insertUser(user);
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Operação Cancelada!");
        }

        returnToMenu();
    }

    public static void editUser() throws SQLException {
        User user = chooseUser();
        System.out.print("Digite a nova matricula (antiga = " + user.registration + "): ");
        String newRegistration = scanner.nextLine();
        System.out.print("Digite o novo nome (antigo = " + user.name + "): ");
        String newName = scanner.nextLine();
        if (confirmOperation()) {
            user.setName(newName);
            user.setRegistration(newRegistration);
            Database.updateUser(user);
            System.out.println("Usuário editado com sucesso");
        } else {
            System.out.println("Operação Cancelada!");
        }
        returnToMenu();
    }

    public static void showAllUsers() throws SQLException {
        ArrayList<User> users = library.getAllUsers();

        for (User user : users) {
            System.out.println("-------- Usuário " + user.id + " --------");
            System.out.println("Matrícula: " + user.registration);
            System.out.println("Nome: " + user.name);
            System.out.println("---------------------------");
        }
        returnToMenu();
    }

    public static void deleteUser() throws SQLException {
        User userToDelete = chooseUser();
        if (confirmOperation()) {
            Database.deleteUser(userToDelete.id);
            System.out.println("Usuário apagado com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }
        returnToMenu();
    }

    public static User chooseUser() throws SQLException {
        ArrayList<User> users = library.getAllUsers();
        int biggerId = 0;

        for (User user : users) {
            System.out.println("Usuário Nº " + user.id);
            System.out.println("Matrícula: " + user.registration);
            System.out.println("Nome: " + user.name);
            System.out.println("---------------------------");

            if (user.id > biggerId) {
                biggerId = user.id;
            }
        }

        while (true) {
            System.out.print("Digite o número do usuario para selecionar");
            String choiceString = scanner.nextLine();
            int choice = Integer.parseInt(choiceString);
            if (choice > 0 && choice <= biggerId) {
                User user = library.getUserById(choice);
                if (user != null) {
                    return user;
                } else {
                    System.out.println("Não existe usuario com esse número!");
                }
            } else {
                System.out.println("Número inválido, escolha outro!");
            }
        }

    }

    public static void returnToMenu() {
        System.out.println();
        System.out.println("Aperte enter para voltar ao menu");
        scanner.nextLine();
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
