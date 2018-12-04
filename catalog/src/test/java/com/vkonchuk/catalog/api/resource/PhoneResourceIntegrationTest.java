package com.vkonchuk.catalog.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.vkonchuk.catalog.CatalogApplication;
import com.vkonchuk.catalog.model.Phone;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CatalogApplication.class)
public class PhoneResourceIntegrationTest {

    private static final String PHONES_PATH = "/phones";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void returnAllPhonesIntegrationTest() {
        ResponseEntity<List<Phone>> responseEntity = restTemplate.exchange(PHONES_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<List<Phone>>() {});
        assertThat(responseEntity.getStatusCode()).as("Status code must be 200 OK when all phones are returned").isEqualTo(HttpStatus.OK);
        List<Phone> responseBody = responseEntity.getBody();
        assertNotNull("Response should have a body", responseBody);
        assertTrue("List of phones should not be empty", !responseBody.isEmpty());
    }

}
