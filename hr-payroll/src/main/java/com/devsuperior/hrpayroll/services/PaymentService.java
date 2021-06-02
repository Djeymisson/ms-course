package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVaribles = new HashMap<>();

		uriVaribles.put("id", "" + workerId);

		Worker wk = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVaribles);
		return new Payment(wk.getName(), wk.getDailyIncome(), days);
	}

}
