package br.com.projetoapi.api.services;

import br.com.projetoapi.api.domain.User;
import br.com.projetoapi.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);
    void delete(Integer id);
}
