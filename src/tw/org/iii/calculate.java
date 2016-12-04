package tw.org.iii;


public class calculate {
	public String cal(String x, String y)
	{
		String result = null;
		Integer a =  Integer.parseInt(x) + Integer.parseInt(y);
		result = a.toString();
		return result;
	}
}
