package system.domain;

public enum Regex {

	all_type(".smi|.acc|.mp4|.avi|.mkv"),seeder("\\[.*\\]"),resol("\\(.*\\)"),movie_type(".mp4|.avi|.mkv"),jamak_type(".smi|.acc");
	private String regex;

	private Regex(String regex) {
		this.setRegex(regex);
	}

	public String getRegex() {
		return regex;
	}

	private void setRegex(String regex) {
		this.regex = regex;
	}

}
