package springboot.rest.versioning;

public class PersonV1 {
    public String name;

    public PersonV1() {
        super();
    }

    public String getName() {
        return name;
    }

    public PersonV1(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
