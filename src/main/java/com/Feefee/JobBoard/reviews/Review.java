package com.Feefee.JobBoard.reviews;

import com.Feefee.JobBoard.companies.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
// @Table("review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private String title;
  private String description;
  private double rating;
  @JsonIgnore @ManyToOne private Company company;

  public Review() {}

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
