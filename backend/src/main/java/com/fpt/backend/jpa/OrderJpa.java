package com.fpt.backend.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// import com.fpt.backend.bean.ProductSellBean;
import com.fpt.backend.entity.Order;

public interface OrderJpa extends JpaRepository<Order, Integer> {

      @Query("""
                      SELECT o FROM Order o
                      WHERE o.user.id = :userId
                        AND (:startDate IS NULL OR o.createAt >= :startDate)
                        AND (:endDate IS NULL OR o.createAt <= :endDate)
                  """)
      Page<Order> findByUserIdAndCreateAtRange(
                  @Param("userId") int userId,
                  @Param("startDate") Date startDate,
                  @Param("endDate") Date endDate, Pageable pageable);

      @Query("""
                      SELECT COUNT(o) FROM Order o
                      WHERE o.user.id = :userId
                        AND (:startDate IS NULL OR o.createAt >= :startDate)
                        AND (:endDate IS NULL OR o.createAt <= :endDate)
                  """)
      int countByUserIdAndCreateAtRange(
                  @Param("userId") int userId,
                  @Param("startDate") Date startDate,
                  @Param("endDate") Date endDate);


      @Query("""
                      SELECT o FROM Order o
                      WHERE (:startDate IS NULL OR o.createAt >= :startDate)
                        AND (:endDate IS NULL OR o.createAt <= :endDate)
                  """)
      Page<Order> findByCreateAtRange(

                  @Param("startDate") Date startDate,
                  @Param("endDate") Date endDate, Pageable pageable);

      @Query("""
                      SELECT COUNT(o) FROM Order o
                      WHERE
                         (:startDate IS NULL OR o.createAt >= :startDate)
                        AND (:endDate IS NULL OR o.createAt <= :endDate)
                  """)
      int countByCreateAtRange(
                  @Param("startDate") Date startDate,
                  @Param("endDate") Date endDate);

      @Query("""
                       SELECT o FROM Order o
                      WHERE o.user.id = :userId
                  """)
      List<Order> findByUserId(@Param("userId") int userId);

      @Query("""
                       SELECT o FROM Order o
                      WHERE o.user.id = :userId AND o.id=:id
                  """)
      Optional<Order> findByOrderUserId(@Param("userId") int userId, @Param("id") int id);

      @Query("""
                      SELECT o FROM Order o
                      WHERE
                            (:startDate IS NULL OR o.createAt >= :startDate)
                        AND (:endDate IS NULL OR o.createAt <= :endDate)
                  """)
      List<Order> findByCreateAt(
                  @Param("startDate") Date startDate,
                  @Param("endDate") Date endDate);

      // @Query("""
      // SELECT new com.fpt.backend.bean.ProductSellBean(
      // oi.product.id,
      // oi.product.name,
      // SUM(oi.quantity)
      // )
      // FROM Order o
      // JOIN o.orderItems oi
      // WHERE (:startDate IS NULL OR o.createAt >= :startDate)
      // AND (:endDate IS NULL OR o.createAt <= :endDate)
      // GROUP BY oi.product.id, oi.product.name
      // ORDER BY SUM(oi.quantity) DESC
      // """)
      // List<ProductSellBean> getTop5ProductSell(@Param("startDate") Date startDate,
      // @Param("endDate") Date endDate);

}
