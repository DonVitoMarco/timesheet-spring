package pl.thewalkingcode.service;

import pl.thewalkingcode.model.UserQueryDTO;

import java.util.List;


public interface IUserQueryService {

    boolean checkExistUsername(String username);

    List<UserQueryDTO> getAllUsers();

}
