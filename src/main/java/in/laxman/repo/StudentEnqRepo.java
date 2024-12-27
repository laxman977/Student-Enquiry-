package in.laxman.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.laxman.Entity.StudentEnq;

public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer> {
	public List<StudentEnq> findByCid(Integer cid);
}
