package zti.websocketjakartaeedemo.example1;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/example1")
public class Example1 {
    @OnOpen
    public void onOpen(final Session session) throws IOException {
        String message = "Connection opened for session: " + session.getId();
        System.out.println(message);
        session.getBasicRemote().sendText(message);
    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException, InterruptedException {
        String message = "[" + session.getId() + "] " + msg;
        System.out.println(message);
        session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String message = "Connection closed for session: " + session.getId();
        System.out.println(message);
    }

    @OnError
    public void inError(Throwable error) throws IOException {
        System.out.println("Error occurred: " + error.getMessage());
    }
}
