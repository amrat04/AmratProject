package org.test.coronaServices.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.TotalCountEntity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 *  CovidControllerTest - Testcases for controller layer only. Integration with 3rd party API
 *      has been added at service layer
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CovidServiceTest {

    @Autowired
    CovidService covidService;

    @Autowired
    private RestTemplateService restTemplateService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @WithMockUser(value = "spring")
    @Test
    public void givenCovidTotalCountService_shouldSucceedWith200() throws Exception {

        TotalCountEntity totalCountEntity = covidService.getTotalCount();

    }

    @WithMockUser(value = "spring")
    @Test
    public void givenCovidStateWiseService_shouldSucceedWith200() throws Exception {

        StateWiseDataEntity stateWiseDataEntity = covidService.getStateWiseData();

    }

}
