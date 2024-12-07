package KI306.Karapinka.Lab2;

public class PhotoCameraDriver {
    public static void main(String[] args) {
        PhotoCamera camera = new PhotoCamera("Canon", "EOS R5", new Lens("Canon", 24), new MemoryCard(64000), new Battery(100));

        camera.turnOn();
        camera.takePhoto();
        camera.burstMode(5);
        System.out.println("Battery level: " + camera.getBatteryLevel() + "%");
        System.out.println("Available memory: " + camera.getAvailableMemory() + " units");

        camera.changeLens(new Lens("Canon", 50));
        camera.takePhoto();

        camera.rechargeBattery();
        camera.formatMemoryCard();
        System.out.println("Available memory: " + camera.getAvailableMemory() + " units");
        camera.takePhoto();
        camera.takePhoto();
        camera.getBatteryLevel();

        camera.turnOff();
        camera.closeLogger();
    }
}