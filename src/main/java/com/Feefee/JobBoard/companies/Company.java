package com.Feefee.JobBoard.companies;

import com.Feefee.JobBoard.job.Job;
import com.Feefee.JobBoard.reviews.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "company")
  private List<Job> jobs;

  //  @JsonIgnore
  @OneToMany(mappedBy = "company")
  private List<Review> reviews;

  public Company() {}

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }
}
