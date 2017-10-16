package com.stackroute.hackathon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.stackroute.hackathon.model.UserModel;

public class E2ETest {
	String url = "http://localhost:8080/v1.0/hackathon/user";
	String urlwithId = "http://localhost:8080/v1.0/hackathon/user/1";
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	String userJson;
	
	
	@Before
    public void setUp() throws Exception {
         userJson = "{\"id\" : \"1\", \"username\" : \"Devendra\", \"emailid\" : \"devendra.baruah@cgi.com\"}";
    }
	
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
    public void testSaveData() throws Exception {
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(userJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        
        assertEquals("User Added Successfully",actual);
    }
	
	@Test
	public void testGetData() throws Exception {
		ResponseEntity<UserModel> GetResponse = restTemplate.exchange(
                urlwithId,
                HttpMethod.GET, null, UserModel.class);

		String actualId = GetResponse.getBody().getId();
        String actualName = GetResponse.getBody().getUsername();
        String actualEmailId = GetResponse.getBody().getEmailid();
        
        assertEquals("1", actualId);
        assertEquals("Devendra Kumar", actualName);
        assertEquals("devendra.baruah@cgi.com", actualEmailId);
    }
	
	@Test
	public void testGetAllData() throws Exception {
		ResponseEntity<ArrayList<UserModel>> GetResponseList = restTemplate.exchange(
                url,
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<UserModel>>() {});
		
		String actualId = GetResponseList.getBody().get(0).getId();
        String actualName = GetResponseList.getBody().get(0).getUsername();
        String actualEmailId = GetResponseList.getBody().get(0).getEmailid();
        
        assertEquals("1", actualId);
        assertEquals("Devendra Kumar", actualName);
        assertEquals("devendra.baruah@cgi.com", actualEmailId);
    }
	
	@Test
	public void testPutData() throws Exception {
		headers.setContentType(MediaType.APPLICATION_JSON);
		userJson = "{\"id\" : \"1\", \"username\" : \"Devendra Kumar\", \"emailid\" : \"devendra.baruah@cgi.com\"}";
        HttpEntity<String> entity = new HttpEntity<String>(userJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(
	            url,
	            HttpMethod.PUT, entity, String.class);
	    assertNotNull(response);
	    String actual = response.getBody();
	    assertEquals(" User Updated Successfully", actual);
	}
	
	@Test
	public void testDeleteData() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(
	            urlwithId,
	            HttpMethod.DELETE, null, String.class);
	    assertNotNull(response);
	    String actual = response.getBody();
	    assertEquals("User with Id 1 Deleted Succesfully ", actual);
	}
}
