package springboot.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("John Cena");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){ return new PersonV2(new Name("John","Cena")); }

    @GetMapping(value="/person/param", params="version=1")
    public PersonV1 personParamV1(){
        return new PersonV1("Bob Param");
    }

    @GetMapping(value="/person/param", params="version=2")
    public PersonV2 personParamV2(){ return new PersonV2(new Name("Bob","Param")); }

    @GetMapping(value="/person/header", headers="X-API-VERSION=1")
    public PersonV1 personHeaderV1(){
        return new PersonV1("Bob Header");
    }

    @GetMapping(value="/person/header", headers="X-API-VERSION=2")
    public PersonV2 personHeaderV2(){ return new PersonV2(new Name("Bob","Header")); }

    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
    public PersonV1 personProducesV1(){
        return new PersonV1("Bob Produces");
    }

    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
    public PersonV2 personProducesV2(){ return new PersonV2(new Name("Bob","Produces")); }
}
