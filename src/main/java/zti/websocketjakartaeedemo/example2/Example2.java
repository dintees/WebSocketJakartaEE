package zti.websocketjakartaeedemo.example2;

import jakarta.websocket.EncodeException;
import jakarta.websocket.*;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import zti.websocketjakartaeedemo.example2.models.Message;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/example2/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class Example2 {
    private static final Set<Session> allSessions = new HashSet<>();

    private static void sendToAll(Message m, String sessionId) throws EncodeException, IOException {
        for (Session session : allSessions) {
            boolean changedColor = false;

            if (sessionId != null && session.getId().equals(sessionId) && m.getColor().isEmpty()) {
                m.setColor("#880");
                changedColor = true;
            }

            session.getBasicRemote().sendObject(m);
            if (changedColor) m.setColor("");
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException
    {
        allSessions.add(session);
        sendToAll(new Message("Joined!", username, "#088"), null);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException
    {
        sendToAll(message, session.getId());
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException, EncodeException
    {
        allSessions.remove(session);
        sendToAll(new Message("left the chat", username, "#f00"), session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable)
    {
        System.out.println("Error in session " + session.getId());
        throwable.printStackTrace();
    }
}
