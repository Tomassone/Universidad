
public class MyMathTest 
{
	public static void main()
	{
		testOK_mcd();
		//testKO_mcd();
		testOK_mcm();
		//testKO_mcm();
	}
	private static void testOK_mcd()
	{
		assert(MyMath.mcd(10, 5) == 5);
		assert(MyMath.mcd(7, 3) == 1);
		assert(MyMath.mcd(21,49) == 7);
	}
	private static void testKO_mcd()
	{
		assert(MyMath.mcd(10, 5) == 4);
	}
	private static void testOK_mcm()
	{
		assert(MyMath.mcm(10, 5) == 10);
		assert(MyMath.mcm(7, 3) == 21);
		assert(MyMath.mcm(21, 49) == 147);
	}
	private static void testKO_mcm()
	{
		assert(MyMath.mcm(10, 5) == 12);
	}
}
