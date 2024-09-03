package com.tagsolution.ecommercebackend.service;

import com.tagsolution.ecommercebackend.model.request.UserDetailsUpdateRequest;
import com.tagsolution.ecommercebackend.model.response.UserResponse;

public interface UserService {

    UserResponse findByUserId(Long id);

    UserResponse meData(String email);

    UserResponse userUpdate(String email, UserDetailsUpdateRequest request);
}
