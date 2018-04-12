package bootcamp.controller;

import bootcamp.service.CompanyService;
import bootcamp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@RestController
public class AdvisorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CompanyService companyService;

    @Autowired
   private DateFormat dateFormat;

    @RequestMapping(value = "/company", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public Double[] getStoreValue() {

        log.info("BBB stopped by at {}", dateFormat.format(new Date()));
        return companyService.getCompanyValue();
    }
}
