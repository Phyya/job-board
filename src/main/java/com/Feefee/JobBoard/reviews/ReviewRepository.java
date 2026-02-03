package com.Feefee.JobBoard.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByCompanyId(Long companyId);
  Optional<Review> findByIdAndCompanyId(Long reviewId, Long companyId);

  Review findByIdAndCompany_Id(Long id, Long companyId);
}
