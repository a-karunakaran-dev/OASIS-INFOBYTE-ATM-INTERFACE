package com.oasisinfobyte.atm;

import java.util.ArrayList;

public class Bank {

	private Account account1;
	private Account account2;
	private Account currentAccount;
	private ArrayList<Transaction> transactions;

	public Bank() {
		account1 = new Account("1001", "9363", 10000);
		account2 = new Account("1002", "2409", 5000);
		transactions = new ArrayList<>();
	}

	public boolean login(String userId, String pin) {

		if (account1.getUserId().equals(userId) && account1.getPin().equals(pin)) {
			currentAccount = account1;
			return true;
		}

		if (account2.getUserId().equals(userId) && account2.getPin().equals(pin)) {
			currentAccount = account2;
			return true;
		}

		return false;
	}

	public Account getAccount() {
		return currentAccount;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(String type, double amount) {
		transactions.add(new Transaction(type, amount));
	}

	public boolean transfer(String receiverId, double amount) {

		Account receiver = null;

		if (account1.getUserId().equals(receiverId)) {
			receiver = account1;
		} else if (account2.getUserId().equals(receiverId)) {
			receiver = account2;
		}

		if (receiver == null || receiver == currentAccount) {
			return false;
		}

		if (currentAccount.withdraw(amount)) {
			receiver.deposit(amount);
			addTransaction("Transfer to " + receiverId, amount);
			return true;
		}

		return false;
	}
}