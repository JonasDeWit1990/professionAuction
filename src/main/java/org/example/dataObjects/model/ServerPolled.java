package org.example.dataObjects.model;

import javax.persistence.*;

@Entity
public class ServerPolled {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable=false)
    private String serverName;

    @Column(nullable = false)
    private Long lastPolled;

    protected ServerPolled() {

    }

    public ServerPolled(String serverName, Long lastPolled) {
        this.serverName = serverName;
        this.lastPolled = lastPolled;
    }
}
