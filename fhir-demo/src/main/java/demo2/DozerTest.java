package demo2;

import java.time.LocalDate;
import java.util.Arrays;

import org.dozer.DozerBeanMapper;

public class DozerTest {

    public static void main(String[] args) {
        mapperMethod();
    }

    public static void mapperMethod(){

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

        User user = new User(222L, "Fei Yan", "fei@demo1.com", LocalDate.of(1980,8,20));

        dozerBeanMapper.setMappingFiles(Arrays.asList("dozerusermapper.xml"));

        UserDto1 userDto1 = dozerBeanMapper.map(user, UserDto1.class);
        UserDto2 userDto2 = dozerBeanMapper.map(user, UserDto2.class);

        System.out.println(user.getId()+","+userDto1.getId());
        System.out.println(user.getName()+","+userDto1.getUsername().getFamily());
        System.out.println(user.getId()+","+userDto2.getId());
        System.out.println(user.getEmail()+","+userDto2.getEmailaddress());
    }
}

