package com.company.menus;

import com.company.Screen;
import com.company.db.Database;
import com.company.models.Library;
import com.company.models.Loan;
import com.company.models.Publication;
import com.company.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuLoan {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();

    public static void menuOptions() throws SQLException {
        while (true) {
            System.out.print("Digite o número da sua escolha: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                realizeLoan();
            } else if (choice.equals("2")) {
                returnPublication();
            } else if (choice.equals("3")) {
                Screen.MainMenu();
                break;
            }
        }
    }

    public static void realizeLoan() throws SQLException {
        User user = MenuUser.chooseUser();
        Publication publication = choosePublicationToLoan();

        Loan verifyLoan = Database.getLoan(user.id, publication.id);

        if (confirmOperation()) {
            if (verifyLoan == null || verifyLoan.isReturned.equals("s")) {
                Loan loan = new Loan(user.id, publication.id, 0, "n");
                Database.insertLoan(loan);
                publication.decreaseQuantity();
                Database.updatePublication(publication);
                System.out.println("Empréstimo Realizado!");
            } else {
                System.out.println("Esta publicação já está emprestada para esse usuário");
            }
        } else {
            System.out.println("Operação Cancelada!");

        }
        returnToMenu();

    }

    public static void returnPublication() throws SQLException {
        User user = MenuUser.chooseUser();
        ArrayList<Publication> publications = Database.getPublicationsFromUserId(user.id);

        if (publications.size() > 0) {
            Publication publication = choosePublicationFromArray(publications);
            System.out.println("Esta devolução está atrasada?(S/N)");
            String isLate = scanner.nextLine();
            int lateDays = 0;
            if (isLate.equalsIgnoreCase("S")) {
                System.out.println("Quantos dias ela está atrasada?");
                String lateDaysString = scanner.nextLine();
                lateDays = Integer.parseInt(lateDaysString);
            }
            if (confirmOperation()) {
                Loan loan = Database.getActualLoan(user.id, publication.id);
                loan.setLateDays(lateDays);
                loan.setIsReturned("s");

                Database.updateLoan(loan);
                publication.increaseQuantity();
                Database.updatePublication(publication);
                System.out.println("Devolução Realizada!");
            } else {
                System.out.println("Operação Cancelada!");
            }
        } else {
            System.out.println("Este usuario não tem nenhum emprestimo em sua conta!");
        }
        returnToMenu();

    }

    public static Publication choosePublicationToLoan() throws SQLException {
        ArrayList<Publication> publications = library.getAllPublications();
        int biggerId = 0;
        ArrayList<Integer> availablePublicationsIds = new ArrayList<Integer>();
        for (Publication publication : publications) {
            if (publication.quantity > 0) {
                System.out.println("Publicação Nº " + publication.id);
                System.out.println("Titulo: " + publication.title);
                System.out.println("Autor: " + publication.author);
                System.out.println("Quantidade: " + publication.quantity);
                System.out.println("---------------------------");
                if (publication.id > biggerId) {
                    biggerId = publication.id;
                }
                availablePublicationsIds.add(publication.id);
            }
        }
        while (true) {
            System.out.print("Digite o número da publicação para alugar:");
            String numberPublication = scanner.nextLine();
            int choice = Integer.parseInt(numberPublication);
            if (choice > 0 && choice <= biggerId) {
                if (availablePublicationsIds.contains(choice)) {
                    Publication publication = library.getPublicationById(choice);
                    if (publication != null) {
                        return publication;
                    } else {
                        System.out.println("Não existe publicação com esse número!");
                    }
                } else {
                    System.out.println("Esta publicação não está disponível!");
                }

            } else {
                System.out.println("Número inválido, escolha outro!");
            }
        }

    }

    public static Publication choosePublicationFromArray(ArrayList<Publication> publications) throws SQLException {
        int biggerId = 0;
        System.out.println("Publicações emprestadas para este usuário");
        for (Publication publication : publications) {
            System.out.println("Publicação Nº " + publication.id);
            System.out.println("Titulo: " + publication.title);
            System.out.println("Autor: " + publication.author);
            System.out.println("---------------------------");

            if (publication.id > biggerId) {
                biggerId = publication.id;
            }
        }

        while (true) {
            System.out.print("Digite o número da publicação para devolver:");
            String choiceString = scanner.nextLine();
            int choice = Integer.parseInt(choiceString);
            if (choice > 0 && choice <= biggerId) {
                Publication publication = library.getPublicationById(choice);
                if (publication != null) {
                    return publication;
                } else {
                    System.out.println("Não existe publicação com esse número!");
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
        Screen.LoanMenu();
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
