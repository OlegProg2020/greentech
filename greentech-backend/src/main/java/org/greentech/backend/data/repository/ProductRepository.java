package org.greentech.backend.data.repository;

import org.greentech.backend.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameLikeIgnoreCaseAndArticleLikeIgnoreCase(String name, String article, Pageable pageable);

    List<Product> findAllByArticle(String article);
}