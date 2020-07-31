package com.where2beer.ws.user.dao;

import com.where2beer.ws.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByFirebaseId(String firebaseId);

    boolean existsByEmailContainingIgnoreCase(String email);

    boolean existsByPseudoContainingIgnoreCase(String pseudo);
}
