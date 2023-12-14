package category.SchoolProject.security.repository;

import category.SchoolProject.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, String > {
}
