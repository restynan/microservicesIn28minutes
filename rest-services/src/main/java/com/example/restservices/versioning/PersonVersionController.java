package com.example.restservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

    //URL versioning
    // need to create a new url
    @GetMapping(value="v1/person")
    public PersonV1 retrievePerson1(){
        return new PersonV1("Resty Robinson");
    }

    @GetMapping(value="v2/person")
    public PersonV2 retrievePerson2(){
        return new PersonV2(new Name("Mark","James"));
    }




//using request params versioning

   @GetMapping(value="/person/param",params="version=1")
    public PersonV1 retrievePersonV1(){
        return new PersonV1("Resty Robinson");
    }

    @GetMapping(value="/person/param",params="version=2")
    public PersonV2 retrievePersonV2(){
        return new PersonV2(new Name("Mark","James"));
    }

    //coaching is hard,Most of end user customers will not know how to configure header and you can't do it on a normal broweser
    //documentation of the Api is hard

// Using headers   header versioning   /// parameter versioning
    @GetMapping(value="/person/header",headers="X-API-VERSION=1")
    public PersonV1 retrievePersonHeaderV1(){
        return new PersonV1("Resty Robinson");
    }

    @GetMapping(value="/person/header",headers="X-API-VERSION=2")
    public PersonV2 retrievePersonHeaderV2(){
        return new PersonV2(new Name("Mark","James"));
    }


 // Using produces -- sent as accept header    media type versioning/content negociation /accept header versioning
    @GetMapping(value="/person/produces",produces={"application/vnd.company.app-v1+json", "application/vnd.company.app-v1+xml"})
    public PersonV1 retrievePersonProducesV1(){
        return new PersonV1("Resty Robinson");
    }

    @GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
    public PersonV2 retrievePersonProducesV2(){
        return new PersonV2(new Name("Mark","James"));
    }
}
