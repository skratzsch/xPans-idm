package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserDto {

    public CreateUserDto(String userId, String password, String description, LocalDateTime dateCreated) {
        this.userId = userId;
        this.password = password;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    private String userId;

    private String password;

    private String description;

    private LocalDateTime dateCreated;

}
