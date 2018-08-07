package edu.msg.ro.boundary;

import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.transfer.UserDTO;

import java.util.List;

public interface UserManagement {

    /**
     * Method is used for persisting an user from an userDTO.
     * It generates the username and does the validations.
     * @param userDTO user information
     * @return the newly created entity as a userDTO
     */
    UserDTO createUser(UserDTO userDTO) throws BusinessException;

    void deactivateUser(String username);

    void activateUser(String username);

    List<UserDTO> getAllUsers();

    UserDTO login(String username, String password) throws BusinessException;






}
