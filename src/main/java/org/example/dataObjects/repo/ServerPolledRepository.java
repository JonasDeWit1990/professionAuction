package org.example.dataObjects.repo;

import org.example.dataObjects.model.ServerPolled;
import org.springframework.data.repository.CrudRepository;

public interface ServerPolledRepository extends CrudRepository<ServerPolled,Long> {
    ServerPolled findByServerName(String serverName);

}
