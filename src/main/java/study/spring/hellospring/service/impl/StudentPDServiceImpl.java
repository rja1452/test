package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.StudentPD;
import study.spring.hellospring.service.StudentPDService;

@Service
public class StudentPDServiceImpl implements StudentPDService {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public StudentPD getStudentPDItem(StudentPD student) throws Exception {
		StudentPD result = null;

		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudProfDeptItem", student);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public List<StudentPD> getStudentPDList(StudentPD student) throws Exception {
		List<StudentPD> result = null;
		
		try {
			result = sqlSession.selectList("StudentJoinMapper.selectStudentPDList", student);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int getStudentPDCount(StudentPD student) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudentPDCount", student);
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

}