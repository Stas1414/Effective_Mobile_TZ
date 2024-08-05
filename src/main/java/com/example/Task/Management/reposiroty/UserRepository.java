package com.example.Task.Management.reposiroty;

import com.example.Effective_Mobile_TZ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
