package org.example.blizzardapi.Service;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public interface restCallService {

    JsonNode callURL (String urlString) throws IOException;
}
