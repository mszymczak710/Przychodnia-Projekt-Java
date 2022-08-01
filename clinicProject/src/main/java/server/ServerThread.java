package server;
// polaczenie serwer klient
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.tables.Doctors;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{

    Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    
    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ Usluga ]: polaczenia z: " + socket.getInetAddress() + "/" + socket.getPort());

        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream outputStream = new PrintStream(socket.getOutputStream());
            String JsonToClient = "";

            // W TY MIEJSCU SERWER ZCZYTUJE ZAPYTANIE TYPU            jsonFromClient =inputStream.readLine();


                // wstepnie tylko wyslanie przykladowego obiektu jako json i odczyt jego w kliencie
            System.out.println("JESTEM PRZED WYSLANIEM DO KLIENTA");
            Doctors test = new Doctors();
            Doctors test2 = new Doctors();

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String outToClient = objectMapper.writeValueAsString(test);
                System.out.println(outToClient);
                outputStream.println(outToClient);

                String outToClient2 = objectMapper.writeValueAsString(test2);
                System.out.println(outToClient2);
                outputStream.println(outToClient2);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            socket.close();
        }
        catch (Exception e) {
            System.err.println("[ Usluga ]: Exception: " + e);
        }
        System.out.println("[ Usluga ]: rozlaczanie: " + socket.getInetAddress () + "/" + socket.getPort ());
        System.out.println("* ------ *");
    }

    private String getServerResult(String userCommand, String userParams) {
        return "dupa";
    }

}
