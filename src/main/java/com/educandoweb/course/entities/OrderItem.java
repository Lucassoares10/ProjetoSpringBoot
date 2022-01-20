package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.course.entities.pk.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order_item")
public class OrderItem  implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId   //pegar o id o OrdemItemPk que sera gerado e transforma-lo no id do OrdemItem na tabela do banco
	private OrderItemPk id = new OrderItemPk(); //sempre que for criar uma classe auxiliar que é um id composto, voce deve instanciar-lo
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		
	}
	//Order Item, item do pedido, o pedido tal foi comprado em tal momento e esta em tal estado.
	//OrderItemPk ira gerar uma primary key para tal pedido, para recupera-lo futuramente de acordo com produto, preço, estado e etc.

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order); //passando o order que chegou como argumento
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double GetSubTotal() { //para buscar o subtotal dos pedidos
		return price * quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
