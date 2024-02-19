package main;

import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    protected String manufacturer;
    protected String model;
    protected int maxSpeed;
    protected Engine engine;

    public Vehicle(String manufacturer, String model, int maxSpeed) { // Vehicle-luokan konstruktori
        this.manufacturer = manufacturer;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    // Getter-metodit
    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getType() { // Metodi kulkuneuvon tyypin saamiseksi
        return "Kulkuneuvo";
    }
}

class Car extends Vehicle { // Car-luokka perii Vehicle-luokan ominaisuudet ja toiminnallisuudet
    public Car(String manufacturer, String model, int maxSpeed) {
        super(manufacturer, model, maxSpeed);
        this.setEngine(new Engine("V8", 300));
    }

    // Korvataan Vehicle-luokan getType-metodi
    @Override
    public String getType() {
        return "Auto";
    }

    public void drive() { // Metodi auton ajamiseen
        System.out.println(this.getManufacturer() + " " + this.getModel() + " paahtaa tietä eteenpäin!");
    }
}

class Plane extends Vehicle { // Plane-luokka perii Vehicle-luokan ominaisuudet ja toiminnallisuudet
    public Plane(String manufacturer, String model, int maxSpeed) {
        super(manufacturer, model, maxSpeed);
        this.setEngine(new Engine("Suihkumoottori", 500));
    }

    // Korvataan Vehicle-luokan getType-metodi
    @Override
    public String getType() { 
        return "Lentokone";
    }

    public void fly() {  // Metodi lentokoneen lentämiseen
        System.out.println(this.getManufacturer() + " " + this.getModel() + " lentää kohteeseen!");
    }
}

class Ship extends Vehicle { // Ship-luokka perii Vehicle-luokan ominaisuudet ja toiminnallisuudet
    public Ship(String manufacturer, String model, int maxSpeed) {
        super(manufacturer, model, maxSpeed);
        this.setEngine(new Engine("Wärtsilä Super", 1000));
    }

     // Korvataan Vehicle-luokan getType-metod
    @Override
    public String getType() {
        return "Laiva";
    }

    public void sail() { // Metodi laivan seilaamiseen
        System.out.println(this.getManufacturer() + " " + this.getModel() + " seilaa merten ääriin!");
    }
}

class Engine { //Engine luokka
    private String name;
    private int power;

    public Engine(String name, int power) { //Engine rakentaja
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }
}

public class App { //"Pääohjelma" alkaa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Scanner olio, jolla luetaan syötettä
        ArrayList<Vehicle> vehicles = new ArrayList<>(); //tähän tallenetaan tietoja

        int choice;
        do {
            System.out.println("1) Luo uusi kulkuneuvo, 2) Listaa kulkuneuvot 3) Aja autoja, 4) Lennä lentokoneita, 5) Seilaa laivoja, 0) Lopeta ohjelma");
            choice = scanner.nextInt();
            switch (choice) { //valikko alkaa 
                case 1:  // Luodaan uusi kulkuneuvo
                    System.out.println("Minkä kulkuneuvon haluat rakentaa? 1) auto, 2) lentokone, 3) laiva");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Anna kulkuneuvon valmistaja:");
                    String manufacturer = scanner.nextLine();
                    System.out.println("Anna kulkuneuvon malli:");
                    String model = scanner.nextLine();
                    System.out.println("Anna kulkuneuvon huippunopeus:");
                    int maxSpeed = scanner.nextInt();
                    switch (type) {
                        case 1:
                            vehicles.add(new Car(manufacturer, model, maxSpeed));
                            break;
                        case 2:
                            vehicles.add(new Plane(manufacturer, model, maxSpeed));
                            break;
                        case 3:
                            vehicles.add(new Ship(manufacturer, model, maxSpeed));
                            break;
                    }
                    break;
                case 2: // Listataan kulkuneuvot
                    for (Vehicle vehicle : vehicles) {
                        System.out.println(vehicle.getType() + ": " + vehicle.getManufacturer() + " " + vehicle.getModel());
                        System.out.println("Moottori: " + vehicle.engine.getName() + " " + vehicle.engine.getPower() + "kW");
                        System.out.println("Huippunopeus: " + vehicle.getMaxSpeed() + "km/h\n");
                    }
                    break;
                case 3:  // Ajetaan autoja
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Car) {
                            ((Car) vehicle).drive();
                        }
                    }
                    break;
                case 4: // Lenennetään lentokoneita
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Plane) {
                            ((Plane) vehicle).fly();
                        }
                    }
                    break;
                case 5: // Seilataan laivoja
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Ship) {
                            ((Ship) vehicle).sail();
                        }
                    }
                    break;
                case 0:    // Lopetetaan ohjelma
                    System.out.println("Kiitos ohjelman käytöstä.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
}