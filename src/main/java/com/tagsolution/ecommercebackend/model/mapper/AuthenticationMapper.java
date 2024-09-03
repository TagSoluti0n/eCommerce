package com.tagsolution.ecommercebackend.model.mapper;

import com.tagsolution.ecommercebackend.model.entity.AccountDetailsEntity;
import com.tagsolution.ecommercebackend.model.entity.RoleEntity;
import com.tagsolution.ecommercebackend.model.entity.UserEntity;
import com.tagsolution.ecommercebackend.model.request.RegisterRequest;
import com.tagsolution.ecommercebackend.model.response.AuthenticationResponse;
import com.tagsolution.ecommercebackend.model.response.RegisterResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationMapper {

    public static UserEntity toEntity(RegisterRequest dto) {
        AccountDetailsEntity accountDetails = new AccountDetailsEntity();
        accountDetails.setEmail(dto.getEmail());
        accountDetails.setPassword(dto.getPassword());
        accountDetails.setFirstName(dto.getFirstName());
        accountDetails.setLastName(dto.getLastName());
        return UserEntity.builder()
                .accountDetails(accountDetails)
                .roles(List.of(RoleEntity.builder().id(dto.getRoleId()).build()))
                .build();
    }

    public static RegisterResponse registerToDto(UserEntity ent, String jwt) {
        return RegisterResponse.builder()
                .email(ent.getAccountDetails().getEmail())
                .firstName(ent.getAccountDetails().getFirstName())
                .lastName(ent.getAccountDetails().getLastName())
                .jwt(jwt)
                .build();
    }

    public static AuthenticationResponse authenticationToDto(Authentication userInContext, String jwt) {
        return AuthenticationResponse.builder()
                .email(userInContext.getName())
                .jwt(jwt)
                .build();
    }
}
