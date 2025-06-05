package com.example.login.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class MainApiController {

    @GetMapping("/main")
    public ResponseEntity<?> showMainForm(@RequestParam String userId){

        return ResponseEntity.ok().body("환영합니다, " + userId + "님");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 되었습니다.");
    }
}
