package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVaribles = new HashMap<>();

		uriVaribles.put("id", "" + workerId);

		Worker wk = workerFeignClient.findById(workerId).getBody();
		return new Payment(wk.getName(), wk.getDailyIncome(), days);
	}

}
