package com.ssafy.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(

)
@Slf4j
class FinaltripApplicationTests {

	private String userid;

 	private String userpwd;
	
	@Test
	void contextLoads() {
		log.debug("uuuuuuuuu {} {}",userid,userpwd );
	}

}
