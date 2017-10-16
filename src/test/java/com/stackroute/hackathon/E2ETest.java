package com.stackroute.hackathon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import com.stackroute.hackathon.model.UserModel;

public class E2ETest {
	String url = "http://localhost:8080//hackathon/user";
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	UserModel user;
	JSONObject obj;

	@Before
    public void setUp() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
         user = new UserModel("1", "Devendra", "devendra.baruah@cgi.com");
         List<UserModel> userList = new ArrayList<UserModel>();
         userList.add(user);
         
         String userJson = "{'id' : '1', 'username' : 'Devendra', 'emailid' : 'devendra.baruah@cgi.com'}";
         
         obj = new JSONObject();
         obj.put("id", "1");
         obj.put("username", "Devendra");
         obj.put("emailid", "devendra.baruah@cgi.com");
    }
	
//	@Test
//    public void testSaveData() throws Exception {
//		
//        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(user, headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST, entity, String.class);
//        assertNotNull(response);
//        String actual = response.getBody();
//        
//        assertEquals("Data saved successfully",actual);
//    }

}
