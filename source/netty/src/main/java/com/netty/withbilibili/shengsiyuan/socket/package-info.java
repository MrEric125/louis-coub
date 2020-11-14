/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 *
 * 网络编程过程
 * 服务端编写方式
 * 1. ServerSocket severSocket=...
 * 2. serverSocket.bind(8899);
 * 3.
 * while(true){
 *     Socket socket =serverSocket.accept(); //阻塞
 *     new Thread(()->{
 *         socket.getInputStream()
 *         .....
 *     }).start();
 * }
 *
 * 客户端编写方式：
 * Socket socket =new Socket("localhost",8899);
 * socket.connect()
 *
 * 当服务端监听到有一个客户端连接上的时候，就会创建一个随机端口和服务端创立连接，然后服务端的随机端口和客户端端口连接
 */
package com.netty.withbilibili.shengsiyuan.socket;