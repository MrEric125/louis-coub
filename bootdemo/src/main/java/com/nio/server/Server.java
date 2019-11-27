

public class Server{

    private ServerSocket serverSocket;

    public Server(int port){
        this.serverSocket=new ServerSocket(port);
        Sout("服务器启动成功，端口： "+port);

    }
    public void start(){
        new Thread(new Runnable(){
            public void run(){
                doStart()
            }
        })
    }
    public void doStart(){
        while(true){
            Socket server=serverSocket.accept();
            new ClientHandler(server).start();
        }
    }
}