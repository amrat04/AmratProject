package org.test.coronaServices.dto;

import lombok.Data;

import java.util.Set;

/**
 *  TotalCountEntity : Entity class for totalCount Service
 *  Used lombok for GETTERS and SETTERS
 */
@Data
public class TotalCountEntity {

    String statusMsg;

    Set<TotalCountResponse> data;

}
