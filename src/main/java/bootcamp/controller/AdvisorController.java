package bootcamp.controller;

import bootcamp.service.CompanyService;
import bootcamp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvisorController {

//    Logger logger
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/company", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})

    public Double[] getStoreValue(){
       return companyService.getCompanyValue();
    }
}
