package com.where2beer.ws.user.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.where2beer.ws.common.exception.NotFoundException;
import com.where2beer.ws.common.exception.TechnicalException;
import com.where2beer.ws.common.helper.GenericSpecificationBuilder;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.user.dao.UserDao;
import com.where2beer.ws.user.dto.UserDto;
import com.where2beer.ws.user.dao.UserSpecification;
import com.where2beer.ws.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public Page<User> search(List<SearchCriterion> criteria, Pageable pageable) {
        var builder = new GenericSpecificationBuilder<User>(criteria);

        return this.userDao.findAll(builder.build(UserSpecification::new), pageable);
    }

    public User create(UserDto userDto) {
        try {
            CreateRequest firebaseRequest = new CreateRequest()
                    .setEmail(userDto.getEmail())
                    .setPassword(userDto.getPassword())
                    .setDisplayName(userDto.getPseudo())
                    .setEmailVerified(userDto.isEmailVerified());
            var userRecord = FirebaseAuth.getInstance().createUser(firebaseRequest);

            var claims = new HashMap<String, Object>();
            claims.put("role", userDto.getRole().name());
            FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

            var user = User.builder()
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .pseudo(userDto.getPseudo())
                    .firebaseId(userRecord.getUid())
                    .emailVerified(userDto.isEmailVerified())
                    .role(userDto.getRole())
                    .build();

            return this.userDao.save(user);
        } catch (FirebaseAuthException e) {
            throw new TechnicalException();
        }
    }

    public User update(UserDto userDto) {
        var user = this.userDao.findById(userDto.getId()).orElseThrow(TechnicalException::new);

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailVerified(userDto.isEmailVerified());
        user.setDisabled(userDto.isDisabled());
        user.setRole(userDto.getRole());

        try {
            var claims = new HashMap<String, Object>();
            claims.put("role", userDto.getRole().name());

            UpdateRequest request = new UpdateRequest(user.getFirebaseId())
                    .setEmailVerified(user.isEmailVerified())
                    .setDisabled(user.isDisabled())
                    .setCustomClaims(claims);
            FirebaseAuth.getInstance().updateUser(request);

            return this.userDao.save(user);
        } catch (FirebaseAuthException e) {
            throw new TechnicalException();
        }
    }

    public User find(Long id) {
        return this.userDao.findById(id).orElse(null);
    }

    public void delete(Long id) {
        try {
            var user = this.userDao.findById(id).orElseThrow(NotFoundException::new);

            FirebaseAuth.getInstance().deleteUser(user.getFirebaseId());

            this.userDao.deleteById(id);
        } catch (FirebaseAuthException e) {
            throw new TechnicalException();
        }
    }

    public boolean emailExist(String email) {
        return this.userDao.existsByEmailContainingIgnoreCase(email);
    }

    public boolean pseudoExist(String pseudo) {
        return this.userDao.existsByPseudoContainingIgnoreCase(pseudo);
    }
}
