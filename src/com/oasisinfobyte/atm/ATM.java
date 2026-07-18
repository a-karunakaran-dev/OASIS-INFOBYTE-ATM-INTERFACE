package com.oasisinfobyte.atm;

public class ATM {

	private Bank bank;

	public ATM(Bank bank) {
		this.bank = bank;
	}

	public boolean login(String userId, String pin) {
		return bank.login(userId, pin);
	}

	public double checkBalance() {
		return bank.getAccount().getBalance();
	}

	public void deposit(double amount) {
		bank.getAccount().deposit(amount);
		bank.addTransaction("Deposit", amount);
		System.out.println("₹" + amount + " deposited successfully.");
	}

	public void withdraw(double amount) {
		if (bank.getAccount().withdraw(amount)) {
			bank.addTransaction("Withdraw", amount);

			System.out.println("Please collect your cash.");
		} else {
			System.out.println("Insufficient Balance!");
		}
	}

	public void transfer(String receiverId, double amount) {

		if (bank.transfer(receiverId, amount)) {
			System.out.println("Transfer Successful.");
		} else {
			System.out.println("Transfer Failed.");
			System.out.println("Invalid Receiver ID or Insufficient Balance.");
		}
	}

	public void showTransactions() {
		if (bank.getTransactions().isEmpty()) {
			System.out.println("No transactions found.");
		} else {
			System.out.println("\nTransaction History");
			System.out.println("-------------------------");

			for (Transaction transaction : bank.getTransactions()) {
				System.out.println(transaction);
			}

			System.out.println();
		}
	}
}
