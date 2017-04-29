package com.helloworld;

import com.helloworld.representation.HelloWorldGreetingJaxb;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HelloWorldApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloworldApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloworldApplicationTests.class);

	private static final String SPRING_BOOT_MATCH = "Spring Boot";
	private static final String FROM = "Val";

	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter =
				Arrays.asList(converters).stream()
						.filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
						.findAny().get();
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test1() throws Exception {

		ResultActions result = this.mockMvc.perform(get("/helloworld/all")).andExpect(status().isOk());

		String json = result.andReturn().getResponse().getContentAsString();
		LOGGER.info("TESTING /helloworld/all: {}",json);
	}

	@Test
	public void test2() throws Exception {

		List<HelloWorldGreetingJaxb> greetings = new ArrayList<HelloWorldGreetingJaxb>();
		greetings.add(new HelloWorldGreetingJaxb("Hello to Spring Boot","Spring Boot", "Val"));
		greetings.add(new HelloWorldGreetingJaxb("Hello to Spring 4","Spring 4", "Val"));
		greetings.add(new HelloWorldGreetingJaxb("Hello to Everyone","Everyone", "Val"));
		greetings.add(new HelloWorldGreetingJaxb("Hello World","World", "Val"));

		String json = toJsonString(greetings);
		LOGGER.info("TESTING toJsonString: {}",json);
	}

	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/helloworld/all"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$",iterableWithSize(4)))
				.andExpect(jsonPath("$[0]['greeting']",containsString(SPRING_BOOT_MATCH)));
	}

	@Test
	public void findByFrom() throws Exception {
		mockMvc.perform(get("/helloworld/findBy/from/" + FROM))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$",iterableWithSize(4)))
				.andExpect(jsonPath("$[0]['from']",containsString(FROM)));
	}

	protected String toJsonString(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
