package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.Student;
import study.spring.hellospring.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	SqlSession sqlSession;
	 
	@Override
	public void addStudent(Student student) throws Exception {
		try {
			int result = sqlSession.insert("StudentMapper.insertStudentItem", student);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("����� �����Ͱ� �����ϴ�.");
		} catch (Exception e) {
			throw new Exception("������ ���忡 �����߽��ϴ�.");
		}
		
	}
	@Override
	public void editStudent(Student student) throws Exception {
		try {
			int result = sqlSession.update("StudentMapper.updateStudentItem", student);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("����� �����Ͱ� �����ϴ�.");
		} catch (Exception e) {
			throw new Exception("������ ������ �����߽��ϴ�.");
		}
		
	}
	@Override
	public void deleteStudent(Student student) throws Exception {
		try {
			int result = sqlSession.delete("StudentMapper.deleteStudentItem", student);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("������ �����Ͱ� �����ϴ�.");
		} catch (Exception e) {
			throw new Exception("������ ������ �����߽��ϴ�.");
		}
		
	}
	@Override
	public Student getStudentItem(Student student) throws Exception {
		Student result = null;
		
		try {
			result = sqlSession.selectOne("StudentMapper.selectSrudentItem", student);
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
	@Override
	public List<Student> getStudentList(Student student) throws Exception {
		List<Student> result = null;
		
		try {
			result = sqlSession.selectList("StudentMapper.selectStudentList", student);
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
