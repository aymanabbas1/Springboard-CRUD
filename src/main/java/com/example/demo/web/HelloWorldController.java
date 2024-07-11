package com.example.demo.web;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequestMapping(path = "/helloworld")
public class HelloWorldController {

    private Map<UUID, String> dataStore = new HashMap<>();


    @GetMapping
    public Set<UUID> getAllhelloWorldData() {
        return dataStore.keySet();
    }

    @GetMapping(path = "/{id}")
    public String getHelloWorldDataById(@PathVariable("id") UUID id) {
        return dataStore.get(id);
    }

    @PostMapping
    public UUID postHelloWorld(@RequestBody String body) { // req - consists of many things like headers and body etc.
        UUID key = randomUUID();
        dataStore.put(key, body);
        return key;
    }

    @PutMapping(path = "/{id}")
    public UUID postHelloWorldData(@PathVariable("id") UUID id, @RequestBody String body) {
        dataStore.put(id, body);
        return id;
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void deleteHelloWorldData(@PathVariable("id") UUID id) {
        dataStore.remove(id);
    }


}
