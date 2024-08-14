package com.techv.vitor.service;

import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public UserResponseDto insertUsers(UserRequestDto requestDto) {

        User user = new User();

        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        user.setLastModified(LocalDateTime.now());
        user.setIntegrated(Integrated.TRUE);
        user.setAdmin(Admin.ADMIN);

        userRepository.save(user);

        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setLastModified(LocalDateTime.now());

        if (responseDto.getPassword() == null) {
            throw new PasswordOrUsernameException(
                    "Password can not be null...",
                    HttpStatus.PRECONDITION_FAILED);
        } else if (responseDto.getUsername() == null) {
            throw new PasswordOrUsernameException(
                    "Username can not be null...",
                    HttpStatus.PRECONDITION_FAILED);
        }

        return responseDto;

    }

}
