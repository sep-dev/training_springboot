package com.jpasample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DB操作インタフェースクラス。
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
