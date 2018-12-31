#include "stdafx.h"
#include <iostream>

//*********************************************************
// BMI Calculator (Proceedural C)
// BMI = weight over height squared

float heightininches = 0;
float weightinlbs = 0;

float heightinm = 0;
float weightinkg = 0;

float CalcBMIMetric(void) {
    return weightinkg / (heightinm * heightinm);
}

float CalcBMIEnglish(void) {
    heightinm = heightininches * 0.025;
    weightinkg = weightinlbs * 0.45;
    return CalcBMIMetric();
}

int main() {
    heightininches = (6.0 * 12.0) + 1; // 6'1"
    weightinlbs = 190.0;               // 190 lbs

    float BMI = CalcBMIEnglish();
    printf("BMI: %f\n", BMI);
    return 0;
}

