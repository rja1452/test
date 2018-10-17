package study.spring.hellospring;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import study.spring.hellospring.model.Student;
import study.spring.hellospring.service.StudentService;
import study.spring.helper.WebHelper;

@Controller
public class StudentApi {

	private static final Logger logger = LoggerFactory.getLogger(StudentApi.class);

	@Autowired
	WebHelper web;
	
	@Autowired
	StudentService studentService;

	@ResponseBody
	@RequestMapping(value = "student_api/StudentSelectListApi", method = RequestMethod.GET)
	public void StudentSelectListApi(Locale locale, Model model, HttpServletResponse response) {

		web.init();
		response.setContentType("application/json");

		List<Student> item = null;
		try {
			item = studentService.getStudentList(null);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "student_api/StudentSelectItemApi", method = RequestMethod.GET)
	public void StudentSelectItemApi(Locale locale, Model model, HttpServletResponse response) {

		web.init();
		response.setContentType("application/json");

		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if (studno == 0) {
			web.printJsonRt("학생번호가 없습니다.");
			return;
		}

		Student student = new Student();
		student.setStudno(studno);

		Student item = null;
		try {
			item = studentService.getStudentItem(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "student_api/StudentInsertApi", method = RequestMethod.POST)
	public void StudentInsertApi(Locale locale, Model model, HttpServletResponse response) {
		
		web.init();
		response.setContentType("application/json");
		
		String name = web.getString("name");
		String userId = web.getString("user_id");
		int grade = web.getInt("grade");
		String idnum = web.getString("idnum");
		String birthdate = web.getString("birthdate");
		String tel = web.getString("tel");
		int height = web.getInt("height");
		int weight = web.getInt("weight");
		int deptno = web.getInt("deptno");
		int profno = web.getInt("profno");
		
		logger.debug("name=" + name);
		logger.debug("userid=" + userId);
		logger.debug("grade=" + grade);
		logger.debug("idnum=" + idnum);
		logger.debug("birthdate=" + birthdate);
		logger.debug("tel=" + tel);
		logger.debug("height=" + height);
		logger.debug("weight=" + weight);
		logger.debug("deptno=" + deptno);
		logger.debug("profno=" + profno);
		
		if (name == null) 		{ web.printJsonRt("이름을 입력하세요"); 	return; }
		if (userId == null) 	{ web.printJsonRt("아이디를 입력하세요"); 	return; }
		if (grade == 0) 		{ web.printJsonRt("학년을 입력하세요"); 	return; }
		if (idnum == null) 		{ web.printJsonRt("주민번호를 입력하세요"); 	return; }
		if (birthdate == null) 	{ web.printJsonRt("생년월일을 입력하세요"); 	return; }
		if (tel == null) 		{ web.printJsonRt("전화번호을 입력하세요"); 	return; }
		if (height == 0) 		{ web.printJsonRt("키를 입력하세요"); 	return; }
		if (weight == 0) 		{ web.printJsonRt("몸무게를 입력하세요"); 	return; }
		if (deptno == 0) 		{ web.printJsonRt("학과번호를 입력하세요"); return; }
		if (profno == 0) 		{ web.printJsonRt("교수번호를 입력하세요"); 	return; }
		
		Student student = new Student();
		student.setName(name);
		student.setUserid(userId);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		
		try {
			studentService.addStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
	
	@ResponseBody
	@RequestMapping(value = "student_api/StudentDeleteApi", method = RequestMethod.POST)
	public void StudentDeleteApi(Locale locale, Model model, HttpServletResponse response) {
		
		web.init();
		response.setContentType("application/json");
		
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if (studno == 0) 		{
			web.printJsonRt("학생번호가 없습니다.");
			return;
		}
		
		Student student = new Student();
		student.setStudno(studno);
		
		try {
			studentService.deleteStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
	
	@ResponseBody
	@RequestMapping(value = "student_api/StudentEditApi", method = RequestMethod.POST)
	public void StudentEditApi(Locale locale, Model model, HttpServletResponse response) {
		
		web.init();
		response.setContentType("application/json");
		
		int studno = web.getInt("studno");
		String name = web.getString("name");
		String userId = web.getString("user_id");
		int grade = web.getInt("grade");
		String idnum = web.getString("idnum");
		String birthdate = web.getString("birthdate");
		String tel = web.getString("tel");
		int height = web.getInt("height");
		int weight = web.getInt("weight");
		int deptno = web.getInt("deptno");
		int profno = web.getInt("profno");
		
		logger.debug("studno=" + studno);
		logger.debug("name=" + name);
		logger.debug("userid=" + userId);
		logger.debug("grade=" + grade);
		logger.debug("idnum=" + idnum);
		logger.debug("birthdate=" + birthdate);
		logger.debug("tel=" + tel);
		logger.debug("height=" + height);
		logger.debug("weight=" + weight);
		logger.debug("deptno=" + deptno);
		logger.debug("profno=" + profno);

		if (studno == 0) 		{ web.printJsonRt("교수번호가 없습니다."); 	return; }
		if (name == null) 		{ web.printJsonRt("이름을 입력하세요"); 	return; }
		if (userId == null) 	{ web.printJsonRt("아이디를 입력하세요"); 	return; }
		if (grade == 0) 		{ web.printJsonRt("학년을 입력하세요"); 	return; }
		if (idnum == null) 		{ web.printJsonRt("주민번호를 입력하세요"); 	return; }
		if (birthdate == null) 	{ web.printJsonRt("생년월일을 입력하세요"); 	return; }
		if (tel == null) 		{ web.printJsonRt("전화번호을 입력하세요"); 	return; }
		if (height == 0) 		{ web.printJsonRt("키를 입력하세요"); 	return; }
		if (weight == 0) 		{ web.printJsonRt("몸무게를 입력하세요"); 	return; }
		if (deptno == 0) 		{ web.printJsonRt("학과번호를 입력하세요"); return; }
		if (profno == 0) 		{ web.printJsonRt("교수번호를 입력하세요"); 	return; }

		Student student = new Student();
		student.setStudno(studno);
		student.setName(name);
		student.setUserid(userId);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		
		try {
			studentService.editStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
}
