package com.fpt.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpt.backend.bean.ProductBean;
import com.fpt.backend.entity.AttributeOptionProduct;
import com.fpt.backend.entity.Product;
import com.fpt.backend.jpa.ProductJpa;
import com.fpt.backend.mapper.ProductMapper;
import com.fpt.backend.util.CloudinaryUpload;

@Service
public class ProductService {

    @Autowired
    private ProductJpa productJpa;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AttributeOptionProductService attributeOptionProductService;

    @Autowired
    private AttributeOptionService attributeOptionService;

    public List<Object> getProductSearch(String keyword, Pageable pageable, String option) {
        Sort sort;
        switch (option) {
            case "default":
            default:
                sort = Sort.by(Sort.Direction.DESC, "id");
                break;
            case "desc":
                sort = Sort.by(Sort.Direction.DESC, "price");
                break;
            case "asc":
                sort = Sort.by(Sort.Direction.ASC, "price");
                break;
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Product> products;
        if (keyword == null || keyword.isEmpty()) {
            products = productJpa.findAll(sortedPageable);
        } else {
            products = productJpa.findByKeyword(keyword, sortedPageable);
        }

        return products.stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public int totalItem(String keyword) {
        return productJpa.totalItem(keyword);
    }

    //tìm sản phẩm
    public Product getProductById(Integer id) {
        return productJpa.findById(id).orElse(null);
    }

    public Object getProductDTO(Integer id, String token) {
        Product product = getProductById(id);
        return productMapper.toDTODetail(product, token);
    }

    public int getAllProducts() {
        return productJpa.findAll().size();
    }

    public Product save(Product product) {
        return productJpa.save(product);
    }

    public Product save(ProductBean productBean, Integer id) {
        try {
            Product product;
            boolean isEdit = (id != null);

            if (isEdit) {
                product = getProductById(id);
            } else {
                product = new Product();
                product.setCreateAt(new Date());
            }

            product.setName(productBean.getName());
            product.setPrice(productBean.getPrice());
            product.setWeight(productBean.getWeight());
            product.setQuantity(productBean.getQuantity());
            product.setDescriptions(productBean.getDescription());

            // Xử lý ảnh
            if (productBean.getImage() != null && !productBean.getImage().isEmpty()) {
                String urlImage = CloudinaryUpload.uploadImage(productBean.getImage());
                product.setImage(urlImage);
            } else if (!isEdit || product.getImage() == null) {
                // Nếu là thêm mới hoặc chưa có ảnh thì dùng ảnh mặc định
                product.setImage(
                        "https://images.unsplash.com/photo-1602351447937-745cb720612f?q=80&w=600&h=400&auto=format&fit=crop");
            }

            // Lưu sản phẩm
            Product savedProduct = productJpa.save(product);

            // Cập nhật lại danh sách attributeOptions
            if (isEdit) {
                attributeOptionProductService.deleteByProduct(savedProduct);
            }

            if (productBean.getAttributeOptions() != null) {
                for (Integer optionId : productBean.getAttributeOptions()) {
                    AttributeOptionProduct aop = new AttributeOptionProduct();
                    aop.setProduct(savedProduct);
                    aop.setAttributeOption(attributeOptionService.findById(optionId));
                    attributeOptionProductService.save(aop);
                }
            }

            return savedProduct;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductBean findDTOById(Integer id) {
        Product product = productJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        ProductBean dto = new ProductBean();

        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setWeight(product.getWeight());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescriptions());
        List<Integer> selectedOptionIds = product.getAttributeOptionProducts()
                .stream()
                .sorted((a, b) -> Integer.compare(
                        b.getAttributeOption().getAttribute().getId(),
                        a.getAttributeOption().getAttribute().getId()))
                .map(aop -> aop.getAttributeOption().getId())
                .collect(Collectors.toList());

        dto.setAttributeOptions(selectedOptionIds);
        return dto;
    }

    public boolean delete(int id) {
        Product product = getProductById(id);
        if (product == null) {
            return false;
        }
        if (product.getCartItems().size() > 0 || product.getOrderItems().size() > 0
                || product.getFavorites().size() > 0) {
            return false;
        }
        attributeOptionProductService.deleteByProduct(product);
        productJpa.delete(product);
        return true;
    }

}
