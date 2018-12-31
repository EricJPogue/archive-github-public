//*********************************************************
// BMI Calculator (OOP Java)
// BMI = weight over height squared

abstract class BMI {
    abstract public float CalcBMI(float height, float weight); 
}

class BMIMetric extends BMI {
    public float CalcBMI(float height, float weight) {
         return weight / (height * height);
    }
}

class BMIEnglish extends BMI {
    public float CalcBMI(float height, float weight) {
        // Convert to meters and kg.
        height = height * (float)0.025; 
        weight = weight * (float)0.45; 
        return weight / (height * height);
    }
}

class CalcBMI {
    public static void main(String[] args) {
        BMI myBMI = new BMIEnglish();
        float BMIresult = myBMI.CalcBMI(
            (float)((6.0 * 12.0) + 1.0) /*height*/, 
            (float)190.0 /*weight*/); 

        System.out.println(BMIresult);
    }
}

