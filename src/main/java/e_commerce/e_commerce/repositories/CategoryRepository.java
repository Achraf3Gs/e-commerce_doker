package e_commerce.e_commerce.repositories;


import e_commerce.e_commerce.auth.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}

