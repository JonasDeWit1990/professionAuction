package org.example.dataObjects.controller;

import org.example.dataObjects.model.ServerPolled;
import org.example.dataObjects.repo.ServerPolledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerPolledService{

    private final ServerPolledRepository repository;

    @Autowired
    public ServerPolledService(ServerPolledRepository repository) {
        this.repository = repository;
    }

    public Long GetModifiedForServer(String serverName) {
        ServerPolled Polled = repository.findByServerName(serverName);

        if(Polled == null)
            return null;
        else
            return Polled.getLastPolled();

    }

    public void SaveModifiedForServer(String serverName, Long lastModified) {
        ServerPolled Polled = repository.findByServerName(serverName);
        if(Polled != null)
            Polled.setLastPolled(lastModified);
        else
            Polled = new ServerPolled(serverName,lastModified);

        repository.save(Polled);
    }
}
