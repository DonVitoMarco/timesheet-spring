package pl.thewalkingcode.service;

import pl.thewalkingcode.model.UserRegisterDTO;


public interface IUserCommandService {

    int registerUser(UserRegisterDTO userRegisterDTO);

    int changeRoleUser(String userId);

    int changeEnableUser(String userId);

}
