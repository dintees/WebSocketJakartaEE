package zti.websocketjakartaeedemo.example2;

import jakarta.json.Json;
import jakarta.websocket.EncodeException;
import jakarta.websocket.EndpointConfig;
import zti.websocketjakartaeedemo.example2.models.Message;

public class MessageEncoder implements jakarta.websocket.Encoder.Text<Message> {
    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("message", message.getMessage())
                .add("author", message.getAuthor())
                .add("color", message.getColor())
                .build().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        Text.super.init(config);
        System.out.println("Encoder initializing...");
    }

    @Override
    public void destroy() {
        Text.super.destroy();
        System.out.println("Encoder destroying...");
    }
}
