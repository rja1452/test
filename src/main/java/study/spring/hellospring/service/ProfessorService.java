package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Professor;

public interface ProfessorService {
	
	public List<Professor> getProfessorList(Professor student) throws Exception;

}
