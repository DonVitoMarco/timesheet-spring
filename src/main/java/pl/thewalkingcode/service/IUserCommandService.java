package pl.thewalkingcode.service;


import pl.thewalkingcode.model.UserRegisterDTO;

public interface IUserCommandService {

    int registerUser(UserRegisterDTO userRegisterDTO);

    boolean changeRoleUser(String userId);

    boolean changeEnableUser(String userId);

}
