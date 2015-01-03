/*$Id:$*/
package jPetrovich;

import jPetrovich.util.CommonUtils;
import jPetrovich.util.ECase;
import jPetrovich.util.EGender;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public class TPetrovich {
	@Test
	public void TDetectGender() {
		String maleString = "Алексеевич";
		String femaleString = "Алексеевна";
		String wrongString = "блаблабла";

		assert (CommonUtils.detectGender(maleString).equals(EGender.male));

		assert (CommonUtils.detectGender(femaleString).equals(EGender.female));

		assert (CommonUtils.detectGender(wrongString).equals(EGender.androgynous));
	}

	@Test
	public void TFirstName() throws IOException {
		Petrovich p = new Petrovich();
		Collection<String[]> strings = loadTestSources("FirstNames.csv");

		for (String[] line : strings) {
			if (line.length == 4) {
				String testName = line[0];
				EGender genderCase = EGender.getGender(line[1]);
				ECase nameCase = ECase.valueOf(line[2].toLowerCase());
				String assertName = line[3];

				String result = p.firstName(testName, genderCase, nameCase);
				System.out.printf("name=%s;\tsex=%s;\tcase=%s;\tresult=%s;\n", testName, genderCase, nameCase, result);
				assert (assertName.equals(result));
			}
		}
	}

	@Test
	public void TLastName() throws IOException {
		Petrovich p = new Petrovich();
		Collection<String[]> strings = loadTestSources("LastNames.csv");

		for (String[] line : strings) {
			if (line.length == 4) {
				String testName = line[0];
				EGender genderCase = EGender.getGender(line[1]);
				ECase nameCase = ECase.valueOf(line[2].toLowerCase());
				String assertName = line[3];

				String result = p.lastName(testName, genderCase, nameCase);
				System.out.printf("name=%s;\tsex=%s;\tcase=%s;\tresult=%s;\n", testName, genderCase, nameCase, result);
				assert (assertName.equals(result));
			}
		}
	}

	@Test
	public void TMiddleName() throws IOException {
		Petrovich p = new Petrovich();
		Collection<String[]> strings = loadTestSources("MiddleNames.csv");

		for (String[] line : strings) {
			if (line.length == 4) {
				String testName = line[0];
				EGender genderCase = EGender.getGender(line[1]);
				ECase nameCase = ECase.valueOf(line[2].toLowerCase());
				String assertName = line[3];

				String result = p.middleName(testName, genderCase, nameCase);
				String resultDetectGender = p.middleName(testName, nameCase);
				System.out.printf("name=%s;\tsex=%s;\tcase=%s;\tresult=%s;\n", testName, genderCase, nameCase, result);
				assert (assertName.equals(result));
				assert (assertName.equals(resultDetectGender));
			}
		}
	}

	private Collection<String[]> loadTestSources(String path) throws IOException {
		List<String[]> res = new ArrayList<>();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		try (InputStream is = classloader.getResourceAsStream(path)) {

			String str;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
				while ((str = br.readLine()) != null) {
					String[] line = StringUtils.deleteWhitespace(str).split(",");
					res.add(line);
				}
			}
		}
		return res;
	}
}
