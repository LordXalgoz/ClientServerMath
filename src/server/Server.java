package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    static void Log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        System.in.read();

        ServerSocket listener = null;
        Controller controller = new Controller();

        String result;

        try {
            listener = new ServerSocket(37152, 12, InetAddress.getByName("127.0.0.1"));
            Log("server is started");
        } catch (Exception e) {
            Log("error! server is NOT started");
            return;
        }

        while (true) {

            Log("server is listening...");

            Socket talking = null;
            DataInputStream in;
            DataOutputStream out;

            boolean isRun = false;


            try {
                talking = listener.accept();
                Log("client is connected");

                in = new DataInputStream(talking.getInputStream());
                out = new DataOutputStream(talking.getOutputStream());

                isRun = true;
            }
            catch (Exception e)
            {
                Log("client error: "+e.getMessage());
                continue;
            }

            try {
                while (isRun == true) {
                    String request = in.readUTF();
                    Log("from client: " + request);

                    String response = "";
                    String answer = request.substring(7);

                    switch (request) {
                        case "/hello":
                            response = "hi";
                            break;
                        case "/generate":
                            response = controller.GenerateMathExample();
                            break;
                        default:
                            response = controller.CheckAnswer(answer);
                            break;
                    }

                    out.writeUTF(response);
                    Log("to client: " + response);
                }
                talking.close();
            }
            catch (Exception e)
            {
                Log("client error: "+e.getMessage());
                isRun=false;
            }
        }
    }
}
