package com.Feefee.JobBoard.reviews.impl;

import com.Feefee.JobBoard.companies.Company;
import com.Feefee.JobBoard.companies.CompanyService;
import com.Feefee.JobBoard.reviews.Review;
import com.Feefee.JobBoard.reviews.ReviewRepository;
import com.Feefee.JobBoard.reviews.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
  private ReviewRepository reviewRepository;
  private CompanyService companyService;

  //  private CompanyRepository companyRepository;

  public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
    this.reviewRepository = reviewRepository;
    this.companyService = companyService;
  }

  public List<Review> getAllReviews(Long id) {
    List<Review> reviews = reviewRepository.findByCompanyId((id));
    return reviews;
  }

  public boolean addReview(Long companyId, Review review) {
    Company company = companyService.getCompanyById(companyId);
    if (company != null) {
      review.setCompany(company);
      reviewRepository.save(review);
      return true;
    } else {
      return false;
    }
  }

  public Review getReview(Long companyId, Long reviewId) {
    List<Review> reviews = reviewRepository.findByCompanyId((companyId));
    return reviews.stream()
        .filter(review -> review.getId().equals(reviewId))
        .findFirst()
        .orElse(null);
  }
}
