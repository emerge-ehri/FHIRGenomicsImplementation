package demo2;

import org.hl7.fhir.r4.model.StringType;

public class UserDto2 {
    private long id;
    private StringType emailaddress;

    public UserDto2(){}

    public UserDto2(long id, StringType emailaddress) {
        this.id = id;
        this.emailaddress = emailaddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StringType getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(StringType emailaddress) {
        this.emailaddress = emailaddress;
    }
}
