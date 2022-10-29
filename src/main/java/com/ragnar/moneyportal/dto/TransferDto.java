package com.ragnar.moneyportal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class TransferDto {


	String senderId;
	String reciverId;
	Float amount;
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReciverId() {
		return reciverId;
	}
	public void setReciverId(String reciverId) {
		this.reciverId = reciverId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
	
}
