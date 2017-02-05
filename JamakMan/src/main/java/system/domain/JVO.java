package system.domain;

public class JVO implements VO {

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	private String file_name;
	private String file_path;
	private String file_type;
	private String Ab_file_path;

	@Override
	public void setAbFile_path(String Ab_file_path) {
		this.Ab_file_path = Ab_file_path;
	}

	@Override
	public String getAbFile_path() {
		return Ab_file_path;
	}
}
