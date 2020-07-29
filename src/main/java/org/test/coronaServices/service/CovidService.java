package org.test.coronaServices.service;

import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.TotalCountEntity;

/**
 *  CovidService : Service layer interface
 */
public interface CovidService {

    TotalCountEntity getTotalCount();

    StateWiseDataEntity getStateWiseData();

    // Future API's
    //getIndiaStateCodesAndNames();
}
