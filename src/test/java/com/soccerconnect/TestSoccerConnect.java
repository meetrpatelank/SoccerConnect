package com.soccerconnect;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TestSoccerConnect {

	SoccerConnect sc;

	@BeforeEach
	void setup() {
	sc = new SoccerConnect();
	}

	@Test
	public void notNullTest() {
		assertNotNull(sc);
	}
}
