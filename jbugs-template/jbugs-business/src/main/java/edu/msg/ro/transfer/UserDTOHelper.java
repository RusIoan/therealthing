package edu.msg.ro.transfer;

import edu.msg.ro.persistence.user.entity.User;

public class UserDTOHelper {


    public static UserDTO fromEntity(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        userDTO.setEmail(user.getEmail());



        userDTO.setUsername(user.getUsername());

        userDTO.setPhoneNumber(user.getPhoneNumber());

        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;

    }
}

