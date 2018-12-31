//*********************************************************
// BMI Calculator (OOP Java)
// BMI = weight over height squared
//
// Learning Objectives (Week 2): 
// 7. Understand how to enhance BMI Calculator 
//     a) Add JavaDocs documentation
//     b) Add keyboard input (scanner)
//

import java.util.Scanner;

abstract class BMI {
    abstract public float CalcBMI(float height, float weight); 
}

class BMIMetric extends BMI {
    public float CalcBMI(float height, float weight) {
         return weight / (height * height);
    }
}

/**
 * This method supports calculating Body Mass Index (BMI)
 * using English units.
 * @author EricJPogue
 */
class BMIEnglish extends BMI {
    /**
     * This method calulates the BMI based on a person's 
     * height and weight.
     * @param height This is the person's height in inches.
     * @param weight This is the person's weight in lbs.
     * @return This method returns BMI.
     */
    public float CalcBMI(float height, float weight) {
        // Convert to meters and kg.
        height = height * (float)0.025; 
        weight = weight * (float)0.45; 
        return weight / (height * height);
    }
}

public class CalcBMI {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter height (feet): ");
        int heightFeetFromUser=myScanner.nextInt();
        
        System.out.print("Enter height (inches): ");
        int heightInchesFromUser=myScanner.nextInt();
      
        System.out.print("Enter weight (lbs): ");
        int weightFromUser=myScanner.nextInt();

        BMI myBMI = new BMIEnglish();
        float BMIresult = myBMI.CalcBMI(
            (float)((heightFeetFromUser * 12.0) + heightInchesFromUser) /*height*/, 
            (float)weightFromUser /*weight*/); 

        System.out.println(BMIresult);
    }
}
