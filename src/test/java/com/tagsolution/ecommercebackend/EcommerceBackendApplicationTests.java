package com.tagsolution.ecommercebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EcommerceBackendApplicationTests {

	@Test
	void contextLoads() {
		var ecommerceBackendApplication = new EcommerceBackendApplication();
		assertNotNull(ecommerceBackendApplication.getClass());
	}
}
