package com.thrift;

import com.thrift.thriftgen.thrift.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author John·Louis
 * @date create in 2019/10/13
 * description:
 * {@link org.apache.thrift.protocol.TBinaryProtocol
 * @link org.apache.thrift.protocol.TJSONProtocol
 *
 * }
 *
 * Thrift 数据传输方式
 * {@link org.apache.thrift.server.TSimpleServer 简单的单线程服务模型，常用语测试
 * @link org.apache.thrift.server.TThreadPoolServer 多线程服务模型 使用标准的阻塞是IO
 * @link org.apache.thrift.server.TNonblockingServer 多线程服务模型，使用非阻塞式IO(需要使用
 * {@link org.apache.thrift.transport.TFramedTransport}数据传输方式)
 * @link org.apache.thrift.server.THsHaServer THsHa 引入了线程池去处理，其模型把读写任务放在了线程池去
 * 处理，Half-sync/Half-async的处理模式，Half-aysnc是在处理IO时间上Half-sync用于handler对于RPC的同步处理
 * }
 * Thrift 传输格式
 */
public class LouisThriftServer {

    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.processorFactory(new TProcessorFactory(processor));
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());

        TServer server = new THsHaServer(arg);

        server.serve();

    }

}
