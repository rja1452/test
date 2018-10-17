package study.spring.helper;

/**
 * ê¸°ë³¸? ?¸ ê³µí†µ ê¸°ëŠ¥?“¤?„ ë¬¶ì–´ ?†“?? ?´?˜?Š¤
 */
public class Util {

	/**
	 * ë²”ìœ„ë¥? ê°–ëŠ” ?œ?¤ê°’ì„ ?ƒ?„±?•˜?—¬ ë¦¬í„´?•˜?Š” ë©”ì„œ?“œ 
	 * @param min - ë²”ìœ„ ?•ˆ?—?„œ?˜ ìµœì†Œê°?
	 * @param max - ë²”ìœ„ ?•ˆ?—?„œ?˜ ìµœë?ê°?
	 * @return min~max ?•ˆ?—?„œ?˜ ?œ?¤ê°?
	 */
	public int random(int min, int max) {
		int num = (int) ((Math.random() * (max - min + 1)) + min);
		return num;
	}

	/**
	 * ?œ?¤?•œ ë¹„ë?ë²ˆí˜¸ë¥? ?ƒ?„±?•˜?—¬ ë¦¬í„´?•œ?‹¤.
	 * @return String
	 */
	public String getRandomPassword() {
		// ë¦¬í„´?•  ë¬¸ì?—´
		String password = "";

		// A~Z, a~z, 1~0 
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		// ê¸??ê¸¸ì´
		int words_len = words.length();

		for (int i = 0; i < 8; i++) {
			// ?œ?¤?•œ ?œ„ì¹˜ì—?„œ ?•œ ê¸??ë¥? ì¶”ì¶œ?•œ?‹¤.
			int random = random(0, words_len - 1);
			String c = words.substring(random, random + 1);

			// ì¶”ì¶œ?•œ ê¸??ë¥? ë¯¸ë¦¬ ì¤?ë¹„í•œ ë³??ˆ˜?— ì¶”ê??•œ?‹¤.
			password += c;
		}

		return password;
	}
}
