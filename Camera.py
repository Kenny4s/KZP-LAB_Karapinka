class Camera:
    """
    Базовий клас Фотоапарат, що містить основні характеристики фотоапарата і додатковий функціонал.
    """

    def __init__(self, brand, model, resolution):
        """
        Ініціалізація фотоапарата з маркою, моделлю та роздільною здатністю.
        
        :param brand: Марка фотоапарата
        :param model: Модель фотоапарата
        :param resolution: Роздільна здатність в мегапікселях
        """
        self.brand = brand
        self.model = model
        self.resolution = resolution
        self.zoom_level = 1  # Початковий рівень зуму
        self.white_balance = "auto"  # Баланс білого: auto, daylight, cloudy тощо
        self.photos = []  # Список збережених фотографій

    def take_photo(self):
        """
        Метод для симуляції фотографування.
        
        :return: Повідомлення про те, що фото зроблено
        """
        photo = f"Фото {len(self.photos) + 1} з роздільною здатністю {self.resolution} MP"
        self.photos.append(photo)
        return f"Фото зроблено за допомогою {self.brand} {self.model}. {photo} збережено."

    def set_zoom(self, level):
        """
        Встановити рівень зуму.
        
        :param level: Новий рівень зуму
        :return: Повідомлення про встановлення зуму
        """
        self.zoom_level = level
        return f"Зум встановлено на рівень {self.zoom_level}x."

    def set_white_balance(self, mode):
        """
        Встановити баланс білого.
        
        :param mode: Режим балансу білого (наприклад, auto, daylight, cloudy)
        :return: Повідомлення про встановлення балансу білого
        """
        self.white_balance = mode
        return f"Баланс білого встановлено на режим '{self.white_balance}'."

    def view_photos(self):
        """
        Перегляд усіх збережених фотографій.
        
        :return: Список усіх збережених фотографій
        """
        if self.photos:
            return f"Збережені фотографії: {', '.join(self.photos)}"
        else:
            return "Немає збережених фотографій."

    def delete_last_photo(self):
        """
        Видалення останнього збереженого фото.
        
        :return: Повідомлення про видалення або про відсутність фотографій
        """
        if self.photos:
            deleted_photo = self.photos.pop()
            return f"{deleted_photo} видалено."
        else:
            return "Немає фотографій для видалення."
