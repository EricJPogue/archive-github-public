import java.util.Random;

abstract class Animal {
    abstract public void PrintYourAngrySound(); 
}

class Dog extends Animal {
    public void PrintYourAngrySound() {
        System.out.println("Bark!"); 
    }
}

class Cat extends Animal {
     public void PrintYourAngrySound() {
        System.out.println("Hisssss!"); 
    }
}

class Bigcat extends Cat {
     public void PrintYourAngrySound() {
        System.out.println("Grrrr... Hisssss!"); 
    }
}

class PolyAnimalSounds {
    public static void main(String[] args) {
        Random rand = new Random();

        Animal someAnimal = new Dog(); 
        for (int i=1; i<50; i++) {
            int randAnimalIndex = rand.nextInt(3);
            if (randAnimalIndex == 0) someAnimal = new Dog();
            else if (randAnimalIndex == 1) someAnimal = new Cat();
            else if (randAnimalIndex == 2) someAnimal = new Bigcat(); 
            
            someAnimal.PrintYourAngrySound();
         }
    }
}

