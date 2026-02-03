package com.Feefee.JobBoard.reviews;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
  private ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<List<Review>> GetAllCompanyReviews(@PathVariable Long companyId) {
    return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> CreateCompanyReview(
      @PathVariable Long companyId, @RequestBody Review review) {
    boolean isReviewSaved = reviewService.addReview(companyId, review);
    if (isReviewSaved) {

      return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<Review> GetAReview(
      @PathVariable Long companyId, @PathVariable Long reviewId) {
    return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
  }
  @PutMapping("/{reviewId}")
  public ResponseEntity<String> UpdateReview(
      @PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
    boolean isReviewSaved = reviewService.updateReview(companyId, reviewId, review);
    if (isReviewSaved) {

      return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Review failed to update", HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/{reviewId}")
  public ResponseEntity<String> DeleteReview(
      @PathVariable Long companyId, @PathVariable Long reviewId) {
    boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
    if (isReviewDeleted) {
      return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Review not found or failed to delete", HttpStatus.NOT_FOUND);
    }
  }
}
