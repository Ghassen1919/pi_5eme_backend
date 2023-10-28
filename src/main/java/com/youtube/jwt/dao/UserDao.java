package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserName(String username);
    @Query("SELECT u FROM User u JOIN u.role r " +
            "WHERE r.roleName = :roleName " +
            "AND r.roleDescription = :roleDescription")
    List<User> findUsersByRoleNameAndDescription(String roleName, String roleDescription);
    boolean existsByEmail(String email);
    boolean existsByUserName(String name);
List<User> findByUserFirstName(String a);
}
