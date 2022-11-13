
#include "modulo.h"

double f(double x)
{
	x = x * x;
	return x;
}

float integrale_it(float a, float b, float n)
{
	float area = 0;
	float i, i_pr;
	for (i = a; i <= b; i = i + (b - a) / n)
	{
		if (i != a)
			area = ((float) f((double) i) + (float) f((double) i_pr)) * (i - i_pr) / 2 + area;
		i_pr = i;
	}
	return area;
}

float calc_ric(float a, float b, float delta, float max, float area)
{
	if (b > max)
		return 0;
	else
	{
		area = ((float) f((double) b) + (float) f((double) a)) * (b - a) / 2 + calc_ric((a + delta), (b + delta), delta, max, area);
		return area;
	}
}

float integrale_ric(float a, float b, float n)
{
	float area = 0;
	area = calc_ric(a, a + (b - a) / n, (b - a) / n, b, area);
	return area;
}

