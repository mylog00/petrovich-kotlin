/*$Id:$*/
package petrovich.util;

/**
 * @author DMITRY KNYAZEV
 * @since 08.06.2014
 */
public abstract class CommonUtils
{
	public static String join(String[] str, String separator) {
		if(str == null || str.length <= 0)
			return "";
		StringBuilder res = new StringBuilder("");
		for(int i = 0; i < str.length;) {
			res.append(str[i]);
			if(++i < str.length)
				res.append(separator);
		}
		return res.toString();
	}
	public static boolean isEmpty(String[] str) {
		return str != null && str.length > 0;
	}
}
