package com.Feefee.JobBoard.reviews.impl;

import com.Feefee.JobBoard.companies.Company;
import com.Feefee.JobBoard.companies.CompanyService;
import com.Feefee.JobBoard.reviews.Review;
import com.Feefee.JobBoard.reviews.ReviewRepository;
import com.Feefee.JobBoard.reviews.ReviewService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
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
    return reviewRepository
            .findByIdAndCompanyId(reviewId, companyId)
            .orElseThrow(() -> new EntityNotFoundException("Review not found"));
  }
  public boolean updateReview(Long companyId,  Long reviewId, Review updatedReview) {
    Review review = reviewRepository.findByIdAndCompany_Id(reviewId,companyId);
    if(review != null) {
      review.setRating(updatedReview.getRating());
      review.setDescription(updatedReview.getDescription());
      review.setTitle(updatedReview.getTitle());
      reviewRepository.save(review);
      return true;

    }

    return false;
  }
  public boolean deleteReview(Long companyId, Long reviewId) {
    Company company = companyService.getCompanyById(companyId);
    if (company == null) return false;

    Review review = reviewRepository.findById(reviewId).orElse(null);
    if (review == null || !review.getCompany().getId().equals(companyId)) return false;

    // Remove review from the company's list
    company.getReviews().remove(review);
    companyService.updateCompany(company, companyId);

    // Delete the review from DB
    reviewRepository.delete(review);

    return true;
  }
}
