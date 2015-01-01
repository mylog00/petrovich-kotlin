/*$Id:$*/
package jPetrovich;

import jPetrovich.util.ECase;
import jPetrovich.util.EGender;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public class TPetrovich
{

	public static void main(String[] args) throws FileNotFoundException
	{
		Petrovich p = new Petrovich(EGender.male);
		System.out.println(p.firstname("Иван", ECase.dative));
		System.out.println(p.middlename("Сергеевич", ECase.dative));
		System.out.println(p.lastname("Воронов", ECase.dative));
	}

	@Test
	public void TDetectGender() throws FileNotFoundException
	{
		String maleString = "Алексеевич";
		String femaleString = "Алексеевна";
		String wrongString = "блаблабла";
		Petrovich p = new Petrovich();
		System.out.println(p.detectGender(maleString));
		assert (p.detectGender(maleString) == EGender.male);
		System.out.println(p.detectGender(femaleString));
		assert (p.detectGender(femaleString) == EGender.female);
		System.out.println(p.detectGender(wrongString));
		assert (p.detectGender(wrongString) == EGender.androgynous);
	}
}
