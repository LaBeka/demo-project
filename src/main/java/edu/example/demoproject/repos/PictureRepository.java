package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.picture.PictureCreateDto;
import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository extends BaseRepository<PictureEntity> {

    public PictureDto findPictureEntityByProductId(Long id) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.picture.PictureDto(
                              p.name, p.productId)
                              from PictureEntity p
                              where p.productId = :id
                              """, PictureDto.class)
                .setParameter("productId", id)
                .getSingleResult();
    }

    public void update(PictureCreateDto pictureCreateDto) {

    }
}
