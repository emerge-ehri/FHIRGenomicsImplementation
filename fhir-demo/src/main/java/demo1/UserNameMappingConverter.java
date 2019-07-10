package demo1;

import org.dozer.CustomConverter;
import org.hl7.fhir.r4.model.HumanName;

public class UserNameMappingConverter implements CustomConverter {

    public HumanName convert(Object destination,
                             Object source, Class<?> destinationClass,
                             Class<?> sourceClass) {

        HumanName returnValue = new HumanName();

        if(source != null){
            returnValue.setFamily(source.toString());
            return returnValue;
        }

        return null;
    }
}