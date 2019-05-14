
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Server {

    private static int port = 8080;

    public static void main(String[] args) throws IOException {
        // write your code here
        ServerSocket serverSocket = new ServerSocket(port);

        while(true){
            //得到客户端
            Socket client = serverSocket.accept();

            //为客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        }

    }

    /**
     * 客户端消息处理的类
     */
    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket){ this.socket = socket; }

        @Override
        public void run(){
            super.run();

            try{
                //从客户端获取输入流
                InputStream inputStream = socket.getInputStream();
                //从客户端获取输出流
                OutputStream outputStream = socket.getOutputStream();

                //准备一个缓存数组
                byte inputData[] = new byte[4096];
                //将数据读取到字节数组中
                inputStream.read(inputData);

                //打印客户端发送来的报文
                System.out.println(new String(inputData));

                String responseMessage = makeResponseMessage();

                //将响应报文内容写入
                outputStream.write(responseMessage.getBytes());

                //关闭客户端和服务端的流
                inputStream.close();
                outputStream.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }


        private static String makeResponseMessage(){

            StringBuffer response = new StringBuffer();

            response.append("HTTP/1.1 200 OK\r\n");
            response.append("Content-type:text/html\r\n\r\n");
            //要返回的内容(当前时间)
            response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return response.toString();
        }

    }
}
