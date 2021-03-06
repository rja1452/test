package study.spring.helper;

/**
 * ?λ‘λ ? ??Ό? ? λ³΄λ?? ???₯?κΈ? ?? JavaBeans
 * - ?΄ ?΄??€? κ°μ²΄κ°? ?λ‘λ ? ??Ό? ? λ§νΌ ??±??΄ List ??λ‘? λ³΄κ???€.
 */
public class FileInfo {
	private String orginName; 	// ?λ³? ??Ό ?΄λ¦?
	private String fileDir; 	// ??Ό?΄ ???₯??΄ ?? ?λ²μ? κ²½λ‘
	private String fileName; 	// ?λ²μ? ??Ό ?΄λ¦?
	private String contentType; // ??Ό? ??
	private long fileSize; 		// ??Ό? ?©?

	public String getOrginName() {
		return orginName;
	}

	public void setOrginName(String orginName) {
		this.orginName = orginName;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileInfo [orginName=" + orginName + ", fileDir=" + fileDir + ", fileName="
				+ fileName + ", contentType=" + contentType + ", fileSize=" + fileSize + "]";
	}

}