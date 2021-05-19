package esanchez.devel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esanchez.devel.app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
