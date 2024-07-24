package backend.services;
import backend.DTOs.UserDTO;
import backend.exceptions.NotFoundException;
import backend.DTOs.CreateUpdateUserDTO;
import backend.mappers.UserMapper;
import backend.models.User;
import backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(CreateUpdateUserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = repo.save(user);
        return mapper.toDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, CreateUpdateUserDTO userDTO) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        mapper.updateEntityFromDTO(userDTO, existingUser);
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        repo.save(existingUser);
        return mapper.toDTO(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        repo.delete(user);
    }

    public UserDTO getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return mapper.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
