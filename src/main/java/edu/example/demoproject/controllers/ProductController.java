package edu.example.demoproject.controllers;

import edu.example.demoproject.dtos.PageDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.dtos.product.ProductSearchDto;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.entities.ProductEntity;
import edu.example.demoproject.mappers.ProductMapper;
import edu.example.demoproject.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    final private ProductService productService;
    final private ProductMapper productMapper;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity addProduct(@Valid ProductCreateDto productCreateDto) throws IOException {
        System.out.println("THE IMAGE SIZE IS: " + productCreateDto.getImage().getSize());

        boolean allowedAddNewProd = productService.isAllowedAddNewProd(productCreateDto);
        if(allowedAddNewProd){
            return new ResponseEntity<>(HttpStatus.CONFLICT); //catch in js 409 to say ProductEntity with this name and brand already in use, do you want to add it anyway?
        }

        Optional<ProductEntity> product  = productService.saveProduct(productCreateDto);
        if(product.isEmpty()){
            return new ResponseEntity("No productEntity created", HttpStatus.BAD_GATEWAY);//error 502 for some reason productEntity is not saved, try again
        }
        return new ResponseEntity(this.productMapper.buildProduct(product.get()), HttpStatus.OK);
    }

    @GetMapping(value = "/image/{productId}")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getImageDataFromProduct(@PathVariable String productId, HttpServletRequest request) throws MalformedURLException {
        Long parsedID = 0L;
        try{
            parsedID = Long.parseLong(productId);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //eror 406 couldnt find productEntity
        }
        Optional<ProductEntity> foundProduct = productService.findProductById(parsedID);
        if(foundProduct.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //eror 404 couldnt find productEntity
        }

        Path userImageDirectory = Paths.get("storage", foundProduct.get().getImage());
        Resource resource = new UrlResource(userImageDirectory.toUri());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        if (contentType == null) {// Fallback to the default content type if type could not be determined
            contentType = "application/octet-stream";//binary data == octet-stream
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/allCriteria")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getListByCriteria(ProductSearchDto dto, Pageable pageable){
        PageImpl<ProductEntity> resultList = productService.rawSearch(dto, pageable);
        return returnResponseEntity(resultList);
    }

    private ResponseEntity returnResponseEntity(PageImpl<ProductEntity> resultList) {
        if(resultList.getTotalElements() == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);// error 404 not found any productEntities
        }

        List<ProductDto> resultListDto =  resultList.stream()
                .map(p -> this.productMapper.buildProduct(p))
                .collect(Collectors.toList());

        int totalPages = resultList.getTotalPages();
        long totalElements = resultList.getTotalElements();
        int size1 = resultList.getSize();
        return new ResponseEntity(new PageDto<>(
                resultListDto,
                totalPages,
                totalElements,
                size1), HttpStatus.OK
        );
    }

    @GetMapping("/inWord/{word}")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getListByWord(
            @Valid @Size(min = 3) @PathVariable String word,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "2") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        PageImpl<ProductEntity> resultList = productService.searchInWord(word, pageable);
        return returnResponseEntity(resultList);
    }

    @GetMapping("/detail/{productId}")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getProductID(
            @PathVariable String productId
    ) throws IOException {
        Long id = 0L;
        try{
            id = Long.parseLong(productId);
        } catch (Exception e){
            e.getMessage();
        }
        Optional<ProductEntity> product = productService.getProduct(id);
        if(!product.isPresent()){
            return new ResponseEntity("Could not find this productEntity", HttpStatus.NOT_FOUND); //404 error
        }
        return new ResponseEntity(this.productMapper.buildProduct(product.get()), HttpStatus.OK);
    }
}