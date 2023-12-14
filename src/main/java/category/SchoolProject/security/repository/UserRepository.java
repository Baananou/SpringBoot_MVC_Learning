package category.SchoolProject.security.repository;

import category.SchoolProject.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String > {
    public AppUser findAppUserByUserName(String userName);
}
