package KI306.Karapinka.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Interface for devices that can connect to Wi-Fi.
 */
interface WiFiConnectable {
    void connectToWiFi(String networkName);
    void disconnectFromWiFi();
    boolean isConnectedToWiFi();
}

/**
 * Abstract base class for a photo camera.
 */
abstract class AbstractPhotoCamera {
    protected Lens lens;
    protected MemoryCard memoryCard;
    protected Battery battery;
    protected String brand;
    protected String model;
    protected PrintWriter logWriter;

    public AbstractPhotoCamera(String brand, String model, Lens lens, MemoryCard memoryCard, Battery battery) {
        this.brand = brand;
        this.model = model;
        this.lens = lens;
        this.memoryCard = memoryCard;
        this.battery = battery;
        initializeLogger();
    }

    private void initializeLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter("camera_log.txt", true), true);
        } catch (IOException e) {
            System.err.println("Error initializing logger: " + e.getMessage());
        }
    }

    protected void log(String message) {
        if (logWriter != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            logWriter.println(timestamp + ": " + message);
        }
    }

    public abstract void takePhoto();

    public void changeLens(Lens newLens) {
        this.lens = newLens;
        log("Lens changed to " + newLens.getBrand() + " " + newLens.getFocalLength() + "mm");
    }

    public void rechargeBattery() {
        battery.recharge();
        log("Battery recharged to 100%");
    }

    public void formatMemoryCard() {
        memoryCard = new MemoryCard(memoryCard.getCapacity());
        log("Memory card formatted");
    }

    public int getBatteryLevel() {
        log("Battery level checked: " + battery.getCharge() + "%");
        return battery.getCharge();
    }

    public int getAvailableMemory() {
        int available = memoryCard.getCapacity() - memoryCard.getUsedSpace();
        log("Available memory checked: " + available + " units");
        return available;
    }

    public void closeLogger() {
        if (logWriter != null) {
            logWriter.close();
        }
    }
}

/**
 * Represents a smart photo camera with Wi-Fi capabilities.
 */
public class SmartPhotoCamera extends AbstractPhotoCamera implements WiFiConnectable {
    private boolean wifiConnected;
    private String connectedNetwork;

    public SmartPhotoCamera(String brand, String model, Lens lens, MemoryCard memoryCard, Battery battery) {
        super(brand, model, lens, memoryCard, battery);
        this.wifiConnected = false;
        this.connectedNetwork = null;
    }

    @Override
    public void takePhoto() {
        if (battery.getCharge() > 0 && memoryCard.getUsedSpace() < memoryCard.getCapacity()) {
            battery.use(1);
            memoryCard.addPhoto(5);
            log("Smart photo taken with " + lens.getBrand() + " " + lens.getFocalLength() + "mm lens");
            if (wifiConnected) {
                uploadPhotoToCloud();
            }
        } else {
            log("Unable to take photo: " + (battery.getCharge() == 0 ? "Battery dead" : "Memory card full"));
        }
    }

    private void uploadPhotoToCloud() {
        if (wifiConnected) {
            log("Photo uploaded to cloud via Wi-Fi");
        } else {
            log("Cannot upload photo: Wi-Fi not connected");
        }
    }

    @Override
    public void connectToWiFi(String networkName) {
        this.wifiConnected = true;
        this.connectedNetwork = networkName;
        log("Connected to Wi-Fi network: " + networkName);
    }

    @Override
    public void disconnectFromWiFi() {
        this.wifiConnected = false;
        this.connectedNetwork = null;
        log("Disconnected from Wi-Fi network");
    }

    @Override
    public boolean isConnectedToWiFi() {
        return wifiConnected;
    }

    public void takeTimeLapse(int frames, int interval) {
        log("Starting time-lapse: " + frames + " frames at " + interval + " second intervals");
        for (int i = 0; i < frames; i++) {
            takePhoto();
            try {
                Thread.sleep(interval * 1000);
            } catch (InterruptedException e) {
                log("Time-lapse interrupted");
                break;
            }
        }
        log("Time-lapse completed");
    }

    public static void main(String[] args) {
        SmartPhotoCamera camera = new SmartPhotoCamera("Sony", "A7R IV", new Lens("Sony", 35), new MemoryCard(128000), new Battery(100));
    
        camera.takePhoto();
        camera.connectToWiFi("Home_Network");
        camera.takePhoto();  // This photo will be uploaded to the cloud
        camera.takeTimeLapse(5, 2);
        camera.disconnectFromWiFi();
    
        // Logging battery level and available memory instead of printing to console
        camera.log("Battery level: " + camera.getBatteryLevel() + "%");
        camera.log("Available memory: " + camera.getAvailableMemory() + " units");
    
        camera.closeLogger();
    }
    
}

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