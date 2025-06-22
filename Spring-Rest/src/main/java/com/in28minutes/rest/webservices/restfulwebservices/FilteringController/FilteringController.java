package com.in28minutes.rest.webservices.restfulwebservices.FilteringController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("filtering/static")
    public SomeBean staticFitering(){
        return new SomeBean("Value1","Value2","Value3");
    }
    @GetMapping("filtering/dynamic")
    public MappingJacksonValue dynamicFitering(){

        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
        extracted(mappingJacksonValue);
        return mappingJacksonValue;
    }

    @GetMapping("filteringlist/dynamic")
    public MappingJacksonValue staticFiteringList(){
        List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("Value4", "Value5", "Value6"),
                new SomeBean("Value7", "Value8", "Value9"));
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(list);

        extracted(mappingJacksonValue);
        return mappingJacksonValue;

    }

    private static void extracted(MappingJacksonValue mappingJacksonValue) {
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
    }
}
