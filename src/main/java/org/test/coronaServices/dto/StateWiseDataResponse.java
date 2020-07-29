package org.test.coronaServices.dto;

import lombok.Data;

/**
 *  StateWiseDataResponse : Entity class used in StateWiseDataEntity as a SET
 *  Used lombok for GETTERS and SETTERS
 */
@Data
public class StateWiseDataResponse {

    private String code;
    private String name;
    private String active;
    private String deaths;
    private String confirmed;
    private String newdeaths;
    private String recovered;
    private String newconfirmed;
    private String newrecovered;
    private String migratedother;
    private String lastupdatedtime;
}
