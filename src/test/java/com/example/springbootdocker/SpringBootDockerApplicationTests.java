package com.example.springbootdocker;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootDockerApplicationTests {

	@Autowired
    private MockMvc mvc;

	@Test
	void contextLoads() throws Exception {
		mvc.perform(get("/")
			      .contentType(MediaType.TEXT_PLAIN))
			      .andExpect(status().isOk())
			      .andExpect(content().string(containsString("Hello Docker World")));
	}

}
