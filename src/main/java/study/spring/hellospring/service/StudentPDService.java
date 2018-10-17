package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.StudentPD;

public interface StudentPDService {
	
	public StudentPD getStudentPDItem(StudentPD student) throws Exception;
	
	public List<StudentPD> getStudentPDList(StudentPD student) throws Exception;
	
	public int getStudentPDCount(StudentPD student) throws Exception;

}
