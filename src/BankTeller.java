/**
* CS 111 Section No. 002
* Lab Assignment 8
* @author Samuel Harris
**/

import java.util.ArrayList;
import java.util.Scanner;

public class BankTeller {
	public static void main(String args[]) {
		ArrayList<ArrayList<Integer>> db = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		boolean isRunning = true;
		int response = 0;

		while (isRunning) {
			System.out.println("____________MENU____________");
			System.out.println("1) Add a new customer.");
			System.out.println("2) Record a payment by an existing customer.");
			System.out.println("3) Display the database.");
			System.out.println("4) Exit.\n");

			response = input.nextInt();

			switch (response) {
			case 1:
				System.out.println("Please enter the approved loan amount for the new customer.\n");
				ArrayList<Integer> tempPerson = new ArrayList<>();
				tempPerson.add(input.nextInt());
				db.add(tempPerson);
				System.out
						.println("The customer has been added with their loan amount. (ID: " + (db.size() - 1) + ")\n");
				break;
			case 2:
				int ID = 0, amountPaid = 0, totalAmountPaid = 0;
				System.out.println("Please enter the customer ID of this customer.\n");
				ID = input.nextInt();
				
				if (ID >= 0 && ID < db.size()) {
					System.out.println("Please enter the amount that the customer wants to pay off.\n");
					amountPaid = input.nextInt();
					int ogLoan = db.get(ID).get(0);
					System.out.println("The original loan was for $" + ogLoan + ".\n");
					db.get(ID).add(amountPaid);
					totalAmountPaid = amountPaid(db.get(ID));

					if (ogLoan <= totalAmountPaid) {
						System.out.println("You have fully paid back the loan!\n");
					} else if (ogLoan > totalAmountPaid) {
						System.out.println(
								"Payment processed! You have $" + (ogLoan - totalAmountPaid) + " left to pay.\n");
					}
				} else if (ID < 0 || ID >= db.size()) {
					System.out.println("No such customer found!\n");
				}
				break;
			case 3:
				for (int i = 0; i < db.size(); i++) {
					System.out.println("Customer: " + i + " Loan Amount: $" + db.get(i).get(0) + " Amount Paid: $"
								+ amountPaid(db.get(i)) + " Amount Left: $"
								+ amountLeft(db.get(i)));
					
				}
				break;
			case 4:
				isRunning = false;
				break;
			default:
				System.out.println("You entered an invalid character.");
				break;
			}

		}
	}

	public static int amountPaid(ArrayList<Integer> customer) {
		int totalAmountPaid = 0;
		for (int i = 1; i < customer.size(); i++) {
			totalAmountPaid += customer.get(i);
		}
		return totalAmountPaid;
	}

	public static int amountLeft(ArrayList<Integer> customer) {
		int totalAmountPaid = amountPaid(customer);
		int ogLoan = customer.get(0);
		int remainingLoan = 0;

		if (ogLoan <= totalAmountPaid) {
			remainingLoan = 0;
		} else {
			remainingLoan = ogLoan - totalAmountPaid;
		}
		return remainingLoan;
	}
}
