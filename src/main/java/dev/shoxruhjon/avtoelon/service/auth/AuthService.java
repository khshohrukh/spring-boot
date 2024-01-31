package dev.shoxruhjon.avtoelon.service.auth;

import dev.shoxruhjon.avtoelon.dto.request.SignupRequest;
import dev.shoxruhjon.avtoelon.dto.response.UserResponse;

public interface AuthService {
    UserResponse createCustomer(SignupRequest signupRequest);
    void createAdminAccount();
    boolean hasCustomerWithEmail(String email);
}
