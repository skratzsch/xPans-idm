package rocks.tuwa.xpans.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.User;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUser(ValidateUserDto validateUserDto) {

        Optional<User> returnedUser = userRepository.findByUserId(validateUserDto.getUserId());

        return returnedUser.map(user ->
                        user.getHashedPassword().equals(validateUserDto.getPassword()))
                        .orElse(false);
    }

    public boolean createUser(CreateUserDto createUserDto) {

        if (createUserDto == null) {
            log.error("CreateUserDto ist null");
            return false;
        }

        if (!isValidCreatedUserDto(createUserDto)) {
            log.error("CreateUserDto ist nicht gültig: " + createUserDto);
            return false;
        }

        try {
            User user = User.fromCreateUser(createUserDto);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Da ist was schief gelaufen beim speichern", e);
            return false;
        }
        return true;
    }

    private boolean isValidCreatedUserDto(CreateUserDto dto) {
        return dto.getUserId() != null && !dto.getPassword().isEmpty();
    }
}
