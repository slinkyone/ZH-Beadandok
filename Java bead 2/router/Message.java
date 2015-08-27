package router;

import java.util.Scanner;

public class Message implements encoding.Textualizable {
    
    String title;
    String content;

    public Message() {
    }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void fromText(String s) {
        Scanner sc = new Scanner(s).useDelimiter("@");
        this.title = sc.next();
        this.content = sc.next();
    }

    @Override
    public String toText() {
        return this.title + "@" + this.content;
    }

    @Override
    public void reset() {
        this.title = null;
        this.content = null;
    }

    @Override
    public String toString() {
        return "[MESSAGE] Title: " + this.title + ", Content: " + this.content;
    }

}
