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

        boolean isValid = userService.validateUser(new ValidateUserDto(userId, password));

        return isValid ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {

        boolean isValid = userService.createUser(createUserDto);

        return isValid ? ResponseEntity.ok("User wurde erstellt") : ResponseEntity.badRequest().build();
    }
}