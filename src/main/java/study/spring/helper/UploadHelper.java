package study.spring.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class UploadHelper {
	
	/** ���ε� �� ������� ����� ���� */
	public String fileDir = null;
	/** ���ε尡 ����� �ӽ� ���� */
	public String tempDir = null;
	
	public UploadHelper(String homeDir) {
		this.fileDir = homeDir + "/upload";
		this.tempDir = fileDir + "/temp";
	}

	/** File������ �����ϱ� ���� �÷��� */
	private List<FileInfo> fileList;

	/** �� ���� �Ϲ� �����͸� �����ϱ� ���� �÷��� */
	private Map<String, String> paramMap;

	/** ���ε�� ������ ����Ʈ�� �����Ѵ�. */
	public List<FileInfo> getFileList() {
		return this.fileList;
	}

	/** ���ε�ÿ� �Բ� ���޵� �Ķ���͵��� �÷����� �����Ѵ�. */
	public Map<String, String> getParamMap() {
		return this.paramMap;
	}

	/**
	 * Multipart�� ���۵� �����͸� �Ǻ��Ͽ� ���ϸ���Ʈ�� �ؽ�Ʈ �Ķ���͸� �з��Ѵ�.
	 * @throws Exception
	 */
	public void multipartRequest() throws Exception {
		/** JSP ���尴ü�� ��� �ִ� Spring�� ��ü�� ���ؼ� ���尴ü ȹ���ϱ� */
		// --> import org.springframework.web.context.request.RequestContextHolder;
		// --> import org.springframework.web.context.request.ServletRequestAttributes;
		ServletRequestAttributes requestAttr 
			= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();
		
		/** multipart�� ���۵Ǿ����� ���� �˻� */
		// --> import org.apache.commons.fileupload.servlet.ServletFileUpload
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			// ���۵� �����Ͱ� �����Ƿ� ���� ���� �߻�.
			throw new Exception();
		}

		/** ������ ���� ���� üũ�ؼ� �����ϱ� */
		// import java.io.File
		File uploadDirFile = new File(fileDir);
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}

		File tempDirFile = new File(tempDir);
		if (!tempDirFile.exists()) {
			tempDirFile.mkdirs();
		}

		/** ���ε尡 ����� �ӽ� ���� ���� */
		// --> import org.apache.commons.fileupload.disk.DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(tempDirFile);

		/** ���ε� ���� */
		ServletFileUpload upload = new ServletFileUpload(factory);
		// UTF-8 ó�� ����
		upload.setHeaderEncoding("UTF-8");
		// �ִ� ���� ũ�� --> 20M
		upload.setSizeMax(20 * 1024 * 1024);
		// ���� ���ε带 �����Ͽ� ���� �� �Ķ���͵��� ���
		List<FileItem> items = upload.parseRequest(request);

		// items�� ���� �����Ͱ� �з��� �÷��ǵ� �Ҵ��ϱ�
		fileList = new ArrayList<FileInfo>();
		paramMap = new HashMap<String, String>();

		/** ���ε� �� ������ ���� ó�� */
		for (int i = 0; i < items.size(); i++) {
			// ���۵� ���� �ϳ��� �����Ѵ�.
			// import org.apache.commons.fileupload.FileItem
			FileItem f = items.get(i);

			if (f.isFormField()) {
				/** ���� ������ �����Ͱ� �ƴ� ��� --> paramMap�� ���� �з� */
				String key = f.getFieldName();
				// value�� UTF-8 �������� ����Ѵ�.
				String value = f.getString("UTF-8");

				// �̹� ������ Ű���� map�ȿ� �����Ѵٸ�? --> checkbox
				if (paramMap.containsKey(key)) {
					// ������ �� �ڿ� �޸�(,)�� �߰��ؼ� ���� �����Ѵ�.
					String new_value = paramMap.get(key) + "," + value;
					paramMap.put(key, new_value);
				} else {
					// �׷��� �ʴٸ� Ű�� ���� �űԷ� �߰��Ѵ�.
					paramMap.put(key, value);
				}

			} else {
				/** ���� ������ �������� ��� --> fileList�� ���� �з� */
				
				/** 1) ������ ������ �����Ѵ� */
				String orginName = f.getName(); 		// ������ ���� �̸�
				String contentType = f.getContentType();// ���� ����
				long fileSize = f.getSize(); 			// ���� ������

				// ���� ����� ���ٸ� �������� ���ư���.
				if (fileSize < 1) {
					continue;
				}

				// �����̸����� Ȯ���ڸ� ����
				String ext = orginName.substring(orginName.lastIndexOf("."));

				/** 2) ������ �̸��� ������ �����ϴ��� �˻��Ѵ�. */
				// �� ������ ����� �̸��� "������ Timestamp+Ȯ����(ext)"�� ���� (�ߺ����� ���)
				String fileName = System.currentTimeMillis() + ext;
				// ����� ���� ������ ��� ���� File��ü
				File uploadFile = null;
				// �ߺ��� �̸��� ������ ������ ��� index���� 1�� �����ϸ鼭 �ڿ� �����δ�.
				int index = 0;
				
				// �ϴ� ���ѷ���
				while (true) {
					// ���ε� ������ ����� ���� + �����̸����� ���ϰ�ü�� �����Ѵ�.
					uploadFile = new File(uploadDirFile, fileName);

					// ������ �̸��� ������ ���ٸ� �ݺ� �ߴ�.
					if (!uploadFile.exists()) {
						break;
					}

					// �׷��� �ʴٸ� �����̸��� index���� �����Ͽ� �̸� ����
					fileName = System.currentTimeMillis() + (++index) + ext;
				} // end while

				// ���������� ������ ���ϰ�ü�� ����ؼ�
				// �ӽ� ������ �����ϴ� ������ ������ ������ �����ϰ�, �ӽ����� ����
				f.write(uploadFile);
				f.delete();

				/** 3) ���� ���� �з� ó�� */
				// ������ ������ Beans�� ��ü�� �����ؼ� �÷��ǿ� �����Ѵ�.
				// --> �� ������ ���� ������ ���ε� ������ DB�� ������ �� ���ȴ�.
				FileInfo info = new FileInfo();
				info.setOrginName(orginName);
				info.setFileDir(fileDir);
				info.setFileName(fileName);
				info.setContentType(contentType);
				info.setFileSize(fileSize);

				fileList.add(info);
			} // end if
		} // end for
	}
	
	/**
	 * ������ ����� ������ �о���δ�. �� ������ ���䰴ü(response)�� ����ؼ� ����Ѵ�.  
	 * @param filePath - �������� ���� ���
	 * @param orginName - ���� ���� �̸�
	 * @throws IOException
	 */
	public void printFileStream(String filePath, String orginName) throws IOException {
		/** JSP ���尴ü�� ��� �ִ� Spring�� ��ü�� ���ؼ� ���尴ü ȹ���ϱ� */
		// --> import org.springframework.web.context.request.RequestContextHolder;
		// --> import org.springframework.web.context.request.ServletRequestAttributes;
		ServletRequestAttributes requestAttr 
				= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = requestAttr.getResponse();
		
		/** ������ ���翩�θ� Ȯ���ϰ� ������ ���� �����ϱ� */
		// --> import java.io.File;
		File f = new File(filePath);
		if (!f.exists()) {
			// --> import java.io.FileNotFoundException;
			throw new FileNotFoundException();
		}

		// ������ ũ�� �����ϱ�
		long size = f.length();
		// ������ �����Ǿ� �ִ� ������ �̸� �����ϱ�
		String name = f.getName();
		
		// ���� ���ϸ��� ���޵��� ���� ��� �������� �����̸����� ��ü
		if (orginName == null) {
			orginName = name;
		}
		
		// �������� ��� (���ε� �������� �����ߴ� contentType�� ���� ��)
		// --> import javax.activation.MimetypesFileTypeMap;
		MimetypesFileTypeMap typeMap = new MimetypesFileTypeMap();
		String fileType = typeMap.getContentType(f);

		/** ��Ʈ���� ���� ������ ���̳ʸ� �б� */
		// ���� �б� ��Ʈ���� �����Ѵ�.
		// --> import java.io.InputStream;
		// --> import java.io.FileInputStream;
		InputStream is = new FileInputStream(f);

		// ������ ������ ��� ���� byte �迭
		byte b[] = new byte[(int) size];

		// ���� �б�
		is.read(b);

		/** ���������� �� �޼��带 ȣ���ϴ� �������� ������ �Ϲ� ���Ϸ� �νĽ�Ű�� ���� ó�� */
		// �ٸ� �����Ϳ� ������ �ʵ��� ���䰴ü(response)�� �����Ѵ�.
		response.reset();

		// �������� ���� ����
		response.setHeader("Content-Type", fileType + "; charset=UTF-8");

		// ������ �̸� ���� (���ڵ� �ʿ���)
		// --> import java.net.URLEncoder;
		String encFileName = URLEncoder.encode(orginName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + encFileName + ";");

		// ������ �뷮 ����
		response.setContentLength((int) size);

		/** �о���� ���� ������ ����ϱ� */
		// ��°�ü�� �����ؼ� ������ �����͸� ���� ��Ʈ�ѷ��� ����Ѵ�.
		// --> import java.io.OutputStream;
		OutputStream os = response.getOutputStream();
		os.write(b);
		os.flush();

		/** ��Ʈ���� �ݴ´�. */
		is.close();
		os.close();
	}
	
	/**
	 * ���������� ��ο� �Բ� �̹����� ����,���� ũ�Ⱑ ���޵� ���,
	 * ������ ũ��� ����� �̹����� �����ϰ�, ������ ������� ��½�Ų��.
	 * @param response	- ���䰴ü
	 * @param filePath	- ���� �̹��� ���
	 * @param width		- ���� ũ��
	 * @param height	- ���� ũ��
	 * @throws IOException
	 */
	public void printFileStream(String filePath, 
			int width, int height, boolean crop) throws IOException {
		
		// ������� �����ϰ� ��θ� ���Ϲ޴´�.
		String thumbPath = this.createThumbnail(filePath, width, height, crop);
		
		// ������� ����Ѵ�.
		// --> 	�� �޼��带 ȣ���ϱ� ���ؼ� try~catch�� �䱸������,
		//	   	���� �޼��� ���� throws�� ����߱� ������
		//		����ó���� ���� �޼��带 ȣ���ϴ� ������ �̰��ȴ�.
		this.printFileStream(thumbPath, null);
	}
	
	/**
	 * �������� �� ����� �̹����� �����Ѵ�.
	 * @param loadFile	- ���� ������ ���
	 * @param width		- �ִ� �̹��� ���� ũ��
	 * @param height	- �ִ� �̹��� ���� ũ�� 
	 * @param crop 		- �̹��� ũ�� ��� ����
	 * @return ������ �̹����� ���� ���
	 * @throws IOException
	 */
	public String createThumbnail(String loadFile, int width, int height, boolean crop) 
			throws IOException {
		// ������ ����� �̹����� ��� 
		String saveFile = null;
		
		// ���� ���ϸ��� ����� ���� ��θ� �����Ѵ�.
		File load = new File(loadFile);
		String dirPath = load.getParent();
		String fileName = load.getName();
		
		// ���� �����̸����� �̸��� Ȯ���ڸ� �и��Ѵ�.
		int p = fileName.lastIndexOf(".");
		String name = fileName.substring(0, p);
		String ext = fileName.substring(p+1);
		
		// �����̸��� ��û�� ����� ���ٿ��� ������ ���ϸ��� �����Ѵ�.
		// ex) myphoto.jpg --> myphoto_resize_320x240.jpg
		String prefix = "_resize_";
		if (crop) {
			prefix = "_crop_";
		}
		
		String thumbName = name + prefix + width + "x" + height + "." + ext;
		File f = new File(dirPath, thumbName);
		
		// ������ ����
		saveFile = f.getAbsolutePath();
		
		// �ش� ��ο� �̹����� ���� ��츸 ����
		if (!f.exists()) {
			// ���� �̹��� ��������
			// --> import net.coobird.thumbnailator.Thumbnails;
			// --> import net.coobird.thumbnailator.Thumbnails.Builder;
			Builder<File> builder = Thumbnails.of(loadFile);
			// �̹��� ũ�� ����
			if (crop == true) {
				builder.crop(Positions.CENTER);
			}
			// ����� ������ ����
			builder.size(width, height);
			// ���η� �Կ��� ������ ȸ����Ŵ
			builder.useExifOrientation(true);
			// ������ Ȯ��� ����
			builder.outputFormat(ext);
			// ������ �����̸� ����
			builder.toFile(saveFile);
		}
		
		return saveFile;
	}
	
	/**
	 * ���޵� ��ο� ���� ������ ������ ������ ���
	 * �ش� ���ϰ� ����� �̸��� ���� ������� �ϰ� �����Ѵ�.
	 * @param filePath
	 */
	public void removeFile(String filePath) {
		
		/** 1) ������ ���� ���� �˻� */
		// ��ΰ��� �������� �ʴ´ٸ� ó�� �ߴ�
		if (filePath == null) {
			return;
		}
		
		// �־��� ��ο� ���� ���� ��ü ����
		File file = new File(filePath);
		
		// ������ �����ϴ��� �˻��Ѵ�.
		if (!file.exists()) {
			return;
		}
		
		/** 2) ��ο��� �����̸�(Ȯ���� ����), ������� ���� */
		// ÷�������� �̸����� Ȯ���ڸ� �����ϰ� ����
		String fileName = file.getName();
		final String prefix = fileName.substring(0, fileName.lastIndexOf("."));

		// ������ ����Ǿ� �ִ� ������ ���� ��ü ����
		// --> �� ���� ���� ����� ��ĵ�ؾ� �Ѵ�.
		File dir = file.getParentFile();

		/** 3) ������ ���� �ȿ��� ����� �����̸��� ���� ��� ������ ����� ���� */
		// ���������� ���͸� ��Ģ�� �����Ͽ� ��ġ�ϴ� ��Ģ�� ������ �̸����� �迭�� �޴´�.
		String[] list = dir.list(new FilenameFilter() {
			// dir��ü�� �ǹ��ϴ� ���� ���� ��� ������ �̸���
			// �� �޼��忡�� �����ϰ� �ȴ�.
			// �� �޼��忡���� ���޹��� �̸��� ���ϴ� ��Ģ�� ȣȯ�Ǵ�����
			// �Ǻ��Ͽ� true/false�� �����Ѵ�.
			@Override
			public boolean accept(File dir, String name) {
				// �����̸��� ���������̸��� ���ԵǾ� �ִٸ� true
				// ex) �����̸��� helloworld�� ��� 
				//     helloworld_crop_40x40, helloworld_resize_120x80 ���� �����̸��� ����ȴ�.
				return (name.indexOf(prefix) > -1);
			}
		});

		/** 4) ��ȸ�� ���� ����� ���������� �����Ѵ�. */
		for (int j = 0; j < list.length; j++) {
			File f = new File(dir, list[j]);
			if (f.exists()) {
				f.delete();
			}
		}
	}
}
