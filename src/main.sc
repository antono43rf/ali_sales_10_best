require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        image: https://248305.selcdn.ru/zfl_prod/64069/64072/Y6nDSc64tgJWac7N.png
        a: Добрый день! Я виртуальная фея. Покажу вам популярные женские товары на алиэкспресс. 
                Выберите любимую категорию. || htmlEnabled = true, html = "Добрый день! Я виртуальная фея. Покажу вам популярные женские товары на алиэкспресс.&nbsp;<br><br>Выберите любимую категорию."
        buttons:
            "Украшения" -> /Украшения
            "Спорт и отдых" -> /Спорт и отдых
            "Уютный дом" -> /Уютный дом
            "Женская одежда" -> /Женская одежда
            "Дом и сад" -> /Дом и сад
            "Красота и здоровье" -> /Красота и здоровье
            "Нижнее белье" -> /Женское нижнее белье
            "Обувь" -> /Обувь
        buttons:
            "не нашел что искал"
        intent: /sys/ru/aimylogic/parting || onlyThisState = false, toState = "/Bye"

    state: Bye
        a: Пока-пока!
        EndSession: 
            actions = {}

    state: NoMatch || sessionResult = "Тут обрабатываем непонятные запросы", sessionResultColor = "#3E8080"
        event!: noMatch
        a: Простите, я вас не поняла. Не могли бы вы уточнить вопрос? || html = "Простите, я вас не поняла. Не могли бы вы уточнить вопрос?"
        go!: /Bye

    state: Украшения || sessionResult = "1 блок", sessionResultColor = "#7E47D1"
        a: Посмотри какие украшения нашла для тебя, нажми кнопку с названием чтобы узнать больше || htmlEnabled = false, html = "Посмотри какие украшения нашла для тебя, нажми кнопку с названием чтобы узнать больше"
        buttons:
            {text: "Популярные бусы", url: "https://alii.pub/6uu5ai"}
            {text: "Брошь в виде дракона", url: "https://alii.pub/6tc809"}
            {text: "Бусы Bohoever", url: "https://alii.pub/6uu5km"}
            "Другое" -> /Start

    state: Часы работы || sessionResult = "Отвечаем про часы работы", sessionResultColor = "#15952F"
        a: Мы работаем с 10 утра до 8 вечера по будням и с 11 до 17 в субботу. Воскресенье — выходной. || htmlEnabled = false, html = "Мы работаем с 10 утра до 8 вечера по будням и с 11 до 17 в субботу. Воскресенье — выходной."
        go!: /Доп меню

    state: Доп меню
        a: Чем еще я могу вам помочь? || htmlEnabled = false, html = "Чем еще я могу вам помочь?"
        buttons:
            {text: "перейти на главную страницы маркетплейса", url: "https://alii.pub/6uu7dk"}
            {text: "Спросить оператора", url: "t-do.ru/konovalovokna"}
            "Попрощаться" -> /Bye
            {text: "Партнерка с алиэкспресс", url: "https://shp.pub/6uu7r6"}
        intent: /sys/aimylogic/ru/parting || onlyThisState = false, toState = "/Bye"
        event: noMatch || toState = "/NoMatch"

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
            "Другое" -> /Start

    state: Женское нижнее белье
        a: Посмотри, какая прелесть , нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри, какая прелесть , нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Женский комплект нижнего белья", url: "https://alii.pub/6uu705"}
            {text: "Комплект с бюстгальтером в рубчик", url: "https://alii.pub/6uu70c"}
            {text: "Женские кашемировые носки", url: "https://alii.pub/6uu71b"}
            "Другое" -> /Start

    state: Обувь
        a: Посмотри, эти товары украсят твои ножки , нажми кнопку чтобы узнать больше! || htmlEnabled = false, html = "Посмотри, эти товары украсят твои ножки , нажми кнопку чтобы узнать больше!"
        buttons:
            {text: "Ботинки женские", url: "https://alii.pub/6uu74m"}
            {text: "Ботинки утепленные", url: "https://alii.pub/6uu76k"}
            {text: "Женские ботинки на толстой подошве", url: "https://alii.pub/6uu77j"}
            "Другое" -> /Start
