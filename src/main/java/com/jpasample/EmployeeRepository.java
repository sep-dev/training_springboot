package com.jpasample;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}