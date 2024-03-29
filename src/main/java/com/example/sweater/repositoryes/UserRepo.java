
package com.example.sweater.repositoryes;

import com.example.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ss.
 *
 * @author Taras
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}