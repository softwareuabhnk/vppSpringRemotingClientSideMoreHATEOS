import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	public static void main(String[] args) throws IOException {
	
//		URL url = new URL("http://localhost:8080/mywebapp/customer/100029");
//		
//		InputStream is = url.openStream();
//		
//		InputStreamReader isr = new InputStreamReader(is);
//		BufferedReader br = new BufferedReader(isr);
//		
//		System.out.println(br.readLine());
		
		 RestTemplate template = new RestTemplate();
		 template.setErrorHandler(new CustomExceptionHandler(template));
		 
		 HttpHeaders headers = new HttpHeaders();
		 List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		 acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
		 acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		 headers.setAccept(acceptableMediaTypes);
		 
		 // Post a new customer
		 CustomerClientVersion customer = new CustomerClientVersion();
		 customer.setCompanyName("Ericcson");
		 customer.setNotes("Very important");
		 
		 ResponseEntity<CustomerClientVersion> customerEntity = template.postForEntity("http://localhost:8080/mywebapp/customers",
				 			customer, CustomerClientVersion.class);
		 
		 customer = customerEntity.getBody();
		 System.out.println(customerEntity.getStatusCode());
		 
		 System.out.println("The new customer has been given the id of " + customer.getCustomerId());
		 
		 // Update the customer
		 customer.setCompanyName("Volvo");
		 template.put("http://localhost:8080/mywebapp/customer/" + customer.getCustomerId(), customer);
		 
		 
		 // Get customers
		 HttpEntity<CustomerCollectionRepresentation> requestEntity = new HttpEntity<CustomerCollectionRepresentation>(headers);
		 HttpEntity<CustomerCollectionRepresentation> response = template.exchange("http://localhost:8080/mywebapp/customers",
				                              HttpMethod.GET, requestEntity, CustomerCollectionRepresentation.class);
		 
		 CustomerCollectionRepresentation result = response.getBody();
		 System.out.println(result);
		 }
}
