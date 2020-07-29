package com.where2beer.ws.user.dao;

import com.where2beer.ws.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByFirebaseId(String firebaseId);

    boolean existsByEmail(String email);

    boolean existsByPseudo(String pseudo);
}
