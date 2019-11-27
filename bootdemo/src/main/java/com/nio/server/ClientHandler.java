

public class ClientHandler{

    public static final int Max_DATA_LEN=1024;
    private final Socket socket;
    public ClientHandler(Socket socket){
        this.socket=socket;
    }
    public void start(){
        new Thread(new Runnable(){
            public void run(){
                doStart();
            }
        }).start();
    }
    private void doStart(){
        
    }
}