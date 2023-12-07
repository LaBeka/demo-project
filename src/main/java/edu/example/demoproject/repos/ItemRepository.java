package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.items.ItemCreateDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.entities.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemRepository extends BaseRepository<ItemEntity> {

    public ItemDto getById(Long id) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.items.ItemDto(
                              i.id, i.cartId, i.productId, i.qty)
                              from ItemEntity i
                              where i.id = :id
                              """, ItemDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Optional<ItemDto> ifItemInCartExists(ItemCreateDto dto) {
        return em.createQuery("""
                        select new edu.example.demoproject.dtos.items.ItemDto(
                        i.id, i.cartId, i.productId, i.qty)
                        from ItemEntity i
                        where i.productId = :productId
                        and i.cartId = :cartId
                        """, ItemDto.class)
                .setParameter("productId", dto.getProductId())
                .setParameter("cartId", dto.getCartId())
                .getResultStream()
                .findFirst();
    }

    public void incrementQty(ItemDto dto) {
        em.createQuery("update ItemEntity i set i.qty = :qty where i.id = :id")
                .setParameter("qty", dto.getQty() + 1)
                .setParameter("id", dto.getId())
                .executeUpdate();
    }

    public void decrementQty(ItemDto dto) {
        em.createQuery("update ItemEntity i set i.qty = :qty where i.id = :id")
                .setParameter("qty", dto.getQty() - 1)
                .setParameter("id", dto.getId())
                .executeUpdate();
    }

    public void deleteIfQtyNull(ItemDto dto) {
        em.createQuery("delete ItemEntity i where i.qty = 0 and i.id = :id")
                .setParameter("id", dto.getId())
                .executeUpdate();
    }

    public void delete(Long id) {
        em.createQuery("delete ItemEntity i where i.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
