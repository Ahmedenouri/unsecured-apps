package ma.enset.hospitalapp.security.repositories;

import ma.enset.hospitalapp.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername (String user);
}
