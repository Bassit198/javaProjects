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


    @PostMapping("/session/create/{username}")
    public void createSession(@RequestBody Session session, @PathVariable String username){
        Session checkSession = sessionRepo.findByUsername(username);
        if(checkSession==null){
            Session newSession = new Session();
            newSession.setUsername(session.getUsername());
            newSession.setStatus(1);
            sessionRepo.save(newSession);
        }else{
            checkSession.setStatus(1);
            sessionRepo.save(checkSession);
        }
//        Session newSession = new Session();
//        newSession.setUsername(session.getUsername());
//        newSession.setStatus(1);
//        sessionRepo.save(newSession);
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

    @PostMapping("/session/logout/{username}")
    public void logout(@PathVariable String username){
        Session session = sessionRepo.findByUsername(username);
        session.setStatus(0);
        sessionRepo.save(session);
    }

}


