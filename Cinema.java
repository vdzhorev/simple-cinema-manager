package org.example;

import java.util.Scanner;

public class Cinema {

  private static int rows;
  private static int seats;
  private static char[][] cinema;
  private static int totalSeats;
  private static int currentIncome = 0;
  private static int totalIncome;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the number of rows:");
    rows = scanner.nextInt();
    System.out.println("Enter the number of seats in each row:");
    seats = scanner.nextInt();
    totalSeats = rows * seats;

    cinema = new char[rows][seats];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < seats; j++) {
        cinema[i][j] = 'S';
      }
    }

    menu();

    scanner.close();
  }

  public static void showSeats() {
    System.out.println("Cinema:");
    System.out.print("  ");
    for (int i = 1; i <= seats; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < rows; i++) {
      System.out.print((i + 1) + " ");
      for (int j = 0; j < seats; j++) {
        System.out.print(cinema[i][j] + " ");
      }
      System.out.println();
    }
    menu();
  }

  public static void buyATicket() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nEnter a row number:");
    int chosenRow = scanner.nextInt();
    System.out.println("Enter a seat number in that row:");
    int chosenSeat = scanner.nextInt();

    if (chosenSeat < 1 || chosenSeat > seats || chosenRow < 1 || chosenRow > rows) {
      System.out.println("Wrong input!");
      buyATicket();
    }

    // Calculate the ticket price
    int ticketPrice;
    int frontHalfRows = (rows % 2 == 0) ? rows / 2 : rows / 2 + 1;
    if (totalSeats > 60) {
      ticketPrice = (chosenRow < frontHalfRows) ? 10 : 8;
    } else {
      ticketPrice = 10;
    }

    // Mark the chosen seat as 'B'
    if (cinema[chosenRow - 1][chosenSeat - 1] == 'S') {
      cinema[chosenRow - 1][chosenSeat - 1] = 'B';
      // Print the ticket price
      System.out.println("\nTicket price: $" + ticketPrice);
      currentIncome += ticketPrice;
      menu();
    } else {
      System.out.println("That ticket has already been purchased!");
      buyATicket();
    }
  }

  public static void exit() {
  }

  public static void statistics() {
    int purchasedTickets = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < seats; j++) {
        if (cinema[i][j] == 'B') {
          purchasedTickets++;
        }
      }
    }
    double percentagePurchasedTickets = (double) purchasedTickets / totalSeats * 100;
    totalIncome = calculateTotalIncome();

    System.out.printf("Number of purchased tickets: %d", purchasedTickets);
    System.out.printf("\nPercentage: %.2f%%", percentagePurchasedTickets);
    System.out.printf("\nCurrent income: $%d", currentIncome);
    System.out.printf("\nTotal income: $%d", totalIncome);
    menu();
  }

  public static void menu() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(" ");
    System.out.println("1. Show the seats");
    System.out.println("2. Buy a ticket");
    System.out.println("3. Statistics");
    System.out.println("0. Exit");
    int chosenOption = scanner.nextInt();

    switch (chosenOption) {
      case 1:
        showSeats();
        break;
      case 2:
        buyATicket();
        break;
      case 3:
        statistics();
        break;
      case 0:
        exit();
        break;
    }
  }

  private static int calculateTotalIncome() {
    int total = 0;
    int frontHalfRows = rows / 2;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < seats; j++) {
        if (totalSeats > 60) {
          total += (i < frontHalfRows) ? 10 : 8;
        } else {
          total += 10;
        }
      }
    }
    return total;
  }
}