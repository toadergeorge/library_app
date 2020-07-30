package com.library.library.service;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Author;
import com.library.library.domain.model.User;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.AuthorEntityToAuthorMapper;
import com.library.library.mapper.AuthorToAuthorEntityMapper;
import com.library.library.mapper.UserEntityToUserMapper;
import com.library.library.mapper.UserToUserEntityMapper;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserEntityToUserMapper userEntityToUserMapper;
    private final UserToUserEntityMapper userToUserMapper;

    public User findById(long userId) {
        return repository.findById(userId)
                .map(userEntityToUserMapper::convert)
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));
    }

    public List<User> getAll() {
        return repository.getAll()
                .stream()
                .map(userEntityToUserMapper::convert)
                .collect(Collectors.toList());
    }

    public User create(User user) {
        UserEntity userEntity = userToUserMapper.convert(user);

        UserEntity savedEntity = repository.save(userEntity);
        return userEntityToUserMapper.convert(savedEntity);
    }

    @Transactional
    public void updateTransactional(User user) {
        UserEntity existingEntity = repository.findById(user.getId())
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));

        updateFields(existingEntity, user);
    }

    private void updateFields(UserEntity existingEntity, User user) {
        existingEntity.setFirstName(user.getFirstName());
        existingEntity.setLastName(user.getLastName());
        existingEntity.setCity(user.getCity());
        existingEntity.setCountry(user.getCountry());
        existingEntity.setEmail(user.getEmail());
        existingEntity.setDateOfBirth(user.getDateOfBirth());
    }

    public void delete(Long id) {
        UserEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The usere with id provided cannot be found"));

        repository.delete(existingEntity);
    }
}
