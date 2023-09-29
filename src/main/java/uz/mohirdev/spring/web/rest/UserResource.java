package uz.mohirdev.spring.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.spring.entity.User;
import uz.mohirdev.spring.service.UserServer;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserServer userServer;

    public UserResource(UserServer userServer) {
        this.userServer = userServer;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if (userServer.existsByLogin(user.getLogin())){
            return new ResponseEntity("Bu login mavjud", HttpStatus.BAD_REQUEST);
        }
        if(!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Password uzunligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }
        User result = userServer.save(user);
        return ResponseEntity.ok(result);
    }

    private Boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }
}
