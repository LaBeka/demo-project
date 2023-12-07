package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PictureRepository extends BaseRepository<PictureEntity> {

    public Optional<PictureEntity> findPictureEntityByProductId(Long id) {
        return em.createQuery("""
                              select p
                              from PictureEntity p
                              where p.productId = :id
                              """, PictureEntity.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }


    public void update(Long id, PictureEntity newEntity) {
        em.createQuery("update PictureEntity p set p.imageContentType = :ict" +
                        ", p.image = : image where p.id = :id")
                .setParameter("ict", newEntity.getImageContentType())
                .setParameter("image", newEntity.getImage())
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<PictureDto> getImagesOfProduct(Long productId) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.picture.PictureDto(
                              p.id, p.productId )
                              from PictureEntity p
                              where p.productId = :productId
                              """, PictureDto.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public void delete(Long pictureId) {
        em.createQuery("delete PictureEntity p where p.id = :pictureId")
                .setParameter("pictureId", pictureId)
                .executeUpdate();
    }
}
