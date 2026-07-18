package com.oasisinfobyte.atm;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Bank bank = new Bank();
		ATM atm = new ATM(bank);

		System.out.println("==================================");
		System.out.println("     OASIS INFOBYTE ATM");
		System.out.println("==================================");

		int attempts = 3;
		boolean loggedIn = false;

		while (attempts > 0) {

			System.out.print("Enter User ID: ");
			String userId = scanner.nextLine();

			System.out.print("Enter PIN: ");
			String pin = scanner.nextLine();

			if (atm.login(userId, pin)) {
				loggedIn = true;
				break;
			} else {
				attempts--;
				System.out.println("Invalid User ID or PIN.");

				if (attempts > 0) {
					System.out.println("Attempts Remaining: " + attempts);
				}
			}
		}

		if (!loggedIn) {
			System.out.println("Too many failed attempts.");
			System.out.println("Account Locked.");
			scanner.close();
			return;
		}

		int choice;

		do {

			System.out.println("\n========== ATM MENU ==========");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. Transaction History");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();

			switch (choice) {

			case 1:
				System.out.println("Current Balance : ₹" + atm.checkBalance());
				break;

			case 2:
				System.out.print("Enter amount to deposit: ");
				double deposit = scanner.nextDouble();
				atm.deposit(deposit);
				break;

			case 3:
				System.out.print("Enter amount to withdraw: ");
				double withdraw = scanner.nextDouble();
				atm.withdraw(withdraw);
				break;

			case 4:
				scanner.nextLine();

				System.out.print("Enter Receiver User ID: ");
				String receiverId = scanner.nextLine();

				System.out.print("Enter Amount to Transfer: ");
				double transferAmount = scanner.nextDouble();

				atm.transfer(receiverId, transferAmount);
				break;

			case 5:
				atm.showTransactions();
				break;

			case 6:
				System.out.println("\n==================================");
				System.out.println(" Thank you for using");
				System.out.println(" Oasis Infobyte ATM");
				System.out.println(" Have a nice day!");
				System.out.println(" Goodbye!");
	
				System.out.println("==================================");
				break;
			default:
				System.out.println("Invalid Choice.");
			}

		} while (choice != 6);

		scanner.close();
	}
}