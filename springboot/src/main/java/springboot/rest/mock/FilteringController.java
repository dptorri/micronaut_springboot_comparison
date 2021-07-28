package springboot.rest.mock;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveMockBean() {

        MockBean mockBean = new MockBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("MockBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(mockBean);
        mapping.setFilters(filters);

        return mapping;

    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfMockBeans() {
        List<MockBean> list = Arrays.asList(
            new MockBean("fieldValue1a", "fieldValue2a", "fieldValue3a"),
            new MockBean("fieldValue1b", "fieldValue2b", "fieldValue3b")
        );

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("MockBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);

        mapping.setFilters(filters);

        return mapping;
    }
}
