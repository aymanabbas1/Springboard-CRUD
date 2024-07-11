package com.example.demo.web;

import com.example.demo.persistence.Ruben;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController // ctrl w - extend selection, alt j - multi line selection for current state
@RequestMapping(path = "/hibernate")
public class HibernateController {

    private Map<UUID, String> dataStore = new HashMap<>();
    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public Set<UUID> getAllhelloWorldData() {
        return dataStore.keySet();
    }

    @GetMapping(path = "/{id}")
    public String getHelloWorldDataById(@PathVariable("id") UUID id) {
        return dataStore.get(id);
    }

    @PostMapping
    @Transactional
    public Ruben postHelloWorld(@RequestBody Ruben body) { // req - consists of many things like headers and body etc.
        Session session = entityManager.unwrap(Session.class); //spring - jpa and hibernate
//        UUID key = randomUUID(); // Long IDs let database generate it, UUID can be generated in srcCode randomly.
        Ruben ruben = new Ruben();
//        ruben.setId(key);
//        ruben.setName("xyzx");
//        ruben.setOccupation("Gamer");
        session.persist(body);
        return session.createQuery("SELECT R FROM Ruben R where R.Id = :Id", Ruben.class).setParameter("Id", body.getId()).getSingleResult();
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
