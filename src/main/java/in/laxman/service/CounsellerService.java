package in.laxman.service;

import in.laxman.Entity.Counsellor;
import in.laxman.binding.DashoardResponse;


public interface CounsellerService {

	public String saveCounsellor(Counsellor c);
	public Counsellor loginCheck(String email,String pwd);
	public boolean recoverPwd(String email);
	public DashoardResponse getDashboardinfo(Integer cid);
}
