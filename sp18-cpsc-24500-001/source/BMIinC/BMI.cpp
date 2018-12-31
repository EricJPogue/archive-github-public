#include "stdafx.h"
#include <iostream>

//*********************************************************
// BMI Calculator (Proceedural C)
// BMI = weight over height squared

float height = 0;
float weight = 0;

float CalcBMI(void) {
    return weight / (height * height);
}

int main() {
    height = (6.0 * 12.0) + 1.0; //6'1"
    weight = 190.0;              // 195 lbs
    float BMI = CalcBMI();

    printf("BMI: %f\n", BMI);
    return 0;
}
