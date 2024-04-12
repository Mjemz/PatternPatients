package org.design.patternpatients.Users.Repository;
import org.design.patternpatients.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<Users, Long> {
}
