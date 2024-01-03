package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private String userId;

    private String hashedPassword;

    private String description;

    private LocalDateTime dateCreated;


    public User(String userId, String hashedPassword, String description) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.description = description;
    }

    public static User fromCreateUser(CreateUserDto createUserDto) {
        return new User(createUserDto.getUserId(), createUserDto.getPassword(),
                createUserDto.getDescription());
    }

}