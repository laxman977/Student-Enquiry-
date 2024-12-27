package in.laxman.service;

import java.util.List;

import in.laxman.Entity.StudentEnq;
import in.laxman.binding.SearchCriteria;

public interface EnquiryService {

	public boolean addEnq(StudentEnq se);
	public List<StudentEnq> getEnquiries(Integer cid , SearchCriteria s);
}
