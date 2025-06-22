package com.in28minutes.rest.webservices.restfulwebservices.versioningg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioning {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
            return new PersonV1("Bob Charlie");
    }
    @GetMapping("/v2/person")
    public PersonV2 getFirstVersionOfPersonV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping( path = "person",params = "version1")
    public PersonV1 getFirstVersionOfPersonParamsv1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping( path = "person",params = "version2")
    public PersonV2 getFirstVersionOfPersonParamsv2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping( path = "person/header",headers = "X-API-VERSION=2")
    public PersonV2 getFirstVersionOfPersonParamsHeaderv2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping( path = "person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonParamsHeaderv1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping( path = "person/accept",produces = "application/vnd.company.app-v1+json\n")
    public PersonV1 getFirstVersionOfPersonParamsAcceptHeaderv1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping( path = "person/accept",produces = "application/vnd.company.app-v2+json\n")
    public PersonV2 getFirstVersionOfPersonParamsAcceptHeaderv2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
}
