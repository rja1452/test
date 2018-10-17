package study.spring.helper;

import java.util.regex.Pattern;

public class RegexHelper {
	
	/**
	 * 주어�? 문자?��?�� 공백?��거나 null?���?�? �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - 공백,null?�� ?��?�� 경우 true 리턴
	 */
	public boolean isValue(String str) {
		boolean result = false;
		if (str != null) {
			result = !str.trim().equals("");
		}
		return result;
	}

	/**
	 * ?��?�� 모양?�� ???�� ?��?�� �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?��문으로만 구성?��?��?���??�� ???�� ?��?�� �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isEng(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z]*$", str);
		}
		return result;
	}

	/**
	 * ?���?로만 구성?��?��?���??�� ???�� ?��?�� �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isKor(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?��-?���?-?��]*$", str);
		}
		return result;
	}

	/**
	 * ?��문과 ?��?��로만 구성?��?��?���??�� ???�� ?��?�� �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isEngNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?���?�? ?��?��로만 구성?��?��?���??�� ???�� ?��?�� �??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isKorNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?��-?���?-?��0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?��메일 ?��?��?���??�� ???�� �??��.
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isEmail(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", str);
		}
		return result;
	}

	/**
	 * "-"?��?�� ?��?��?��번호?���??�� ???�� ?��?���??��.
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isCellPhone(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", str);
		}
		return result;
	}

	/**
	 * "-"?��?�� ?��?��번호?���??�� ???�� ?��?���??��.
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isTel(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", str);
		}
		return result;
	}
	
	/**
	 * "-"?��?�� 주�?�번?��?�� ???�� ?��?���??��
	 * 
	 * @param str
	 *            - �??��?�� 문자?��
	 * @return boolean - ?��?��?�� 맞을 경우 true, 맞�? ?��?�� 경우 false
	 */
	public boolean isJumin(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{6}[1-4]\\d{6}", str);
		}
		return result;
	}
}
