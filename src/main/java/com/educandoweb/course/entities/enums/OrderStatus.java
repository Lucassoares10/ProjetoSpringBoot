package com.educandoweb.course.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),   //atribuição de numeros para nao quebrar o banco de dados futuramente
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;  
	
	//metodos para atribuição de numeros no OrderStatus
	private OrderStatus (int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	 //metodo static pq nao vai precisar instanciar e que vai receber um codigo int e retornar o orderStatus correspondente
	public static OrderStatus valueOf(int code) { 
		for (OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code"); //exception do java 
	}
}
