package in.laxman.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.laxman.Entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor,Integer> {
 public Counsellor findByEmailAndPwd(String email ,String pwd);
 public Counsellor findByEmail(String email);
}