package com.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private String transcationId;
	private String fromAccountId;
	private String toAccountId;
	private LocalDateTime createdAt;
	private Double amount;
	private TransactionType transcationType;
	private String relatedTranscation;

	public Transaction() {

	}

	public Transaction(String transactionLine) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String[] transaction = transactionLine.split(",");
		this.transcationId = transaction[0].trim();
		this.fromAccountId = transaction[1].trim();
		this.toAccountId = transaction[2].trim();
		this.createdAt = LocalDateTime.parse(transaction[3].trim(), formatter);
		this.amount = Double.valueOf(transaction[4]);
		this.transcationType = TransactionType.valueOf(transaction[5].trim());
		if (transaction.length > 6) {
			this.relatedTranscation = transaction[6].trim();
		}

	}

	public String getTranscationId() {
		return transcationId;
	}

	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(TransactionType transcationType) {
		this.transcationType = transcationType;
	}

	public String getRelatedTranscation() {
		return relatedTranscation;
	}

	public void setRelatedTranscation(String relatedTranscation) {
		this.relatedTranscation = relatedTranscation;
	}

}
