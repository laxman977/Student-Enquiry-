package in.laxman.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.laxman.Entity.Counsellor;
import in.laxman.Entity.StudentEnq;
import in.laxman.binding.DashoardResponse;
import in.laxman.repo.CounsellorRepo;
import in.laxman.repo.StudentEnqRepo;
import in.laxman.util.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellerService {

	@Autowired
	private CounsellorRepo crepo;

	@Autowired
	private StudentEnqRepo srepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String saveCounsellor(Counsellor c) {

		// Verify duplicate email
		Counsellor obj = crepo.findByEmail(c.getEmail());
		if (obj != null) {
			return "Duplicate Email";
		}

		Counsellor saveObj = crepo.save(c);
		if (saveObj.getCid() != null) {
			return "Registration Sucsess";
		}
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		return crepo.findByEmailAndPwd(email, pwd);

	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor c = crepo.findByEmail(email);

		if (c == null) {
			return false;
		}
		String subject = "Recovery Passowrd -- Laxman";
		String body = "<h1>Your Password: " + c.getPwd() + "</h>";
		return emailUtils.senderEmail(subject, body, email);

	}

	@Override
	public DashoardResponse getDashboardinfo(Integer cid) {

		List<StudentEnq> allEnqs = srepo.findByCid(cid);
		int enrolledEnqs = allEnqs.stream().filter(e -> e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList()).size();

		DashoardResponse resp = new DashoardResponse();

		resp.setTotalEnq(allEnqs.size());
		resp.setEnrolledEnq(enrolledEnqs);
		resp.setLostEnq(allEnqs.size() - enrolledEnqs);
		
		return resp;
	}

}
