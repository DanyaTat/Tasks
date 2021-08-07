package ua.nure.tatarskyi.task4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part2 {
	public static void main(String[] args) {
		String data = "asd 43 asdf asd 43\n" +
				"434 asdf 1 1 1 1 1 \n" +
				"kasdf asdf stop asdf\n" +
				"stop";
		InputStream is = new ByteArrayInputStream(data.getBytes());
		System.setIn(is);
		WordContainer.main(null);
	}
}
