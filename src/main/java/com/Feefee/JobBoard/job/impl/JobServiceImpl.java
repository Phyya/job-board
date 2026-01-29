package com.Feefee.JobBoard.job.impl;

import com.Feefee.JobBoard.job.Job;
import com.Feefee.JobBoard.job.JobRepository;
import com.Feefee.JobBoard.job.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
//
//        jobs.add(job);
        jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id) {
//       for(Job job:jobs){
//           if(job.getId().equals(id)) {
//               return job;
//           }
//       }
        return jobRepository.findById(id).orElse(null);


    }
    @Override
    public Boolean deleteJobById(Long id) {
//      Iterator<Job> iterator = jobs.iterator();
//      while(iterator.hasNext()){
//          Job job = iterator.next();
//          if(job.getId().equals(id)){
//              iterator.remove();
//                      return true;
//          }
//      }
//      return false;
try{
        jobRepository.deleteById(id);
return true;
} catch (Exception e) {
return false;
}
    }
    @Override
    public Boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            return true;
        }


      return false;
    }
}
