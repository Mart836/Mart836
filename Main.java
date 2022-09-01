package com.company;
import java.util.ArrayList;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
abstract class Vehicle {
    private String plateNum;
    protected int seats;
    private double engineSize;

    public Vehicle(String plateNum, int seats, double engineSize){
        this.plateNum = plateNum;
        this.seats = seats;
        this.engineSize = engineSize;
    }

    public abstract double GetPrice(double kilometres);

    public String GetPlateNum(){
        return plateNum;
    }
}
class Sedan extends Vehicle{
    public Sedan(String plateNo, int seats, double engineSize){
        super(plateNo, seats, engineSize);
    }

    @Override
    public double GetPrice(double kilometres) {
        return kilometres * 25;
    }
}
class Minibus extends Vehicle{
    public Minibus(String plateNo, int seats, double engineSize){
        super(plateNo, seats, engineSize);
    }

    @Override
    public double GetPrice(double kilometres) {
        return (kilometres * 15)/this.seats;
    }
}


class CabCompany {
    private ArrayList<Vehicle> vehicles;

    public CabCompany(){
        vehicles = new ArrayList<>(67);
    }
    public String GetAllCars(){
        String finalString = "";
        for(int i = 0; i < vehicles.size(); i++){
            finalString += ("\n" + vehicles.get(i).GetPlateNum());
        }
        return finalString;
    }


    public void AddVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public String CheaperMinibus(double kilometres){
        //since the first element in the array could be a bus or a sedan, we need to use the smalles possible number instead of the fist vehicle's price
        double cheapestPrice = Double.MAX_EXPONENT;
        String cheapestPlate = "";
        for(int i = 0; i < vehicles.size(); i++){
            if(vehicles.get(i) instanceof Minibus && vehicles.get(i).GetPrice(kilometres) <= cheapestPrice){
                cheapestPrice = vehicles.get(i).GetPrice(kilometres);
                cheapestPlate = vehicles.get(i).GetPlateNum();
            }
        }
        return (cheapestPlate);
    }

    public int HowManySedan(){
        int count = 0;
        for(int i = 0; i < vehicles.size(); i++){
            if(vehicles.get(i) instanceof Sedan){
                count++;
            }
        }
        return count;
    }
}
class Bus extends Vehicle{
    public Bus(String plateNo, int seats, double engineSize){
        super(plateNo, seats, engineSize);
    }

    @Override
    public double GetPrice(double kilometres) {
        return (kilometres * 15)/this.seats;
    }
}
class Student{
    private  final double [] quiz_score;

    Student(double[] quiz_score) {
        this.quiz_score = quiz_score;
    }
    public double getHighest(){
        double High = quiz_score[0];
        for (int i=0;i<quiz_score.length;i++){
            if(quiz_score[i]>=High){
                High = quiz_score[i];
            }
        }
        return  High;
    }
    public double getLowest(){
        double Lowest = quiz_score[0];
        for (int i=0;i<quiz_score.length;i++) {
            if (quiz_score[i] <= Lowest) {
                Lowest = quiz_score[i];
            }
        }
        return Lowest;
    }
}
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0");
        System.out.println("How many students inside the class?");
        int stu =scanner.nextInt();
        System.out.println("\nEnter the quiz score for the test");
        double [] Score = new double[stu];
        for (int i=0;i<stu;i++){
            System.out.print("Students " + (i+1) + " : ");
            Score[i] = scanner.nextDouble();
        }

        Student obj = new Student(Score);
        System.out.println("The highest Score is " + df.format(obj.getHighest()));
        System.out.println("The lowest Score is " + df.format(obj.getLowest()));

       Sedan sedan =new Sedan("N 89290",4,3.2);
       Sedan sedan1 =new Sedan("N 2345 K",4,2.3);
       System.out.println("Sedan price " + sedan.GetPrice(970) +" "+ "Sedan1 price: " + sedan1.GetPrice(45));
       System.out.println("Plate number: " + sedan.GetPlateNum() + " " + "sedan1 Plate number " + sedan1.GetPlateNum());

        Minibus bus = new Minibus("N 4747 W",6,3.2);
        Minibus minibus = new Minibus("N 7854 T",6,2);
        System.out.println("The price for minibus2 is " + minibus.GetPrice(12));
        System.out.println("The price for minibus is " + bus.GetPrice(10));

        bus.GetPlateNum();
        Bus bus1 = new Bus("N 6738 ND",5,2.3);

        System.out.println("The price for bus is " + bus1.GetPrice(100));
        System.out.println("PlateNumber" + bus.GetPlateNum());
        CabCompany cabCompany = new CabCompany();
        cabCompany.AddVehicle(bus);
        cabCompany.AddVehicle(bus1);
        cabCompany.AddVehicle(sedan);
        cabCompany.AddVehicle(sedan1);
        cabCompany.AddVehicle(minibus);
        System.out.println(" " + cabCompany.GetAllCars());
        System.out.println("Cheaper " + cabCompany.CheaperMinibus(12));
        System.out.println("Sedans " + cabCompany.HowManySedan());


    }
}
