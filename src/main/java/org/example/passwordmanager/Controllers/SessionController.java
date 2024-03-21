package org.example.passwordmanager.Controllers;

import org.example.passwordmanager.Models.Session;
import org.example.passwordmanager.Models.Users;
import org.example.passwordmanager.Repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {

    @Autowired
    private SessionRepo sessionRepo;


    @PostMapping("/session/create")
    public void createSession(@RequestBody Session session){
        Session newSession = new Session();
        newSession.setUsername(session.getUsername());
        newSession.setStatus(1);
        sessionRepo.save(newSession);
    }

    @DeleteMapping("/session/destroy")
    public void destroySession(@RequestBody Session session){
        sessionRepo.deleteByUsername(session.getUsername());

    }

    @GetMapping("/session/check/{username}")
    public int returnStatus(@PathVariable String username){
        Session session = sessionRepo.findByUsername(username);
        if(session.getStatus() == 1){
            return 1;
        }else{
            return -1;
        }
    }

}


