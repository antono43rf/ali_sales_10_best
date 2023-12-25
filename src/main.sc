require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        image: https://248305.selcdn.ru/zfl_prod/64069/64072/Y6nDSc64tgJWac7N.png
        a: Добрый день! Я виртуальный помощник Анита. Покажу вам популярные женские товары на алиэкспресс за прошедший месяц. Выберите любимую категорию. || htmlEnabled = true, html = "Добрый день! Я виртуальный помощник Анита. Покажу вам популярные женские товары на алиэкспресс за прошедший месяц. Выберите любимую категорию."
        buttons:
            "Украшения" -> /Украшения
            "Спорт и отдых" -> /Спорт и отдых
            "Уютный дом" -> /Уютный дом
            "Женская одежда" -> /Женская одежда
            "Дом и сад" -> /Дом и сад
            "Красота и здоровье" -> /Красота и здоровье
            "Нижнее белье и аксессуары"
            "Обувь"
        intent: /Статус заказа || onlyThisState = false, toState = "/Украшения"
        intent: /Часы работы || onlyThisState = false, toState = "/Часы работы"
        intent: /Оставить отзыв || onlyThisState = false, toState = "/Отзыв о работе"
        intent: /sys/ru/aimylogic/parting || onlyThisState = false, toState = "/Bye"

    state: Bye
        a: Пока-пока!
        EndSession: 
            actions = {}

    state: NoMatch || sessionResult = "Тут обрабатываем непонятные запросы", sessionResultColor = "#3E8080"
        event!: noMatch
        a: Простите, я вас не поняла. Не могли бы вы уточнить вопрос? || html = "Простите, я вас не поняла. Не могли бы вы уточнить вопрос?"
        go!: /Меню

    state: Украшения || sessionResult = "1 блок", sessionResultColor = "#7E47D1"
        a: Посмотри какие украшения нашла для тебя, нажми кнопку с названием чтобы узнать больше || htmlEnabled = false, html = "Посмотри какие украшения нашла для тебя, нажми кнопку с названием чтобы узнать больше"
        buttons:
            {text: "Популярные бусы", url: "https://alii.pub/6uu5ai"}
            {text: "Брошь в виде дракона", url: "https://alii.pub/6tc809"}
            {text: "Бусы Bohoever", url: "https://alii.pub/6uu5km"}
            "Другое" -> /Start

    state: Часы работы || sessionResult = "Отвечаем про часы работы", sessionResultColor = "#15952F"
        a: Мы работаем с 10 утра до 8 вечера по будням и с 11 до 17 в субботу. Воскресенье — выходной. || htmlEnabled = false, html = "Мы работаем с 10 утра до 8 вечера по будням и с 11 до 17 в субботу. Воскресенье — выходной."
        go!: /Меню

    state: Отзыв о работе || sessionResult = "Пример работы с системными интентами", sessionResultColor = "#FFFFFF"
        a: Мы будем благодарны услышать ваш отзыв о работе. || htmlEnabled = false, html = "Мы будем благодарны услышать ваш отзыв о работе."
        intent: /sys/aimylogic/ru/approval || onlyThisState = false, toState = "/Отзыв о работе/Спасибо за оценку"
        intent: /sys/aimylogic/ru/insults || onlyThisState = false, toState = "/Отзыв о работе/Не хами"
        intent: /sys/aimylogic/ru/negative || onlyThisState = false, toState = "/Отзыв о работе/Извиниться"
        intent: /sys/aimylogic/ru/normal || onlyThisState = false, toState = "/Отзыв о работе/Будем стараться"
        event: noMatch || onlyThisState = false, toState = "/Отзыв о работе/Сохранить отзыв"

        state: Не хами
            a: Пожалуйста, сдерживайте ваши эмоции! || htmlEnabled = false, html = "Пожалуйста, сдерживайте ваши эмоции!"
            go!: /Меню

        state: Будем стараться
            a: Спасибо. В следующий раз мы постараемся быть лучше. || htmlEnabled = false, html = "Спасибо. В следующий раз мы постараемся быть лучше."
            go!: /Меню

        state: Извиниться
            a: Я сожалею, что мы доставили вам неудобства. От имени компании приношу вам свои извинения и обязательно передам вашу жалобу руководству. || htmlEnabled = false, html = "Я сожалею, что мы доставили вам неудобства. От имени компании приношу вам свои извинения и обязательно передам вашу жалобу руководству."
            go!: /Меню

        state: Спасибо за оценку
            a: Спасибо за высокую оценку! Мы рады стараться для вас! || htmlEnabled = false, html = "Спасибо за высокую оценку! Мы рады стараться для вас!"
            go!: /Меню

        state: Сохранить отзыв || sessionResult = "Тут мы поместили работу с отзывом в подсценарий", sessionResultColor = "#143AD1"
            a: Хорошо, я поняла. Ваш отзыв:
                {{$request.query}}
                Обязательно передам руководству! || htmlEnabled = true, html = "Хорошо, я поняла. Ваш отзыв: <br><br>{{$request.query}}  <br><br>Обязательно передам руководству!"
            go!: /Меню

    state: Меню
        a: Чем еще я могу вам помочь? || htmlEnabled = false, html = "Чем еще я могу вам помочь?"
        buttons:
            "Статус заказа" -> /Украшения
            "Часы работы" -> /Часы работы
            "Оставить отзыв" -> /Отзыв о работе
        intent: /Статус заказа || onlyThisState = false, toState = "/Украшения"
        intent: /Оставить отзыв || onlyThisState = false, toState = "/Отзыв о работе"
        intent: /Часы работы || onlyThisState = false, toState = "/Часы работы"
        intent: /sys/aimylogic/ru/parting || onlyThisState = false, toState = "/Bye"

    state: Ошибка получения || sessionResult = "Сервер недоступен", sessionResultColor = "#CD4C2B"
        a: К сожалению, я никак не могу связаться с сервером. Попробуйте позже, пожалуйста. || htmlEnabled = false, html = "К сожалению, я никак не могу связаться с сервером. Попробуйте позже, пожалуйста."
        go!: /Меню

    state: Неправильный номер || sessionResult = "Статус заказа", sessionResultColor = "#7E47D1"
        a: Это не похоже на номер заказа. || htmlEnabled = false, html = "Это не похоже на номер заказа."
        go!: /Украшения

    state: Получили данные || sessionResult = "Здесь надо будет научить бота обрабатывать ответ сервера", sessionResultColor = "#CD4C2B"
        a: Кажется, я не поняла, что мне ответили. Научите меня разбираться с этим, пожалуйста :) || htmlEnabled = false, html = "Кажется, я не поняла, что мне ответили. Научите меня разбираться с этим, пожалуйста :)"
        go!: /Меню

    state: Спорт и отдых
        a: Посмотри какие интересные товары для спорта и отдыха, нажми кнопку чтобы узнать больше || htmlEnabled = false, html = "Посмотри какие интересные товары для спорта и отдыха, нажми кнопку чтобы узнать больше"
        buttons:
            {text: "Мягкий флисовый шарф", url: "https://alii.pub/6uu5vi"}
            {text: "Лыжный костюм VECTOR", url: "https://alii.pub/6uu5xu"}
            {text: "Лыжные очки", url: "https://alii.pub/6uu5zl"}
            "Другое" -> /Start

    state: Уютный дом
        a: Посмотри какие интересные товары для дома я нашла, нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри какие интересные товары для дома я нашла, нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Новогодние гирлянды", url: "https://alii.pub/6uu66z"}
            {text: "Рулонные жалюзи с электроприводом ", url: "https://alii.pub/6uu6ey"}
            {text: "Новогодние наклейки на окна ", url: "https://alii.pub/6uu6cm"}
            "Другое" -> /Start

    state: Женская одежда || sessionResult = "одежда", sessionResultColor = "#D93275"
        a: Посмотри какую модную одежду я нашла, нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри какую модную одежду я нашла, нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Женский свитер", url: "https://alii.pub/6uu6gp"}
            {text: "Футболка с длинным рукавом", url: "https://alii.pub/6uu6in"}
            {text: "Женский свитер 100% шерсть", url: "https://alii.pub/6uu6k7"}
            "Другое"

    state: Дом и сад || sessionResult = "дд", sessionResultColor = "#D93275"
        a: Посмотри что полезное я нашла, нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри что полезное я нашла, нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Набор столовых приборов", url: "https://alii.pub/6uu6np"}
            {text: "Точилка для ножей", url: "https://alii.pub/6uu6ov"}
            {text: "Термос армейский", url: "https://alii.pub/6uu6qm"}
            "Другое"

    state: Красота и здоровье
        a: Посмотри, эти товары помогут тебе стать красивой , нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри, эти товары помогут тебе стать красивой , нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Термогель для ногтей", url: "https://alii.pub/6uu6vh"}
            {text: "Массажный пистолет", url: "https://alii.pub/6uu6x1"}
            {text: "Пластырь", url: "https://alii.pub/6uu6xt"}
            "Другое"