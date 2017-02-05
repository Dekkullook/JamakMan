package system.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import system.domain.MVO;
import system.domain.JVO;
import system.domain.Regex;
import system.domain.VO;

public class Functions {
	final private Pattern file_pattern = Pattern.compile("(?i)" + Regex.all_type.getRegex());
	final private Pattern seeder_pattern = Pattern.compile(Regex.seeder.getRegex());
	final private Pattern resol_pattern = Pattern.compile(Regex.resol.getRegex());
	final private Pattern jamak_pattern = Pattern.compile("(?i)" + Regex.jamak_type.getRegex());
	final private Pattern movie_pattern = Pattern.compile("(?i)" + Regex.movie_type.getRegex());

	public void walker(String path) {
		File f = new File(path);
		Matcher m;
		List<VO> mvo_list = new ArrayList<>();
		List<VO> jvo_list = new ArrayList<>();

		if (!f.exists()) {
			System.out.println("[Error] Invalid path..");
		}

		if (f.isDirectory()) {

			for (File child : f.listFiles()) {

				String child_name = child.getName();
				m = file_pattern.matcher(child_name);

				if (!m.find()) {
					continue;
				}

				String child_type = m.group();

				/**
				 * For jamak..
				 */
				if (jamak_pattern.matcher(child_type).find()) {
					JVO jvo = new JVO();
					jvo.setFile_path(child.getPath());
					jvo.setFile_name(child_name);
					jvo.setFile_type(child_type);
					jvo.setAbFile_path(child.getParentFile().getPath());
					jvo_list.add(jvo);

				}

				/**
				 * For Movie..
				 */
				if (movie_pattern.matcher(child_type).find()) {
					MVO mvo = new MVO();
					mvo.setFile_name(replacer(child_name));
					mvo.setFile_type(child_type);
					mvo.setFile_path(child.getPath());
					mvo.setAbFile_path(child.getParentFile().getPath());
					mvo_list.add(mvo);
				}
			}

		}

		Renamer(mvo_list, jvo_list);

	}

	private String replacer(String child_name) {
		Matcher m;
		String delete_target;

		m = seeder_pattern.matcher(child_name);
		if (m.find()) {
			delete_target = m.group();
			child_name = child_name.replace(delete_target, "");
		}

		m = resol_pattern.matcher(child_name);
		if (m.find()) {
			delete_target = m.group();
			child_name = child_name.replace(delete_target, "");
		}

		return child_name.trim();
	}

	private VO SeederRemove(VO vo) {

		String repath = vo.getAbFile_path() + "\\" + vo.getFile_name();
		String org = vo.getFile_path();
		if (!(org.equals(repath))) {
			new File(org).renameTo(new File(repath));
			vo.setFile_path(repath);
		}
		return vo;
	}

	private void Renamer(List<VO> mvo_list, List<VO> jvo_list) {

		if (!(mvo_list.size() == jvo_list.size())) {
			System.out.println("[Error] The sizes of Type1 and Type2 are different.");
			return;
		}

		int i = 0;
		for (VO mvo : mvo_list) {
			mvo = SeederRemove(mvo);
			VO jvo = jvo_list.get(i);
			String replace_jamak_path = mvo.getFile_path().replace(mvo.getFile_type(), jvo.getFile_type());
			new File(jvo.getFile_path()).renameTo(new File(replace_jamak_path));

			i++;

		}

	}
}
