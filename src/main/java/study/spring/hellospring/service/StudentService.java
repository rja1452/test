package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Student;

public interface StudentService {
	public void addStudent(Student student) throws Exception;
	public void editStudent(Student student) throws Exception;
	public void deleteStudent(Student student) throws Exception;
	public Student getStudentItem(Student student) throws Exception;
	public List<Student> getStudentList(Student student) throws Exception;

}
