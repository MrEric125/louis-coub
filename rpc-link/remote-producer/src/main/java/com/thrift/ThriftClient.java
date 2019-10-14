package com.thrift;

import com.thrift.thriftgen.thrift.Person;
import com.thrift.thriftgen.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.*;

/**
 * @author John·Louis
 * @date create in 2019/10/13
 * description:
 */
public class ThriftClient {
    public static void main(String[] args) throws TTransportException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUsername("zhangsan");
            System.out.println(person);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }


    }

}
