//package com.thrift;
//
//import com.thrift.thriftgen.thrift.Person;
//import com.thrift.thriftgen.thrift.PersonService;
//import org.apache.thrift.TException;
//
///**
// * @author John·Louis
// * @date create in 2019/10/14
// * description:
// */
//public class PersonServiceImpl implements PersonService.Iface {
//
//    @Override
//    public Person getPersonByUsername(String username) throws TException {
//        System.out.println("username: " + username);
//        Person person = new Person();
//        person.setUsername(username);
//        person.setAge(11);
//        person.setMarried(false);
//        return person;
//    }
//
//    @Override
//    public void savePerson(Person person) throws TException {
//
//    }
//}
