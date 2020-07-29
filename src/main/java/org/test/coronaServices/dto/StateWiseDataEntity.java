package org.test.coronaServices.dto;

import lombok.Data;

import java.util.Set;

/**
 *  StateWiseDataEntity : Entity class for stateWiseData Service
 *  Used lombok for GETTERS and SETTERS
 */
@Data
public class StateWiseDataEntity {

    String statusMsg;

    Set<StateWiseDataResponse> data;
}
