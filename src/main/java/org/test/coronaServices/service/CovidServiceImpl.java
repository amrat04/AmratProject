package org.test.coronaServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.TotalCountEntity;

/**
 *  Covid Services layer implementation..
 */
@Service
public class CovidServiceImpl implements  CovidService {

    @Autowired
    RestTemplateService restTemplateService;

    @Autowired
    MockServiceResponse mockServiceResponse;    // Used for testing purpose as a mock response.

    @Override
    public TotalCountEntity getTotalCount() {

        TotalCountEntity totalCountEntity = restTemplateService.getTotalCount();
        if(totalCountEntity == null){
            totalCountEntity.setStatusMsg("error");
        }
        return totalCountEntity;
    }

    @Override
    public StateWiseDataEntity getStateWiseData() {

        StateWiseDataEntity stateWiseDataEntity =  restTemplateService.getStateWiseData();

        if(stateWiseDataEntity == null){
            stateWiseDataEntity.setStatusMsg("error");
        }
        return stateWiseDataEntity;
    }


}
