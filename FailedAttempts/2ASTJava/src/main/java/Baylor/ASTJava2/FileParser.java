package Baylor.ASTJava2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
	static ArrayList<String> getSrcCode(File root) {

		ArrayList<String> srcFiles = new ArrayList<String>();

		for (File sub : root.listFiles()) {
			if (sub.isFile() && sub.getName().endsWith(".java")) {
				srcFiles.add(sub.getAbsolutePath());
			} else if (sub.isDirectory()) {
				srcFiles.addAll(getSrcCode(sub));
			}
		}
		return srcFiles;
	}

	static char[] getSrcChArray(String fileName) {
		System.out.println(fileName);
		ArrayList<String> srcFiles = getSrcCode(new File(fileName));

		String str;
		ArrayList<Character> list = new ArrayList<>();
		char[] myCharArray = null;

		BufferedReader br;
		try {

			for (String file : srcFiles) {
				br = new BufferedReader(new FileReader(file));

				while ((str = br.readLine()) != null) {
					char[] cArray = str.toCharArray();

					ArrayList<Character> cList = new ArrayList<Character>();
					for (char c : cArray) {
						// System.out.print(c);
						cList.add(c);
					}
					list.addAll(cList);
					// System.out.print('\n');
					list.add('\n');
				}

			}

			myCharArray = new char[list.size()];
			for (int i = 0; i < list.size(); i++) {
				myCharArray[i] = list.get(i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return myCharArray;
	}
}
