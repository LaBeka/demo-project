package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.entities.CartEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository extends BaseRepository<CartEntity> {

    public CartDto getCart(Long id) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.cart.CartDto(
                              c.id, c.userId)
                              from CartEntity c
                              where c.userId = :id
                              """, CartDto.class)
                .setParameter("userId", id)
                .getSingleResult();
    }

    public void create(Long id) {
        em.createNativeQuery("""
                insert into new edu.example.demoproject.etities.CartEntity(
                userId) VALUES (:userId)
                """, CartEntity.class)
                .setParameter("userId", id)
                .executeUpdate();
    }

    public void delete(Long id) {
    }
}
