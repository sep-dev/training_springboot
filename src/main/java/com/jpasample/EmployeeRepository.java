package com.jpasample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DBから取得したデータを Employee クラスオブジェクトに格納してコントローラに渡すためのインタフェースクラス
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
