package dev.shoxruhjon.avtoelon.controller;

import dev.shoxruhjon.avtoelon.dto.request.AuthecticationRequest;
import dev.shoxruhjon.avtoelon.dto.request.SignupRequest;
import dev.shoxruhjon.avtoelon.dto.response.AuthenticationResponse;
import dev.shoxruhjon.avtoelon.dto.response.UserResponse;
import dev.shoxruhjon.avtoelon.entity.UserEntity;
import dev.shoxruhjon.avtoelon.repository.UserRepository;
import dev.shoxruhjon.avtoelon.service.auth.AuthService;
import dev.shoxruhjon.avtoelon.service.jwt.UserService;
import dev.shoxruhjon.avtoelon.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        UserResponse createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null) return new ResponseEntity<>
                ("Customer not created, Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthecticationRequest authecticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authecticationRequest.getEmail(),
                    authecticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authecticationRequest.getEmail());
        Optional<UserEntity> optionalEmail = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalEmail.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalEmail.get().getId());
            authenticationResponse.setRole(optionalEmail.get().getRole());
        }
        return authenticationResponse;
    }
}
