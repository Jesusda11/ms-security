package com.jda.ms_security.Controllers;

import com.jda.ms_security.Models.Session;
import com.jda.ms_security.Models.User;
import com.jda.ms_security.Repositories.SessionRepository;
import com.jda.ms_security.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sessions")

public class SessionControllers {

    @Autowired
    SessionRepository theSessionRepository;
    @Autowired
    UserRepository theUserRepository;

    @GetMapping("")
    public List<Session> find (){
        return this.theSessionRepository.findAll();
    }

    @GetMapping("{id}")
    public Session findById(@PathVariable String id){
        Session theSession= this.theSessionRepository.findById(id).orElse( null);
        return theSession;
    }

    @PostMapping
    public Session create(@RequestBody Session newSession){
        return this.theSessionRepository.save(newSession);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Session theSession = this.theSessionRepository.findById(id).orElse(null);
        if (theSession!= null){
            this.theSessionRepository.delete(theSession);
        }
    }

    @PutMapping("{id}")
    public Session update(@PathVariable String id, @RequestBody Session newSession){
        Session actualSession = this.theSessionRepository.findById(id).orElse(null);
        if (actualSession != null){
            actualSession.setToken(newSession.getToken());
            actualSession.setExpiration(newSession.getExpiration());
            this.theSessionRepository.save(actualSession);
            return actualSession;
        }

        else {
            return null;
        }
    }

    @PostMapping("{sessionId}/user/{userId}")
    public Session matchSession(@PathVariable String sessionId, @PathVariable String userId){
        Session theSession = this.theSessionRepository.findById(sessionId).orElse(null);
        User theUser = this.theUserRepository.findById(userId).orElse(null);
        if (theSession!=null && theUser!= null){
            theSession.setUser(theUser);
            this.theSessionRepository.save(theSession);
            return theSession;
        }else{
            return  null;
        }
    }

    @GetMapping("user/{userId}")
    public List<Session> getSessionByUser(@PathVariable String userId){
        return this.theSessionRepository.getSessionsByUser(userId);

    }



}

