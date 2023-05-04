package in.divaji.Reports_App01.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.divaji.Reports_App01.entity.CitizenPlan;
import in.divaji.Reports_App01.request.SearchRequest;

@Repository
public interface CitizenRepository  extends JpaRepository<CitizenPlan, Integer>{

	
	@Query("SELECT DISTINCT (planeName) FROM CitizenPlan")
	public List<String> getPlaneName();
	
	@Query("SELECT DISTINCT (planeStatus) FROM CitizenPlan")
	public List<String> getplaneStatus();
	

	
}
