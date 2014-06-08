/*$Id:$*/
package petrovich;

import petrovich.util.ECase;

import java.io.FileNotFoundException;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public class TPetrovich
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Petrovich p = new Petrovich();
		System.out.println(p.detectGender("Алексеевич"));
		System.out.println(p.detectGender("Алексеевна"));
		System.out.println(p.detectGender("блаблабла"));

		System.out.println(p.firstname("Иван", ECase.dative));
		System.out.println(p.middlename("Сергеевич", ECase.dative));
		System.out.println(p.lastname("Воронов", ECase.dative));
	}
}
