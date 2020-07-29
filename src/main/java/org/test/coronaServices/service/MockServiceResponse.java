package org.test.coronaServices.service;

import org.springframework.stereotype.Service;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.StateWiseDataResponse;

import java.util.HashSet;
import java.util.Set;

/**
 *  MockServiceResponse : Created for sending mock response back for testing.
 */
@Service
public class MockServiceResponse {

    StateWiseDataEntity getStateWiseData(){

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
