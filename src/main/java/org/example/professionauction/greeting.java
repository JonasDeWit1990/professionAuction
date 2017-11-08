package org.example.professionauction;

public class greeting {
    private final long id;
    private final String content;

    greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId(){return id;}

    public String getContent(){return content;}
}
