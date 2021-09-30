package com.trainee.store.domain.order;


public class Order {
	
	private Long userId;

	public Order(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
