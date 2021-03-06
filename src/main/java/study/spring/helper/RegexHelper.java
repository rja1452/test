package study.spring.helper;

import java.util.regex.Pattern;

public class RegexHelper {
	
	/**
	 * μ£Όμ΄μ§? λ¬Έμ?΄?΄ κ³΅λ°±?΄κ±°λ null?Έμ§?λ₯? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - κ³΅λ°±,null?΄ ?? κ²½μ° true λ¦¬ν΄
	 */
	public boolean isValue(String str) {
		boolean result = false;
		if (str != null) {
			result = !str.trim().equals("");
		}
		return result;
	}

	/**
	 * ?«? λͺ¨μ? ??? ?? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?λ¬ΈμΌλ‘λ§ κ΅¬μ±???μ§?? ??? ?? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isEng(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z]*$", str);
		}
		return result;
	}

	/**
	 * ?κΈ?λ‘λ§ κ΅¬μ±???μ§?? ??? ?? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isKor(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?±-?κ°?-?£]*$", str);
		}
		return result;
	}

	/**
	 * ?λ¬Έκ³Ό ?«?λ‘λ§ κ΅¬μ±???μ§?? ??? ?? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isEngNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[a-zA-Z0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?κΈ?κ³? ?«?λ‘λ§ κ΅¬μ±???μ§?? ??? ?? κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isKorNum(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^[?±-?κ°?-?£0-9]*$", str);
		}
		return result;
	}

	/**
	 * ?΄λ©μΌ ???Έμ§?? ??? κ²??¬.
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isEmail(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", str);
		}
		return result;
	}

	/**
	 * "-"??΄ ?Έ??°λ²νΈ?Έμ§?? ??? ??κ²??¬.
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isCellPhone(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", str);
		}
		return result;
	}

	/**
	 * "-"??΄ ? ?λ²νΈ?Έμ§?? ??? ??κ²??¬.
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isTel(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", str);
		}
		return result;
	}
	
	/**
	 * "-"??΄ μ£Όλ?Όλ²?Έ? ??? ??κ²??¬
	 * 
	 * @param str
	 *            - κ²??¬?  λ¬Έμ?΄
	 * @return boolean - ??? λ§μ κ²½μ° true, λ§μ? ?? κ²½μ° false
	 */
	public boolean isJumin(String str) {
		boolean result = false;
		if (isValue(str)) {
			result = Pattern.matches("^\\d{6}[1-4]\\d{6}", str);
		}
		return result;
	}
}
