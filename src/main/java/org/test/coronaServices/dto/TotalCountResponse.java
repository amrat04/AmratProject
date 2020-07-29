package org.test.coronaServices.dto;

import lombok.Data;



/**
 *  TotalCountResponse : Entity class used in TotalCountResponse as a SET
 *  Used lombok for GETTERS and SETTERS
 */
@Data
public class TotalCountResponse {

    private String active;

    private String deaths;

    private String confirmed;

    private String newdeaths;

    private String recovered;

    private String newconfirmed;

    private String newrecovered;

    private String migratedother;

   // private String lastupdatedtime;

}
