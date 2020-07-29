package org.test.coronaServices.controller;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;

import org.test.coronaServices.Controller.CovidController;
import org.test.coronaServices.service.CovidService;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.StateWiseDataResponse;
import org.test.coronaServices.dto.TotalCountEntity;
import org.test.coronaServices.dto.TotalCountResponse;

import java.util.HashSet;
import java.util.Set;

/**
 *  CovidControllerTest - Testcases for controller layer only. Integration with 3rd party API
 *      has been added at service layer
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = {CovidController.class})
@WebMvcTest
public class CovidControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CovidService covidService;

    @WithMockUser(value = "spring")
    @Test
    public void givenCovidTotalCountService_shouldSucceedWith200() throws Exception {
        TotalCountEntity totalCountEntity = createTotalCountEntity();

        Mockito.when(covidService.getTotalCount()).thenReturn(totalCountEntity);
        mvc.perform(get("/covid/getcount")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(totalCountEntity.getStatusMsg())));

    }

    @WithMockUser(value = "spring")
    @Test
    public void givenCovidStateWiseService_shouldSucceedWith200() throws Exception {
        StateWiseDataEntity stateWiseDataEntity = getStateWiseData();

        Mockito.when(covidService.getStateWiseData()).thenReturn(stateWiseDataEntity);
        mvc.perform(get("/covid/getstatewisedata")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(stateWiseDataEntity.getStatusMsg())));

    }

    private TotalCountEntity createTotalCountEntity() {
        TotalCountEntity totalCountEntity = new TotalCountEntity();
        totalCountEntity.setStatusMsg("OK");

        TotalCountResponse totalCountResponse = new TotalCountResponse();
        totalCountResponse.setActive("485823");
        totalCountResponse.setConfirmed("1437976");
        totalCountResponse.setDeaths("32836");
        totalCountResponse.setMigratedother("14");
        totalCountResponse.setNewconfirmed("1957");
        totalCountResponse.setNewdeaths("171");
        totalCountResponse.setNewrecovered("421");
        totalCountResponse.setRecovered("885823");
        Set<TotalCountResponse> totalCountResponses = new HashSet<>();
        totalCountResponses.add(totalCountResponse);
        totalCountEntity.setData(totalCountResponses);

        return totalCountEntity;
    }

    private StateWiseDataEntity getStateWiseData(){

        StateWiseDataEntity sw = new StateWiseDataEntity();
        sw.setStatusMsg("OK");

        Set<StateWiseDataResponse> statelist = new HashSet<>();
        StateWiseDataResponse response = new StateWiseDataResponse();
        response.setCode("OR");
        response.setName("ORISSA");
        response.setDeaths("1240");
        response.setActive("12000");
        response.setConfirmed("30000");
        response.setNewconfirmed("2334");
        response.setNewdeaths("400");
        response.setNewrecovered("15000");
        response.setRecovered("24000");

        statelist.add(response);
        StateWiseDataResponse response1 = new StateWiseDataResponse();
        response1.setCode("MH");
        response1.setName("MAHARASHTRA");
        response1.setDeaths("1340");
        response1.setActive("14000");
        response1.setConfirmed("28000");
        response1.setNewconfirmed("1234");
        response1.setNewdeaths("300");
        response1.setNewrecovered("13000");
        response1.setRecovered("23000");

        statelist.add(response1);
        sw.setData(statelist);

        return  sw;
    }
}
