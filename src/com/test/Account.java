package com.test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.test.modal.Transaction;
import com.test.utils.DateUtil;
import com.test.utils.TransactionType; 

public class Account {
	
	public static void main(String[] args) throws Exception {
		// Input Transactions data file location
		String fileName = "C:\\Users\\lenovo\\Desktop\\TestData.csv";

		List<Transaction> transcations = null;
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			transcations = lines.skip(1).map(Transaction::new).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error while reading input transcation file" + e);
		}
		
		Scanner scanner = new Scanner(System.in);
		 //  prompt for the account id
	    System.out.print("accountId: ");
	    String transcationid = scanner.nextLine();

	    //  prompt for the from date
	    System.out.print("from: ");
	    String from = scanner.nextLine();
	    
	    // prompt for to
	    System.out.print("to: ");
	    String to = scanner.nextLine();
	    scanner.close();
		
		LocalDateTime fromDate = DateUtil.getLocalDateTimeFromString(from);
		LocalDateTime toDate =  DateUtil.getLocalDateTimeFromString(to);

		// Get Reversal Transaction Ids
		List<String> reversalTranscations = transcations.stream()
				.filter(t -> t.getTranscationType() == TransactionType.REVERSAL).map(Transaction::getRelatedTranscation)
				.collect(Collectors.toList());

		// Filter Input Transaction, remove reversal transaction and filter dates based on input from date and to date
		List<Transaction> updateTranscations1 = transcations.stream()
				.filter(t -> t.getTranscationType() == TransactionType.PAYMENT
						&& t.getFromAccountId().equals(transcationid)
						&& !reversalTranscations.contains(t.getTranscationId())
						&& t.getCreatedAt().compareTo(fromDate) >= 0 && t.getCreatedAt().compareTo(toDate) <= 0)
				.collect(Collectors.toList());
		
		// Iterate and add/subtract amount based on from or to 
		Double finalAmount = 0.0;
		for (Transaction t : updateTranscations1) {
			if (t.getFromAccountId().equals(transcationid)) {
				finalAmount -= t.getAmount();
			} else if(t.getToAccountId().equals(transcationid)) {
				finalAmount += t.getAmount();
			}
		}
		
		// Print output
		System.out.println("Relative balance for the period is: " + finalAmount);
		System.out.println("Number of transactions included is: " + updateTranscations1.size());
		
	}
		
}
