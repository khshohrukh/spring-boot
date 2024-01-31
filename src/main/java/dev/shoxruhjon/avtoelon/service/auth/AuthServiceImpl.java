package dev.shoxruhjon.avtoelon.service.auth;

import dev.shoxruhjon.avtoelon.dto.request.SignupRequest;
import dev.shoxruhjon.avtoelon.dto.response.UserResponse;
import dev.shoxruhjon.avtoelon.entity.UserEntity;
import dev.shoxruhjon.avtoelon.entity.enums.Role;
import dev.shoxruhjon.avtoelon.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createCustomer(SignupRequest signupRequest) {
        UserEntity userEntity = modelMapper.map(signupRequest, UserEntity.class);
        userEntity.setRole(Role.CUSTOMER);
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
    }

    @Override
    @PostConstruct
    public void createAdminAccount() {
        Optional<UserEntity> userEntity = userRepository.findByRole(Role.ADMIN);
        if (userEntity.isEmpty()){
            UserEntity newAdminAccount = new UserEntity();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(passwordEncoder.encode("admin"));
            newAdminAccount.setRole(Role.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created successfully");
        }
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
