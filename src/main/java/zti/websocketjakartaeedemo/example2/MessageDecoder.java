package zti.websocketjakartaeedemo.example2;

import jakarta.json.Json;
import jakarta.websocket.DecodeException;
import jakarta.websocket.EndpointConfig;
import zti.websocketjakartaeedemo.example2.models.Message;

import java.io.StringReader;

public class MessageDecoder implements jakarta.websocket.Decoder.Text<Message> {

    @Override
    public Message decode(String s) throws DecodeException {
        var json = Json.createReader(new StringReader(s)).readObject();
        String message = json.getString("message");
        String author = json.getString("author");
        String color = json.getString("color");
        return new Message(message, author, color);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            Json.createReader(new StringReader(s)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("Dencoder initializing...");
        Text.super.init(config);
    }

    @Override
    public void destroy() {
        System.out.println("Encoder destroying...");
        Text.super.destroy();
    }
}
