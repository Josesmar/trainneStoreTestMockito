package com.trainee.store.domain.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.trainee.store.domain.payment.PaymentService;
import com.trainee.store.domain.user.UserSerivce;

public class OrderServiceTest {
	
	private PaymentService paymentService = mock(PaymentService.class);
	private UserSerivce userSerivce = mock(UserSerivce.class);		
	private OrderService orderService = new OrderService(userSerivce, paymentService);	
	
	@Test
	public void ShouldThrowsAnExceptionThenUserIsMinor() {		

		Order order = new Order(1L);
		
		when(userSerivce.isUserMinor(equals(1L))).thenReturn(true);	
		

		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> orderService.create(order));
		
		assertEquals("O usuário não pode ser menor de idade", exception.getMessage());		
		verify(userSerivce, times(1)).isUserMinor(equals(1L));		
		
	}	
	
	@Test
	public void ShouldPayThenUserMajor() {
		Order order = new Order(2L);
	
		when(userSerivce.isUserMinor(equals(2L))).thenReturn(false);
		doNothing().when(paymentService).pay();
		
		orderService.create(order);
		
		verify(userSerivce, times(1)).isUserMinor(equals(2L));
		verify(paymentService, times(1)).pay();
		
	}

}

