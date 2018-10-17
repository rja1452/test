package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.Department;
import study.spring.hellospring.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Department> getDepartmentList(Department department) throws Exception {
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartmentList", department);
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

}
