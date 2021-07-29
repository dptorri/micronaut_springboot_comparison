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
}
