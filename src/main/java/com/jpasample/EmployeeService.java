package com.jpasample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 社員データ更新ビジネスロジック
 *
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    /**
     * 社員一覧をページング単位で取得。
     * @param pageable
     * @return
     */
    public Page<Employee> getAll(Pageable pageable) {
        return empRepo.findAll(pageable);
    }
    
    /**
     * 特定の社員データを取得。
     * @param id
     * @return
     */
    public Employee getOne(Long id) {
    	return empRepo.getOne(id);
    }

    /**
     * 社員データを更新。
     * @param employee
     */
    public void save(Employee employee) {
    	empRepo.save(employee);
    }
    
    /**
     * 社員データを削除。
     * @param id
     */
    public void delete(Long id) {
    	empRepo.deleteById(id);
    }
}