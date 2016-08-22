package pl.thewalkingcode.service;


import pl.thewalkingcode.model.UserRegisterDTO;

public interface IUserCommandService {

    int registerUser(UserRegisterDTO userRegisterDTO);

}
