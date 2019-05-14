import java.net.Socket;
import java.io.*;
public class Client {
    private String serverAddress;
    private int port;
    public Client(){
        this("127.0.0.1",8080);
    }
    public Client(String serverAddress,int port){
        this.serverAddress=serverAddress;
        this.port=port;
    }


    public static void main(String [] args)
    {
        Client client = new Client();
        client.go();
    }

    public void go(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String requestLine="";

        while (true){
            System.out.print("telnet>");

        }
    }



}
