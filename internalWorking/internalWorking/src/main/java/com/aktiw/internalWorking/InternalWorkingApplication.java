package com.aktiw.internalWorking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InternalWorkingApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(InternalWorkingApplication.class, args);
	}

	//@Autowired             //we can use this also (final keyword will not implement)
	private final PaymentServices paymentservices; //= new RazorpayPaymentService();     //tight coupling

	public InternalWorkingApplication(PaymentServices paymentservices) {
		this.paymentservices = paymentservices;               //(main function is dependent on this constructure)
	}

	@Override
	public void run(String... args) throws Exception {
		String payment = paymentservices.pay();
		System.out.println("payment Done: " + payment);

	}
}
