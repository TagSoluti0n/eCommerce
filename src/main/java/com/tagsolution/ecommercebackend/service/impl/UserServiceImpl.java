package com.tagsolution.ecommercebackend.service.impl;

import com.tagsolution.ecommercebackend.model.entity.UserEntity;
import com.tagsolution.ecommercebackend.model.request.UserDetailsUpdateRequest;
import com.tagsolution.ecommercebackend.model.response.UserResponse;
import com.tagsolution.ecommercebackend.repository.UserRepository;
import com.tagsolution.ecommercebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse findByUserId(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        return UserResponse.toDto(foundUser);
    }

    @Override
    public UserResponse meData(String email) {
        var foundUser = userRepository.findByAccountDetailsEmail(email).orElseThrow();
        return UserResponse.toDto(foundUser);
    }

    @Override
    public UserResponse userUpdate(String email, UserDetailsUpdateRequest request) {

        var foundUser = userRepository.findByAccountDetailsEmail(email).orElseThrow();

        // Set Account Details
        foundUser.getAccountDetails().setFirstName(request.getFirstName());
        foundUser.getAccountDetails().setLastName(request.getLastName());
        foundUser.getAccountDetails().setDisplayName(request.getDisplayName());
        foundUser.getAccountDetails().setPhone(request.getPhone());
        foundUser.getAccountDetails().setBirthDate(request.getBirthDate());
        // Set Addresses
        foundUser.setBillingAddress(UserDetailsUpdateRequest.toBillingAddress(foundUser.getBillingAddress(), request));
        foundUser.setShippingAddress(UserDetailsUpdateRequest.toShippingAddress(foundUser.getShippingAddress(), request));

        return UserResponse.toDto(userRepository.save(foundUser));
    }
}
