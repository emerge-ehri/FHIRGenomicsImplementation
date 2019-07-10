package demo2;

import org.dozer.CustomConverter;
import org.hl7.fhir.r4.model.HumanName;

public class Converter implements CustomConverter {

    public HumanName convert(Object fhirName,
                             Object name, Class<?> destinationClass,
                             Class<?> sourceClass) {

        HumanName returnValue = new HumanName();

        if(name != null){
            return returnValue.setFamily(name.toString());
        }

        return null;
    }
}
