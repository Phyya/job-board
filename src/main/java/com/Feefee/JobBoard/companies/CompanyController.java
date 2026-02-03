package com.Feefee.JobBoard.companies;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@Tag(name = "Companies", description = "Endpoints for managing companies")

public class CompanyController {
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  @Operation(
          summary = "Get all companies",
          description = "Fetches all companies in the system"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Successfully retrieved companies"),
          @ApiResponse(responseCode = "404", description = "No company found")
  })
  public ResponseEntity<List<Company>> getAllCompanies() {
    return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
    Company company = companyService.getCompanyById(id);
    if (company != null) {

      return new ResponseEntity<>(company, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<String> createCompany(@RequestBody Company newCompany) {
    try {

      companyService.createCompany(newCompany);
      return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);

    } catch (Exception e) {
      System.out.println(e + "The reason why it failed to create");
      return new ResponseEntity<>("Failed to create a new company", HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(
      @PathVariable Long id, @RequestBody Company newCompany) {
    try {

      boolean isUpdated = companyService.updateCompany(newCompany, id);
      if (isUpdated) {

        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Failed to update company", HttpStatus.BAD_REQUEST);
      }

    } catch (Exception e) {
      return new ResponseEntity<>("Failed to update company", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
    try {

      boolean isDeleted = companyService.deleteCompany(id);
      if (isDeleted) {
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);

      } else {
        return new ResponseEntity<>("Failed to delete company", HttpStatus.BAD_REQUEST);
      }

    } catch (Exception e) {
      return new ResponseEntity<>("Failed to delete company", HttpStatus.BAD_REQUEST);
    }
  }
}
