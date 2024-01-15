package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.image.ImageDto;
import edu.example.demoproject.entities.ImageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository extends BaseRepository<ImageEntity>{

    public ImageEntity getImageByItsId(Long id) {
        return em.createQuery("""
                              select i
                              from ImageEntity i
                              where i.id = :id
                              """, ImageEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public void deleteObjectInDB(Long pictureId) {
        em.createQuery("delete ImageEntity i where i.id = :pictureId")
                .setParameter("pictureId", pictureId)
                .executeUpdate();
    }
    public List<ImageDto> getImagesOfProduct(Long productId) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.image.ImageDto(
                              p.id, p.productId )
                              from ImageEntity p
                              where p.productId = :productId
                              """, ImageDto.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}
