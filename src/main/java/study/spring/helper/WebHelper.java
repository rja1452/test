package study.spring.helper;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebHelper {
	/** ��Ű���� ����� ������ */
	private static final String DOMAIN = "localhost";

	/** JSP�� request ���� ��ü */
	// --> import javax.servlet.http.HttpServletRequest;
	private HttpServletRequest request;

	/** JSP�� response ���� ��ü */
	// --> import javax.servlet.http.HttpServletResponse;
	private HttpServletResponse response;

	/** JSP�� out ���� ��ü */
	// --> import java.io.PrintWriter;
	private PrintWriter out;

	/** JSP�� session ���� ��ü */
	// --> import javax.servlet.http.HttpSession;
	private HttpSession session;

	/**
	 * WebHelper ����� �ʱ�ȭ �Ѵ�. Spring�� �����ϴ� ServletRequestAttributes ��ü�� ���ؼ�
	 * request, response��ü�� ���� ������ �� �ִ�.
	 */
	public void init() {

		/** JSP ���尴ü�� ��� �ִ� Spring�� ��ü�� ���ؼ� ���尴ü ȹ���ϱ� */
		// --> import
		// org.springframework.web.context.request.RequestContextHolder;
		// --> import
		// org.springframework.web.context.request.ServletRequestAttributes;
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		// request�� response ��ü�� �����Ѵ�.
		this.request = requestAttr.getRequest();
		this.response = requestAttr.getResponse();

		// ���ǰ�ü �����ϱ�
		this.session = request.getSession();

		// ������ �̵� ���� ������ �����Ǵ� �ð� ���� (��)
		// --> 24�ð�
		this.session.setMaxInactiveInterval(60 * 60 * 24);

		/** ���尴ü �ʱ�ȭ -> utf-8 ����, out��ü ���� */
		try {
			// ���ڵ� �����ϱ�
			this.request.setCharacterEncoding("utf-8");
			this.response.setCharacterEncoding("utf-8");
			// out��ü �����ϱ�
			this.out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �޽��� ǥ�� ��, �������� ������ ������ �̵��Ѵ�.
	 * @param url - �̵��� �������� URL, Null�� ��� ������������ �̵�
	 * @param msg - ȭ�鿡 ǥ���� �޽���. Null�� ��� ǥ�� ����
	 */
	// --> import org.springframework.web.servlet.ModelAndView;
	public ModelAndView redirect(String url, String msg) {
		// ������ View�� ����� ���� HTML �±� ����
		String html = "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='utf-8'>";

		// �޽��� ǥ��
		if (msg != null) {
			html += "<script type='text/javascript'>alert('" + msg + "');</script>";
		}

		// ������ �̵�
		if (url != null) {
			html += "<meta http-equiv='refresh' content='0; url=" + url + "' />";
		} else {
			html += "<script type='text/javascript'>history.back();</script>";
		}

		html += "</head>";
		html += "<body></body>";
		html += "</html>";

		// ������ HTML�� ����Ѵ�.
		// out.print(html);

		// �͸�Ŭ���� ����� ����� �ν��� �� �����Ƿ�, HTML�±׸� ����� ����
		final String page_content = html;

		/** ������ View�� �͸� Ŭ���� ������� �����Ͽ� ���� */
		// --> import org.springframework.web.servlet.View;
		// --> import org.springframework.web.servlet.view.AbstractView;
		View view = new AbstractView() {
			@Override
			protected void renderMergedOutputModel(Map<String, Object> map, 
					HttpServletRequest request,HttpServletResponse response) throws Exception {
				out.println(page_content);
				out.flush();
			}
		};

		// ������ �並 �����Ѵ�.
		return new ModelAndView(view);
	}

	/**
	 * �Ķ���͸� ���޹޾Ƽ� �����Ѵ�.
	 * 
	 * @param fieldName
	 *            - �Ķ���� �̸�
	 * @param defaultValue
	 *            - ���� ���� ��� ���� �⺻��
	 * @return String
	 */
	public String getString(String fieldName, String defaultValue) {
		// ������ ���� ���� �� ��° �Ķ����(�⺻��)�� ������ �д�.
		String result = defaultValue;
		// GET,POST �Ķ���͸� �޴´�.
		String param = this.request.getParameter(fieldName);

		if (param != null) { // ���� null�� �ƴ϶��?
			param = param.trim(); // �յ� ������ �����Ѵ�.
			if (!param.equals("")) { // �������� ����� �� ���ڿ��� �ƴ϶��?
				result = param; // ������ ���ؼ� �غ��� ������ ������ ���� �����Ѵ�.
			}
		}

		// ���� ����. param���� �������� ���� ��� �̸� �غ��� �⺻���� �״�� ���ϵȴ�.
		return result;
	}

	/**
	 * �Ķ���͸� ���޹޾Ƽ� int�� ����ȯ �Ͽ� �����Ѵ�.
	 * 
	 * @param fieldName
	 *            - �Ķ���� �̸�
	 * @param defaultValue
	 *            - ���� ���� ��� ���� �⺻��
	 * @return int
	 */
	public int getInt(String fieldName, int defaultValue) {
		// ������ ���� ���� �� ��° �Ķ����(�⺻��)�� ������ �д�.
		int result = defaultValue;
		// getString()�޼��带 ���ؼ� �Ķ���͸� ���ڿ� ���·� �޴´�.
		// �Ķ���Ͱ� �������� �ʴ´ٸ� �� ��°�� ������ ���� ���ϵȴ�.
		String param = this.getString(fieldName, null);

		// �Ķ���ͷ� ���޵� ���� ���ڷ� ����ȯ �Ѵ�.
		try {
			result = Integer.parseInt(param);
		} catch (NumberFormatException e) {
			// ����ȯ�� ������ ��� catch������� ��� �̵��ϰ�,result���� �̸� ������ ��
			// defaultValue�� ���¸� �����Ѵ�.
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * �迭 ������ �Ķ���͸� �����Ѵ�. üũ�ڽ� ���� ���
	 * 
	 * @param fieldName
	 *            - �Ķ���� �̸�
	 * @param defaultValue
	 *            - ���� ���ų� �迭�� ���̰� 0�� ��� ���� �⺻��
	 * @return String[]
	 */
	public String[] getStringArray(String fieldName, String[] defaultValue) {
		// ������ ���� ���� �� ��° �Ķ����(�⺻��)�� ������ �д�.
		String[] result = defaultValue;
		// �迭 ������ GET,POST �Ķ���͸� �޴´�.
		String[] param = this.request.getParameterValues(fieldName);

		if (param != null) { // ���ŵ� �Ķ���Ͱ� �����Ѵٸ�?
			if (param.length > 0) { // �迭�� ���̰� 0���� ũ�ٸ�?
				result = param; // ������ ���ؼ� �غ��� ������ ������ ���� �����Ѵ�.
			}
		}

		// ���� ����. param���� �������� ���� ��� �̸� �غ��� �⺻���� �״�� ���ϵȴ�.
		return result;
	}

	public String getString(String fieldName) {
		return this.getString(fieldName, null);
	}

	public int getInt(String fieldName) {
		return this.getInt(fieldName, 0);
	}

	public String[] getStringArray(String fieldName) {
		return this.getStringArray(fieldName, null);
	}

	/**
	 * ��Ű���� �����Ѵ�.
	 * 
	 * @param key
	 *            - ��Ű�̸�
	 * @param value
	 *            - ��
	 * @param timeout
	 *            - �����ð�. �������� ������ ��� ������ ��� -1
	 */
	public void setCookie(String key, String value, int timeout) {
		/** ���޵� ���� URLEncoding ó�� �Ѵ�. */
		if (value != null) {
			try {
				// import java.net.URLEncoder
				value = URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		/** ��Ű ��ü ���� �� �⺻ ���� */
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setDomain(DOMAIN);

		/** ��ȿ�ð� ���� */
		// �ð����� 0���� ���� ���� �� �޼��带 �������� �ʵ��� �Ѵ�. (�������� ������ ����)
		// 0���� ������ ��� setMaxAge(0)�̶�� �����ǹǷ� ��� �����ȴ�.
		if (timeout > -1) {
			cookie.setMaxAge(timeout);
		}

		/** ��Ű �����ϱ� */
		this.response.addCookie(cookie);
	}

	/**
	 * ��Ű���� ��ȸ�Ѵ�.
	 * 
	 * @param key
	 *            - ��Ű�̸�
	 * @param defaultValue
	 *            - ���� ���� ��� ���� �⺻��
	 * @return String
	 */
	public String getCookie(String key, String defaultValue) {
		/** ������ ���� ���� */
		String result = defaultValue;

		/** ��Ű �迭 �������� */
		// import javax.servlet.http.Cookie
		Cookie[] cookies = this.request.getCookies();

		/** ��Ű�� �ִٸ�? ����� �迭�� �׸� �� ��ŭ �ݺ��ϸ鼭 ���ϴ� �̸��� ���� �˻� */
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				// ��Ű�� �̸� ���
				String cookieName = cookies[i].getName();
				// ���ϴ� �̸��̶��?
				if (cookieName.equals(key)) {
					// ���� ���� --> �� ���� ���ϵȴ�.
					result = cookies[i].getValue();
					try {
						// import java.net.URLDecoder
						result = URLDecoder.decode(result, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					break;
				} // end if
			} // end for
		} // end if

		return result;
	}

	/**
	 * ��Ű���� ��ȸ�Ѵ�. ���� ���� ��� Null�� �����Ѵ�.
	 * 
	 * @param key
	 *            - ��Ű�̸�
	 * @return String
	 */
	public String getCookie(String key) {
		return this.getCookie(key, null);
	}

	/**
	 * ������ Ű�� ���� ��Ű�� �����Ѵ�.
	 * 
	 * @param key
	 */
	public void removeCookie(String key) {
		this.setCookie(key, null, 0);
	}

	/**
	 * ���ǰ��� �����Ѵ�.
	 * 
	 * @param key
	 *            - �����̸�
	 * @param value
	 *            - ������ ������
	 */
	public void setSession(String key, Object value) {
		this.session.setAttribute(key, value);
	}

	/**
	 * ���ǰ��� ��ȸ�Ѵ�.
	 * 
	 * @param key
	 *            - ��ȸ�� ������ �̸�
	 * @param defaultValue
	 *            - ���� ���� ��� ��ü�� �⺻��
	 * @return Object�̹Ƿ� ����� ����ȯ �ʿ���
	 */
	public Object getSession(String key, Object defaultValue) {
		Object value = this.session.getAttribute(key);

		if (value == null) {
			value = defaultValue;
		}

		return value;
	}

	/**
	 * ���ǰ��� ��ȸ�Ѵ�. ���� ���� ��쿡 ���� �⺻���� null�� ����
	 * 
	 * @param key
	 *            - ���� �̸�
	 * @return Object�̹Ƿ� ����� ����ȯ �ʿ���
	 */
	public Object getSession(String key) {
		return this.getSession(key, null);
	}

	/**
	 * Ư�� ���ǰ��� �����Ѵ�.
	 * 
	 * @param key
	 *            - ���� �̸�
	 */
	public void removeSession(String key) {
		this.session.removeAttribute(key);
	}

	/**
	 * ���� ����ڿ� ���� ��� ���ǰ��� �ϰ� �����Ѵ�.
	 */
	public void removeAllSession() {
		this.session.invalidate();
	}

	/**
	 * ���� ������Ʈ�� �ֻ��� ��ΰ��� "/������Ʈ��" �������� �����Ѵ�.
	 * 
	 * @return
	 */
	public String getRootPath() {
		return this.request.getContextPath();
	}

	/**
	 * ���� �����ڿ� ���� IP�ּҸ� ��ȸ�Ͽ� �����Ѵ�.
	 * 
	 * @return String
	 */
	public String getClientIP() {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * ��� �޽����� JSON���� ����Ѵ�. JSON Api���� web.redirect() ����� ��ü�� �뵵.
	 * 
	 * @param rt
	 *            - JSON�� ������ �޽��� ����
	 */
	public void printJsonRt(String rt) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("rt", rt);

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ڿ��� ���Ե� HTML�±׿� �ٹٲ� ���ڸ� HTMLƯ������ ���·� ��ȯ
	 * 
	 * @param content
	 * @return String
	 */
	public String convertHtmlTag(String content) {
		// ���� ����� ������ ��ü
		StringBuilder builder = new StringBuilder();

		// ���ڿ��� ���Ե� �� ����
		char chrBuff;

		// ���� �� ��ŭ �ݺ��Ѵ�.
		for (int i = 0; i < content.length(); i++) {
			// �� ���ڸ� ����
			chrBuff = (char) content.charAt(i);

			// Ư������ ���¿� ������ ��� ��ȯ�Ͽ� builder�� �߰�
			// �׷��� ���� ��� ���� �״�� builder�� �߰�
			switch (chrBuff) {
			case '<':
				builder.append("&lt;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '&':
				builder.append("&amp;");
				break;
			case '\n':
				builder.append("&lt;br/&gt;");
				break;
			default:
				builder.append(chrBuff);
			}
		}

		// ������ ����� ���ڿ��� ��ȯ�ؼ� �����Ѵ�.
		return builder.toString();
	}
}
