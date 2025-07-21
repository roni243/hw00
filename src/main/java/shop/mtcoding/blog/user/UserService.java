package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void join(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity());
    }


    public User login(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user == null) throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");

        if (!user.getPassword().equals(loginDTO.getPassword())) throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");

        return user;
    }
}
