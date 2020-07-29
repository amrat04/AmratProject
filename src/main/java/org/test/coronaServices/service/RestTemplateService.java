package org.test.coronaServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.TotalCountEntity;
import org.test.coronaServices.exception.RestTemplateExceptionHandler;
import org.test.coronaServices.util.CovidServiceConstants;

/**
 *  Calling Covid Backend Services.
 */
@Service
public class RestTemplateService {

    @Value("${covid.baseurl}")
    private String baseUrl;

    @Value("${covid.header.x-rapidapi-host}")
    private String X_RAPIDAPI_HOST_VALUE;

    @Value("${covid.header.x-rapidapi-key}")
    private String X_RAPIDAPI_KEY_VALUE;

    @Autowired
    RestTemplate restTemplate;

    public TotalCountEntity getTotalCount(){

        HttpHeaders httpHeaders = setHeaders();

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TotalCountEntity> response = null;
        try {
            restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
            response = restTemplate.exchange(baseUrl + CovidServiceConstants.GET_TOTAL_COUNT_API,
                    HttpMethod.GET, entity, TotalCountEntity.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return response.getBody();
    }

    public StateWiseDataEntity getStateWiseData(){

        HttpHeaders httpHeaders = setHeaders();

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<StateWiseDataEntity> response = null;
        try {
            restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
            response = restTemplate.exchange(baseUrl + CovidServiceConstants.GET_INDIA_STATE_WISE_DATA_API,
                    HttpMethod.GET, entity, StateWiseDataEntity.class);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return response.getBody();
    }

    private HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(CovidServiceConstants.X_RAPIDAPI_HOST, X_RAPIDAPI_HOST_VALUE);
        headers.add(CovidServiceConstants.X_RAPIDAPI_KEY,X_RAPIDAPI_KEY_VALUE);
        headers.add(CovidServiceConstants.USE_QUERY_STRING, String.valueOf(true));
        return  headers;
    }


}
