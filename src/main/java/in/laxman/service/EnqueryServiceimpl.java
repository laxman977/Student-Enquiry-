package in.laxman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.laxman.Entity.StudentEnq;
import in.laxman.binding.SearchCriteria;
import in.laxman.repo.StudentEnqRepo;

@Service
public class EnqueryServiceimpl implements EnquiryService {

	@Autowired
	private StudentEnqRepo srepo;
	
	
	@Override
	public boolean addEnq(StudentEnq se) {
	  StudentEnq saveEnq = srepo.save(se);
	  
		return saveEnq.getEnqId()!=null;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {
		StudentEnq enq = new StudentEnq();
		enq.setCid(cid);
		
		// if mode selected then add mode to the query
		if(sc.getClassMode()!=null && !sc.getClass().equals("")) {
			enq.setClassMode(sc.getClassMode());
		}
		if(sc.getCourseName()!=null &&!sc.getCourseName().equals("")){
			enq.setCourseName(sc.getCourseName());
		}
		
		if(sc.getEnqStatus()!=null &&!sc.getEnqStatus().equals("")){
			enq.setEnqStatus(sc.getEnqStatus());
		}
		Example<StudentEnq> of = Example.of(enq);
		
		return  srepo.findAll(of);
	
	}

}
