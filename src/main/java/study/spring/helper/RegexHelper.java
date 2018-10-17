package study.spring.helper;

import java.util.regex.Pattern;

public class RegexHelper {
	
	/**
	 * ì£¼ì–´ì§? ë¬¸ì?—´?´ ê³µë°±?´ê±°ë‚˜ null?¸ì§?ë¥? ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ê³µë°±,null?´ ?•„?‹ ê²½ìš° true ë¦¬í„´
	 */
	public boolean isValue(String str) {
		boolean result = false;
		if (str != null) {
			result = !str.trim().equals("");
		}
		return result;
	}

	/**
	 * ?ˆ«? ëª¨ì–‘?— ???•œ ?˜•?‹ ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?˜ë¬¸ìœ¼ë¡œë§Œ êµ¬ì„±?˜?—ˆ?Š”ì§??— ???•œ ?˜•?‹ ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isEng(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z]*$", str);
		}
		return result;
	}

	/**
	 * ?•œê¸?ë¡œë§Œ êµ¬ì„±?˜?—ˆ?Š”ì§??— ???•œ ?˜•?‹ ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isKor(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?„±-?…ê°?-?£]*$", str);
		}
		return result;
	}

	/**
	 * ?˜ë¬¸ê³¼ ?ˆ«?ë¡œë§Œ êµ¬ì„±?˜?—ˆ?Š”ì§??— ???•œ ?˜•?‹ ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isEngNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?•œê¸?ê³? ?ˆ«?ë¡œë§Œ êµ¬ì„±?˜?—ˆ?Š”ì§??— ???•œ ?˜•?‹ ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isKorNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?„±-?…ê°?-?£0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?´ë©”ì¼ ?˜•?‹?¸ì§??— ???•œ ê²??‚¬.
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isEmail(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", str);
		}
		return result;
	}

	/**
	 * "-"?—†?´ ?•¸?“œ?°ë²ˆí˜¸?¸ì§??— ???•œ ?˜•?‹ê²??‚¬.
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isCellPhone(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", str);
		}
		return result;
	}

	/**
	 * "-"?—†?´ ? „?™”ë²ˆí˜¸?¸ì§??— ???•œ ?˜•?‹ê²??‚¬.
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isTel(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", str);
		}
		return result;
	}
	
	/**
	 * "-"?—†?´ ì£¼ë?¼ë²ˆ?˜¸?— ???•œ ?˜•?‹ê²??‚¬
	 * 
	 * @param str
	 *            - ê²??‚¬?•  ë¬¸ì?—´
	 * @return boolean - ?˜•?‹?— ë§ì„ ê²½ìš° true, ë§ì? ?•Š?„ ê²½ìš° false
	 */
	public boolean isJumin(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{6}[1-4]\\d{6}", str);
		}
		return result;
	}
}
