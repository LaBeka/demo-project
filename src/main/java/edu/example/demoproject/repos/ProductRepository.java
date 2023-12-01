package edu.example.demoproject.repos;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.ProductEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository extends BaseRepository<ProductEntity> {

    public ProductDto getFullInfoById(Long id) {
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.product.ProductDto(
                              p.id, p.name, p.description, p.initialPrice, p.discount, 
                              p.currentPrice, p.newProduct, p.brand, p.category)
                              from ProductEntity p
                              where p.id = :id
                              """, ProductDto.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }
    public List<ProductDto> getAllProducts(){
        return em.createQuery("""
                        select new edu.example.demoproject.dtos.product.ProductDto(
                        p.id, p.name, p.description, p.initialPrice, p.discount, 
                        p.currentPrice, p.newProduct, p.brand, p.category)
                        from ProductEntity p
                        """, ProductDto.class)
                .getResultList();
    }
    public List<ProductDto> findCaseInsensitiveBy(String title){
        return em.createQuery("""
                              select new edu.example.demoproject.dtos.product.ProductDto(
                              p.id, p.name, p.description, p.initialPrice, p.discount, 
                              p.currentPrice, p.newProduct, p.brand, p.category)
                              from ProductEntity p
                              where lower(p.name) like concat('%',:title,'%') 
                              or lower(p.description) like concat('%',:title,'%')
                              """, ProductDto.class)
                .setParameter("title",
                        title.toLowerCase()
                                .strip())
                .getResultList();
    }


    public void updateProduct(Long id, ProductCreateDto dto) {
        StringBuilder querySb = new StringBuilder("""
                                                  update ProductEntity s
                                                  set 
                                                  """);
        Map<String, Object> params = new HashMap<>();
        if (dto.getName() != null) {
            querySb.append("s.name = :name");
            params.put("name", dto.getName());
        }
        if (dto.getDescription() != null) {
            querySb.append(", s.description = :description");
            params.put("description", dto.getDescription());
        }
        if (dto.getInitialPrice() != null) {
            querySb.append(", s.initialPrice = :initialPrice");
            params.put("initialPrice", dto.getInitialPrice());
        }
        if (dto.getStorageQty() != null) {
            querySb.append(", s.storageQty = :storageQty");
            params.put("storageQty", dto.getStorageQty());
        }
        if (dto.getDiscount() != null) {
            querySb.append(", s.discount = :discount");
            params.put("discount", dto.getDiscount());
        }
        if (dto.getBrand() != null) {
            querySb.append(", s.genre = :genre");
            params.put("genre", dto.getBrand());
        }
        if (dto.getCategory() != null) {
            querySb.append(", s.stage = :stage");
            params.put("stage", dto.getCategory());
        }
        querySb.append(" where s.id =:id");
        params.put("id", id);

        Query query = em.createQuery(querySb.toString());
        params.forEach(query::setParameter);
        query.executeUpdate();
    }
}