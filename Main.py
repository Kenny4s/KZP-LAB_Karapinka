from Camera import Camera
from DigitalCamera import DigitalCamera

def main():
    """
    Точка входу в програму. Демонстрація розширених можливостей класів Фотоапарат та Цифрова відеокамера.
    """

    # Створення об'єкта класу Фотоапарат
    camera = Camera(brand="Canon", model="EOS 90D", resolution=32.5)
    print(camera.take_photo())
    print(camera.set_zoom(2))
    print(camera.set_white_balance("daylight"))
    print(camera.take_photo())
    print(camera.view_photos())
    print(camera.delete_last_photo())
    print(camera.view_photos())

    # Створення об'єкта класу Цифрова відеокамера
    digital_camera = DigitalCamera(brand="Sony", model="Alpha 7S III", resolution=12.1, video_resolution="4K", fps=120)
    print(digital_camera.take_photo())
    print(digital_camera.record_video())
    print(digital_camera.set_video_resolution("1080p", 60))
    print(digital_camera.set_recording_mode("slow-motion"))
    print(digital_camera.record_video())
    print(digital_camera.view_videos())
    print(digital_camera.delete_last_video())
    print(digital_camera.view_videos())

if __name__ == "__main__":
    main()
