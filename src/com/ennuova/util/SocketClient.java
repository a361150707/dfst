package com.ennuova.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class SocketClient  extends Thread {  
    private Socket socket;  
    private int port = 7870;  
    private String hostIp = "36.250.69.10";  
    public SocketClient(byte[] b){  
        try {  
            BufferedReader in = null;  
            PrintWriter out = null;  

                socket = new Socket(hostIp, port);  
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
                out = new PrintWriter(socket.getOutputStream(), true); 
                System.out.println("我发送的消息:" +Arrays.toString(b));
                out.println(Arrays.toString(b)); 
                System.out.println("服务器返回的消息:" + in.readLine());  
            out.close();  	
            in.close();  
            socket.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    public static void main(String[] args) {
    	
    	String dd = "我是本次测试消息";
    	byte[] b = dd.getBytes();
    	new SocketClient(b);
	}
}