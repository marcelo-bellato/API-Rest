package br.com.projetoApi.api.services.impl;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.domain.dto.UserDTO;
import br.com.projetoApi.api.repositories.UserRepository;
import br.com.projetoApi.api.services.UserService;
import br.com.projetoApi.api.services.exceptions.DataIntegratyViolationException;
import br.com.projetoApi.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String OBJECT_NOT_FOUND = "Objeto não encontrado";
    private static final String EMAIL_ALREADY_EXISTS = "E-mail já cadastrado no sistema";

    private final UserRepository repository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        validateEmail(userDTO);
        User user = mapper.map(userDTO, User.class);
        return repository.save(user);
    }

    @Override
    public User update(UserDTO userDTO) {
        validateEmail(userDTO);
        User user = mapper.map(userDTO, User.class);
        return repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void validateEmail(UserDTO userDTO) {
        Optional<User> user = repository.findByEmail(userDTO.getEmail());

        if (user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
            throw new DataIntegratyViolationException(EMAIL_ALREADY_EXISTS);
        }
    }
}