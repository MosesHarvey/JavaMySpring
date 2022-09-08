package com.etask.repository;

import com.etask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    @Transactional
    void deleteByUserName(String name);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);

}
