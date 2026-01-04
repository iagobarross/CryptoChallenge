package com.train.crypto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.train.crypto.dto.UserRequestDTO;
import com.train.crypto.dto.UserResponseDTO;
import com.train.crypto.model.User;

@Component
public class UserMapper {
    
  public UserResponseDTO toResponseDTO(User user){
        if (user == null) return null;
        
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserDocument(user.getUserDocument());
        dto.setCreditCardToken(user.getCreditCardToken());
        dto.setValue(user.getValue());
        return dto;
    }

    public List<UserResponseDTO> toResponseDTOList(List<User> users){
        return users.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setUserDocument(dto.getUserDocument());
        user.setCreditCardToken(dto.getCreditCardToken());
        user.setValue(dto.getValue());
        return user;
    }

}
