package org.test.coronaServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.coronaServices.service.CovidService;
import org.test.coronaServices.dto.StateWiseDataEntity;
import org.test.coronaServices.dto.TotalCountEntity;
import org.test.coronaServices.service.CovidServiceImpl;

@RestController
@RequestMapping("/covid")
public class CovidController {

    @Autowired
    CovidService covidService;

    @RequestMapping(value = "/getcount", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TotalCountEntity getTotalCount(Model model) {

        return covidService.getTotalCount();
    }

    @RequestMapping(value = "/getstatewisedata", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public StateWiseDataEntity getStateWiseData(Model model) {

        return  covidService.getStateWiseData();
    }
}
