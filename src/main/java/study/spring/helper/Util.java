package study.spring.helper;

/**
 * 기본?��?�� 공통 기능?��?�� 묶어 ?��?? ?��?��?��
 */
public class Util {

	/**
	 * 범위�? 갖는 ?��?��값을 ?��?��?��?�� 리턴?��?�� 메서?�� 
	 * @param min - 범위 ?��?��?��?�� 최소�?
	 * @param max - 범위 ?��?��?��?�� 최�?�?
	 * @return min~max ?��?��?��?�� ?��?���?
	 */
	public int random(int min, int max) {
		int num = (int) ((Math.random() * (max - min + 1)) + min);
		return num;
	}

	/**
	 * ?��?��?�� 비�?번호�? ?��?��?��?�� 리턴?��?��.
	 * @return String
	 */
	public String getRandomPassword() {
		// 리턴?�� 문자?��
		String password = "";

		// A~Z, a~z, 1~0 
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		// �??��길이
		int words_len = words.length();

		for (int i = 0; i < 8; i++) {
			// ?��?��?�� ?��치에?�� ?�� �??���? 추출?��?��.
			int random = random(0, words_len - 1);
			String c = words.substring(random, random + 1);

			// 추출?�� �??���? 미리 �?비한 �??��?�� 추�??��?��.
			password += c;
		}

		return password;
	}
}
