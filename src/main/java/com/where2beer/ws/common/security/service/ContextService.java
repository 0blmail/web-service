package com.where2beer.ws.common.security.service;

import com.where2beer.ws.common.exception.TechnicalException;
import com.where2beer.ws.common.security.exception.ForbiddenException;
import com.where2beer.ws.user.dao.UserDao;
import com.where2beer.ws.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContextService {

    private final UserDao userDao;

    public User loadUserByFirebaseId(String firebaseId) {
        return this.userDao.findByFirebaseId(firebaseId).orElseThrow(ForbiddenException::new);
    }

    public Optional<User> loadFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        return this.userDao.findByFirebaseId((String) authentication.getCredentials());
    }

    public User loadCurrent() {
        return this.loadFromContext().orElseThrow(TechnicalException::new);
    }
}
