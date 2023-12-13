package category.SchoolProject.repositories;

import category.SchoolProject.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, String > {
}
