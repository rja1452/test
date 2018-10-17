package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.Professor;
import study.spring.hellospring.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Professor> getProfessorList(Professor professor) throws Exception {
		List<Professor> result = null;
		
		try {
			result = sqlSession.selectList("ProfessorMapper.selectProfessorList", professor);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("��ȸ�� �����Ͱ� �����ϴ�.");
		} catch (Exception e) {
			throw new Exception("������ ��ȸ�� �����߽��ϴ�.");
		}
		
		return result;
	}

}
