package rocks.tuwa.xpans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.User;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUser(ValidateUserDto validateUserDto) {

        Optional<User> returnedUdser = userRepository.findByUserId(validateUserDto.getUserId());

        return returnedUdser.map(user ->
                        user.getHashedPassword().equals(validateUserDto.getPassword()))
                .orElse(false);
    }

    public boolean createUser(CreateUserDto createUserDto) {
        //magic
        return true;
    }
}
