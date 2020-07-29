
package org.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  RegistrationControllerTest : Test-cases for Registration URL
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class RegistrationControllerTest {


    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    public void givenRegistrationRequest_shouldSucceedWith200() throws Exception {

        String  email = randomAlphaNumeric(8)+"@test.com";
        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName","test")
                .param("lastName","testlast")
                .param("password","password")
                .param("confirmPassword","password")
                .param("email",email)
                .param("confirmEmail",email))
                .andExpect(status().is3xxRedirection());    // Expecting 302 as controller is redirecting the response.
    }

    @Test
    public void givenRegistrationRequestWithExistingEmail_shouldSucceedWith200WithError() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName","test")
                .param("lastName","testlast")
                .param("password","password")
                .param("confirmPassword","password")
                .param("email","test12@gmail.com")
                .param("confirmEmail","test12@gmail.com"))
                .andExpect( model().hasErrors())
                    .andExpect(status().isOk());
    }

    @Test
    public void givenRegistrationRequestWithDifferentEmail_shouldReturnExceptionWith200() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName","test")
                .param("lastName","testlast")
                .param("password","password")
                .param("confirmPassword","password")
                .param("email","test12@gmail.com")
                .param("confirmEmail","test1234@gmail.com"))
                .andExpect( model().hasErrors())
                        .andExpect(status().isOk());
    }

    private String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

