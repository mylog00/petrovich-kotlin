/*$Id:$*/
package jPetrovich;

import jPetrovich.util.CommonUtils;
import jPetrovich.util.ECase;
import jPetrovich.util.EGender;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
	public void TFirstName() {
//		Assert.
		Petrovich p;
		try {
			p = new Petrovich(EGender.male);

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("FirstNames.csv");
//			InputStream resourceAsStream = this.getClass().getResourceAsStream("resources/FirstNames.csv");

			System.out.println(p.firstName("Иван", ECase.dative));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TLastName(){
		Petrovich p;
		try {
			p = new Petrovich(EGender.male);
			System.out.println(p.lastName("Воронов", ECase.dative));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TMiddleName(){
		Petrovich p;
		try {
			p = new Petrovich(EGender.male);
			System.out.println(p.middleName("Сергеевич", ECase.dative));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
