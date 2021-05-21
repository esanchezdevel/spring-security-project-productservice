package esanchez.devel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esanchez.devel.app.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
