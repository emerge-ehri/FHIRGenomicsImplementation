package demo2;

import org.hl7.fhir.r4.model.HumanName;

public class UserDto1 {
    private long id;
    private HumanName username;

    public UserDto1(){}

    public UserDto1(long id, HumanName username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HumanName getUsername() {
        return username;
    }

    public void setUsername(HumanName username) {
        this.username = username;
    }
}
