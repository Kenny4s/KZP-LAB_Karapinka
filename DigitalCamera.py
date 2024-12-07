from Camera import Camera

class DigitalCamera(Camera):
    """
    Похідний клас Цифрова відеокамера, що розширює функціонал базового класу Фотоапарат.
    """

    def __init__(self, brand, model, resolution, video_resolution, fps):
        """
        Ініціалізація цифрової відеокамери з додатковими параметрами для відеозапису.
        
        :param brand: Марка камери
        :param model: Модель камери
        :param resolution: Роздільна здатність для фотографій
        :param video_resolution: Роздільна здатність відео (наприклад, 4K)
        :param fps: Частота кадрів відео
        """
        super().__init__(brand, model, resolution)
        self.video_resolution = video_resolution  # Роздільна здатність відео
        self.fps = fps  # Частота кадрів
        self.videos = []  # Список збережених відео
        self.recording_mode = "standard"  # Режим зйомки: standard, slow-motion, time-lapse

    def record_video(self):
        """
        Метод для симуляції запису відео.
        
        :return: Повідомлення про те, що відео записано
        """
        video = f"Відео {len(self.videos) + 1} з роздільною здатністю {self.video_resolution} та {self.fps} кадрів/сек"
        self.videos.append(video)
        return f"Запис відео: {video} збережено."

    def set_video_resolution(self, resolution, fps):
        """
        Встановити нову роздільну здатність і частоту кадрів для відеозапису.
        
        :param resolution: Роздільна здатність відео
        :param fps: Частота кадрів
        :return: Повідомлення про нові налаштування відеозапису
        """
        self.video_resolution = resolution
        self.fps = fps
        return f"Налаштування відео оновлено: роздільна здатність {self.video_resolution}, {self.fps} кадрів/сек."

    def set_recording_mode(self, mode):
        """
        Встановити режим зйомки для відеокамери.
        
        :param mode: Режим зйомки (standard, slow-motion, time-lapse)
        :return: Повідомлення про встановлення режиму зйомки
        """
        if mode in ["standard", "slow-motion", "time-lapse"]:
            self.recording_mode = mode
            return f"Режим зйомки встановлено: {self.recording_mode}."
        else:
            return "Неправильний режим зйомки. Доступні режими: standard, slow-motion, time-lapse."

    def view_videos(self):
        """
        Перегляд усіх збережених відео.
        
        :return: Список усіх збережених відеозаписів
        """
        if self.videos:
            return f"Збережені відео: {', '.join(self.videos)}"
        else:
            return "Немає збережених відео."

    def delete_last_video(self):
        """
        Видалення останнього збереженого відео.
        
        :return: Повідомлення про видалення або про відсутність відеозаписів
        """
        if self.videos:
            deleted_video = self.videos.pop()
            return f"{deleted_video} видалено."
        else:
            return "Немає відео для видалення."
