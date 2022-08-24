package com.example.birthdaydemo;

import com.example.birthdaydemo.models.BirthdayResponse;
import com.example.birthdaydemo.repos.BirthdayDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BirthdayDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BirthdayDemoApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private BirthdayDao birthdayDao;

//	@Test
//	void testSingleton() {
//		final String baseUrl = "http://localhost:"+randomServerPort+"/api/birthday";
//
//		try {
//			final String url1 = baseUrl + "?month=" + 12 + "&day=" + 25;
//			final String url2 = baseUrl + "?month=" + 3 + "&day=" + 25;
//
//			URI uri1 = new URI(url1);
//			URI uri2 = new URI(url2);
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("X-COM-PERSIST", "true");
//
//			HttpEntity<BirthdayResponse> request1 = new HttpEntity<>(headers);
//			HttpEntity<BirthdayResponse> request2 = new HttpEntity<>(headers);
//
//			Thread thread1 = new Thread(new GetRequestTask<>(uri1, request1));
//			Thread thread2 = new Thread(new GetRequestTask<>(uri2, request2));
//			thread1.start();
//			thread2.start();
//			thread1.join();
//			thread2.join();
//
//			System.out.println("number of calls : " + birthdayDao.numberOfCalls);
//
//			assertNotEquals(2, birthdayDao.numberOfCalls,
//					"number of calls: " + birthdayDao.numberOfCalls);
//
//		} catch (Exception e) {
//
//		}
//	}

	@Test
	void testSingletonSync() {
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/birthday-sync";

		try {
			final String url1 = baseUrl + "?month=" + 12 + "&day=" + 25;
			final String url2 = baseUrl + "?month=" + 3 + "&day=" + 25;

			URI uri1 = new URI(url1);
			URI uri2 = new URI(url2);

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");

			HttpEntity<BirthdayResponse> request1 = new HttpEntity<>(headers);
			HttpEntity<BirthdayResponse> request2 = new HttpEntity<>(headers);

			Thread thread1 = new Thread(new GetRequestTask<>(uri1, request1));
			Thread thread2 = new Thread(new GetRequestTask<>(uri2, request2));
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();

			System.out.println("number of calls : " + birthdayDao.numberOfCalls);

			assertEquals(2, birthdayDao.numberOfCalls,
					"number of calls: " + birthdayDao.numberOfCalls);

		} catch (Exception e) {

		}
	}
}
