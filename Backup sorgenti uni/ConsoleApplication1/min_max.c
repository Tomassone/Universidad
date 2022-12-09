
#include "min_max.h"

float calc_min(float x, float y)
{
	if (x <= y)
		return x;
	else
		return y;
}

float calc_max(float x, float y)
{
	if (x >= y)
		return x;
	else
		return y;
}