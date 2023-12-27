package rocks.tuwa.xpans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rocks.tuwa.xpans.services.UserService;

@RestController
@RequestMapping("/xpans")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/validateUser")
    public ResponseEntity<?> validateUser(@RequestParam String userId,
                                          @RequestParam String password) {
        // magic
        return ResponseEntity.ok().build();
    }
}