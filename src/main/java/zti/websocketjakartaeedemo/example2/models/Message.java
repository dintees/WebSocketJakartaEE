package zti.websocketjakartaeedemo.example2.models;

public class Message {
    private final String Message;
    private final String Author;
    private String Color;

    public Message(String message, String author, String color) {
        this.Message = message;
        this.Author = author;
        this.Color = color;
    }

    public String getMessage() {
        return Message;
    }

    public String getAuthor() {
        return Author;
    }

    public String getColor() { return Color; }

    public void setColor(String color) { Color = color; }
}
