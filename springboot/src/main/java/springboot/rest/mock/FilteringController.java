package springboot.rest.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MockBean retrieveMockBean() {
        return new MockBean("fieldValue1", "fieldValue2", "fieldValue3");
    }

    @GetMapping("/filtering-list")
    public List<MockBean> retrieveListOfMockBeans() {
        return Arrays.asList(
                new MockBean("fieldValue1a", "fieldValue2a", "fieldValue3a"),
                new MockBean("fieldValue1b", "fieldValue2b", "fieldValue3b")
        );
    }
}
