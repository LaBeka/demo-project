package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.entities.CartEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CartRepository extends BaseRepository<CartEntity> {

    public List<CartDto> getCart(Long id) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.cart.CartDto(
                              c.id, c.userId)
                              from CartEntity c
                              where c.userId = :id
                              """, CartDto.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void delete(Long id) {
        Query query = em.createQuery("""
                              delete
                              from CartEntity c
                              where c.userId = :id
                              """)
                .setParameter("id", id);
        query.executeUpdate();
    }
}
