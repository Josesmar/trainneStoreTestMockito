package com.trainee.store.domain.order;

import com.trainee.store.domain.payment.PaymentService;
import com.trainee.store.domain.user.UserSerivce;

public class OrderService {
	
	private UserSerivce userSerivce;
	private PaymentService paymentService;
	
	public OrderService(UserSerivce userSerivce, PaymentService paymentService) {

		this.userSerivce = userSerivce;
		this.paymentService= paymentService;
	}

	public void create(Order order) {
		boolean IsUserMinor = userSerivce.isUserMinor(order.getUserId());
		
		if (IsUserMinor) {
			throw new IllegalStateException("O usuário não pode ser menor de idade");
		}
		
		paymentService.pay();
	}
}
