package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUserDto {

    public ValidateUserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    private String userId;

    private String password;

}
