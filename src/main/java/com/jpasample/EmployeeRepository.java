package com.jpasample;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * DB操作インタフェースクラス。
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * 検索する名前にLIKE一致するEmployeeデータ一覧を取得する。  
	 * @param searchname
	 * @return
	 */
	@Query("SELECT e FROM Employee e WHERE e.name LIKE %:search_name%")
	List<Employee> findEmployee(@Param("search_name") String searchname);

	/**
	 * 検索する名前にLIKE一致するEmployeeデータ一覧を取得する。  
	 * @param searchname
	 * @return
	 */
	@Query(value = 
			"SELECT e.empno,e.empname,e.tel,ifnull(e2.section_id,0) AS section_id " +
			"FROM employee e LEFT JOIN employee2 e2 ON e2.empno = e.empno " + 
			"WHERE e.empname LIKE %:search_name%",
		   countQuery=
			"SELECT COUNT(*) " + 
			"FROM employee e LEFT JOIN employee2 e2 ON e2.empno = e.empno " + 
			"WHERE e.empname LIKE %:search_name%",
		   nativeQuery=true)
	Page<Employee> findEmployee(@Param("search_name") String searchname, Pageable pageable);

}