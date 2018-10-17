package study.spring.helper;

/**
 * κΈ°λ³Έ? ?Έ κ³΅ν΅ κΈ°λ₯?€? λ¬Άμ΄ ??? ?΄??€
 */
public class Util {

	/**
	 * λ²μλ₯? κ°λ ??€κ°μ ??±??¬ λ¦¬ν΄?? λ©μ? 
	 * @param min - λ²μ ???? μ΅μκ°?
	 * @param max - λ²μ ???? μ΅λ?κ°?
	 * @return min~max ???? ??€κ°?
	 */
	public int random(int min, int max) {
		int num = (int) ((Math.random() * (max - min + 1)) + min);
		return num;
	}

	/**
	 * ??€? λΉλ?λ²νΈλ₯? ??±??¬ λ¦¬ν΄??€.
	 * @return String
	 */
	public String getRandomPassword() {
		// λ¦¬ν΄?  λ¬Έμ?΄
		String password = "";

		// A~Z, a~z, 1~0 
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		// κΈ??κΈΈμ΄
		int words_len = words.length();

		for (int i = 0; i < 8; i++) {
			// ??€? ?μΉμ? ? κΈ??λ₯? μΆμΆ??€.
			int random = random(0, words_len - 1);
			String c = words.substring(random, random + 1);

			// μΆμΆ? κΈ??λ₯? λ―Έλ¦¬ μ€?λΉν λ³??? μΆκ???€.
			password += c;
		}

		return password;
	}
}
