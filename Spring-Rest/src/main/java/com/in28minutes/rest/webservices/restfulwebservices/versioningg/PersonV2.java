package com.in28minutes.rest.webservices.restfulwebservices.versioningg;


public class PersonV2 {
private Name name;

    public PersonV2(Name personV1) {
        this.name=personV1;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                '}';
    }
}
