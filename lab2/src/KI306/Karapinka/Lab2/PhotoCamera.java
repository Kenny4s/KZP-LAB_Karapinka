/**
 * Package for the PhotoCamera class and related components.
 */
package KI306.Karapinka.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a lens for a photo camera.
 */
class Lens {
    private String brand;
    private int focalLength;

    public Lens(String brand, int focalLength) {
        this.brand = brand;
        this.focalLength = focalLength;
    }

    public String getBrand() {
        return brand;
    }

    public int getFocalLength() {
        return focalLength;
    }
}

/**
 * Represents a memory card for a photo camera.
 */
class MemoryCard {
    private int capacity;
    private int usedSpace;

    public MemoryCard(int capacity) {
        this.capacity = capacity;
        this.usedSpace = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsedSpace() {
        return usedSpace;
    }

    public void addPhoto(int size) {
        usedSpace += size;
    }
}

/**
 * Represents a battery for a photo camera.
 */
class Battery {
    private int charge;

    public Battery(int initialCharge) {
        this.charge = initialCharge;
    }

    public int getCharge() {
        return charge;
    }

    public void use(int amount) {
        charge -= amount;
        if (charge < 0) charge = 0;
    }

    public void recharge() {
        charge = 100;
    }
}

/**
 * Represents a photo camera with various functionalities.
 */
public class PhotoCamera {
    private Lens lens;
    private MemoryCard memoryCard;
    private Battery battery;
    private String brand;
    private String model;
    private PrintWriter logWriter;

    /**
     * Constructs a PhotoCamera with specified components.
     */
    public PhotoCamera(String brand, String model, Lens lens, MemoryCard memoryCard, Battery battery) {
        this.brand = brand;
        this.model = model;
        this.lens = lens;
        this.memoryCard = memoryCard;
        this.battery = battery;
        initializeLogger();
    }

    /**
     * Constructs a PhotoCamera with default components.
     */
    public PhotoCamera() {
        this("Default", "Model", new Lens("Generic", 50), new MemoryCard(32000), new Battery(100));
    }

    private void initializeLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter("camera_log.txt", true), true);
        } catch (IOException e) {
            System.err.println("Error initializing logger: " + e.getMessage());
        }
    }

    private void log(String message) {
        if (logWriter != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            logWriter.println(timestamp + ": " + message);
        }
    }

    /**
     * Takes a photo and stores it on the memory card.
     */
    public void takePhoto() {
        if (battery.getCharge() > 0 && memoryCard.getUsedSpace() < memoryCard.getCapacity()) {
            battery.use(1);
            memoryCard.addPhoto(5);
            log("Photo taken with " + lens.getBrand() + " " + lens.getFocalLength() + "mm lens");
        } else {
            log("Unable to take photo: " + (battery.getCharge() == 0 ? "Battery dead" : "Memory card full"));
        }
    }

    /**
     * Changes the lens of the camera.
     */
    public void changeLens(Lens newLens) {
        this.lens = newLens;
        log("Lens changed to " + newLens.getBrand() + " " + newLens.getFocalLength() + "mm");
    }

    /**
     * Recharges the camera's battery.
     */
    public void rechargeBattery() {
        battery.recharge();
        log("Battery recharged to 100%");
    }

    /**
     * Formats the memory card, clearing all photos.
     */
    public void formatMemoryCard() {
        memoryCard = new MemoryCard(memoryCard.getCapacity());
        log("Memory card formatted");
    }

    /**
     * Gets the current battery level.
     */
    public int getBatteryLevel() {
        log("Battery level checked: " + battery.getCharge() + "%");
        return battery.getCharge();
    }

    /**
     * Gets the available space on the memory card.
     */
    public int getAvailableMemory() {
        int available = memoryCard.getCapacity() - memoryCard.getUsedSpace();
        log("Available memory checked: " + available + " units");
        return available;
    }

    /**
     * Simulates taking multiple photos.
     */
    public void burstMode(int count) {
        log("Burst mode activated for " + count + " photos");
        for (int i = 0; i < count; i++) {
            takePhoto();
        }
    }

    /**
     * Turns the camera on.
     */
    public void turnOn() {
        log("Camera turned on");
    }

    /**
     * Turns the camera off.
     */
    public void turnOff() {
        log("Camera turned off");
    }

    /**
     * Closes the logger to ensure proper file handling.
     */
    public void closeLogger() {
        if (logWriter != null) {
            logWriter.close();
        }
    }

    /**
     * Driver class to demonstrate the functionality of the PhotoCamera class.
     */
    
}
