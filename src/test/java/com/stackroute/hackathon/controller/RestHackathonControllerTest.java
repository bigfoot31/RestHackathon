package com.stackroute.hackathon.controller;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.stackroute.hackathon.RestHackathonApplication;
import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.repository.RestHackathonCRUDRepository;
import com.stackroute.hackathon.service.RestHackathonRepoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestHackathonApplication.class)
public class RestHackathonControllerTest {
	private String baseUrl = "http://localhost:8080/hackathon/user";
	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Mock
	private RestHackathonController controller;
	
	@Mock
	private RestHackathonRepoImpl _Service;
	
	@Mock
	private RestHackathonCRUDRepository repoObject;
	
	@Before
    public void setUp() throws Exception {
         UserModel user = new UserModel("1", "Devendra", "devendra.bruah@cgi.com");
    }

	@After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testMockCreation(){
		assertNotNull(controller);
        assertNotNull(_Service);
        assertNotNull(repoObject);
    }
	
	
	@Test
	public void testGET() {
		UserModel b = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
		
		when(repoObject.findOne("1")).thenReturn(b);		
		assertThat(b.getId(), is(equalTo("1")));
	}
}
