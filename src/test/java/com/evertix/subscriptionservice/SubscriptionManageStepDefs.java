package com.evertix.subscriptionservice;

import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.util.RestPageImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;

@CucumberContextConfiguration
@SpringBootTest(classes = SubscriptionServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class SubscriptionManageStepDefs {

    private URL base;
    private String username;
    @Autowired
    protected TestRestTemplate template;

    private ResponseEntity<RestPageImpl<Subscription>> listResponseEntity;

    @Before
    public void setUp() throws MalformedURLException {
        this.base=new URL( "http://tutofast-subscription-service.eastus.azurecontainer.io:8087/api/subs/page");
    }

    @Given("Student with a username {string}")
    public void studentWithAUsername(String arg0) {
        this.username=arg0;
    }

    @When("go to subscription option and call to api is made")
    public void goToSubscriptionOptionAndCallToApiIsMade() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ParameterizedTypeReference<RestPageImpl<Subscription>> responseType = new ParameterizedTypeReference<RestPageImpl<Subscription>>() { };
        this.listResponseEntity = template.exchange(base.toString(), HttpMethod.GET,request, responseType);
    }

    @Then("response status is {int}")
    public void responseStatusIs(int arg0) {
        Assert.assertEquals(listResponseEntity.getStatusCodeValue(),arg0,listResponseEntity.getStatusCodeValue());
    }

    @Given("Teacher with a username {string}")
    public void teacherWithAUsername(String arg0) {
        this.username=arg0;
    }

    @Given("Admin with username {string}")
    public void adminWithUsername(String arg0) {
        this.username=arg0;
    }

    @And("all subscription data is retrieved")
    public void allSubscriptionDataIsRetrieved() {
        Assert.assertEquals("*************Size is "+listResponseEntity.getBody().getTotalElements(),2,listResponseEntity.getBody().getTotalElements());
    }
}
