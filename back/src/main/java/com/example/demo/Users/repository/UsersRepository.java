package com.example.demo.Users.repository;

import com.example.demo.Users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
  User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
