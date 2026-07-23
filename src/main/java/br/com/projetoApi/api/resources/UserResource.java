package br.com.projetoApi.api.resources;

import br.com.projetoApi.api.domain.dto.UserDTO;
import br.com.projetoApi.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {

    private static final String ID_PATH = "/{id}";

    private final ModelMapper mapper;
    private final UserService service;

    public UserResource(ModelMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        UserDTO dto = mapper.map(service.findById(id), UserDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = service.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) {

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID_PATH)
                .buildAndExpand(service.create(userDTO).getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,
                                          @RequestBody UserDTO userDTO) {

        userDTO.setId(id);

        UserDTO dto = mapper.map(service.update(userDTO), UserDTO.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}