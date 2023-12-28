package rocks.tuwa.xpans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
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
        userService.validateUser(new ValidateUserDto(userId, password));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {
        // magic
        userService.createUser(createUserDto);
        return ResponseEntity.ok("User wurde erstellt");
    }
}