package com.jpasample;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    private Page<Employee> getAll(Pageable pageable) {
        return empRepo.findAll(pageable);
    }
    
    /**
     * 社員名検索で社員一覧を取得。
     * @param searchname
     * @param pageable
     * @return
     */
    public Page<Employee> getSearch(String searchname, Pageable pageable) {

    	// 検索ワードが無い場合は全件対象
    	if (searchname.equals("")) {
    		return getAll(pageable);
    	}
    	
    	// 検索で取得した社員Listをページング対応するためPageオブジェクトに変換。
        List<Employee> searchList = empRepo.findEmployee(searchname);

        // 現在のページに該当する社員Listを取得
		List<Employee> empList;
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		if (searchList.size() < startItem) {
            empList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, searchList.size());
            empList = searchList.subList(startItem, toIndex);
        }

        return 
        	new PageImpl<Employee>(empList, PageRequest.of(currentPage, pageSize), searchList.size());
    }
    
    /**
     * 特定の社員データを取得。
     * @param id
     * @return
     */
    public Employee getOne(Long id) {
    	Employee emp = empRepo.getOne(id);
    	if (emp == null) {
    		// 社員データが取得できなかった際のエラー処理
    	}
    	
    	return emp;
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