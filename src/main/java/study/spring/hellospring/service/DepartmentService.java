package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Department;

public interface DepartmentService {
	
	public List<Department> getDepartmentList(Department student) throws Exception;

}
