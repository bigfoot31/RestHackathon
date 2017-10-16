package com.stackroute.hackathon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.stackroute.hackathon.RestHackathonApplication;
import com.stackroute.hackathon.controller.RestHackathonController;
import com.stackroute.hackathon.exception.UserNotFoundException;
import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.repository.RestHackathonCRUDRepository;
import com.stackroute.hackathon.service.RestHackathonRepoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestHackathonApplication.class)
public class RestHackathonControllerTest {
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
		new UserModel("1", "Devendra", "devendra.bruah@cgi.com");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMockCreation() {
		assertNotNull(controller);
		assertNotNull(_Service);
		assertNotNull(repoObject);
	}

	@Test
	public void testReadByIdMethodOfService() throws UserNotFoundException {
		UserModel b = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");

		when(_Service.readById("1")).thenReturn(b);
		assertThat(b.getUsername(), is(equalTo("Devendra")));
	}

	@Test
	public void testDeleteByIdMethodOfService() throws UserNotFoundException {
		UserModel b = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
		UserModel c=null;
		when(_Service.readById("1")).thenReturn(b);
		assertThat(b.getUsername(), is(equalTo("Devendra")));
		_Service.deleteById("1");
		when(_Service.readById("1")).thenReturn(c);
		assertThat(c, is(equalTo(null)));
	}
	
	@Test
	public void testUpdateMethodOfService() throws UserNotFoundException {
		UserModel b = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
		UserModel c=new UserModel("1", "Dharmendra", "devendra.baruah@cgi.com");
		when(_Service.readById("1")).thenReturn(b);
		assertThat(b.getUsername(), is(equalTo("Devendra")));
		_Service.update(c);
		when(_Service.readById("1")).thenReturn(c);
		assertThat(c.getUsername(), is(equalTo("Dharmendra")));
	}
	
	@Test
	public void testAddUserMethodOfService() throws Exception {
		new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
		UserModel c=new UserModel("2", "Dharmendra", "devendra.baruah@cgi.com");
		_Service.addUser(c);
		when(_Service.readById("2")).thenReturn(c);
		assertThat(c.getUsername(), is(equalTo("Dharmendra")));
	}
	
	@Test
	public void testReadMethodOfService() throws Exception {
		UserModel b = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
		UserModel c=new UserModel("2", "Dharmendra", "devendra.baruah@cgi.com");
		ArrayList<UserModel> userList = new ArrayList<UserModel>();
		userList.add(b);
		userList.add(c);
		
		_Service.addUser(b);
		_Service.addUser(c);
		when(_Service.read()).thenReturn(userList);
		assertThat(userList.get(0).getUsername(), is(equalTo("Devendra")));
	}
	
}
