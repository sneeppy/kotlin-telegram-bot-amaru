package bot

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow


class Bot : TelegramLongPollingBot() {

    override fun getBotToken(): String {
        return "7785059012:AAGDOz-CWlAa4pT2gbDABs-sVQSrXxjtGa0"
    }

    override fun getBotUsername(): String {
        return "@pisyat_dva_story_bot"
    }

    private val pismo = "Мой дорогой Чарли,\n" +
            "Надеюсь, это письмо найдет тебя в добром здравии. Я пишу тебе из глубины джунглей Амазонки, где совершил великое открытие. Я нашел его, затерянный город инков - Амару!\n" +
            "Ты знаешь мою страсть к археологии и к поискам сокровищ. Я всегда мечтал найти этот город, легенды о котором передавались из поколения в поколение. И вот, моя мечта сбылась! \n" +
            "Город спрятан глубоко в джунглях, и доступ к нему охраняют опасные ловушки. Но я уверен, что ты, как и я, сможешь их преодолеть. \n" +
            "Я нашел несколько древних артефактов, и уверен, что ты сможешь использовать их, чтобы разгадать тайны Амару. \n" +
            "Я ищу \"Сердце Амару\", легендарный артефакт, который, по преданию, может открыть путь к сокровищам города. \n" +
            "Если ты когда-нибудь получишь это письмо, приезжай в Амазонку и найди меня. Я жду тебя.\n" +
            "Твой дядя,\n" +
            "Эдмунд \n" +
            "P.S. Координаты Амару:\n14.930677, -23.519290"

    private val prologNachalo = "В сердце Южной Америки, среди густых зеленых покровов Амазонской сельвы, таится место, окутанное мифами и легендами. Древний затерянный город инков, Амару, с его несметными сокровищами и загадочными ловушками, давно стал предметом поиска для многих исследователей, но лишь немногие осмеливались попробовать найти его.\n\n" +
            "Вы обнаруживаете письмо, отложенное в архиве вашей семьи, написанное в 1937 году вашим дядей Эдмундом, известным археологом и искателем приключений, который пропал без вести в этих джунглях много лет назад. Его слова, полные энтузиазма и страха, становятся ключом к разгадке не только его судьбы, но и самой судьбы Амару. В письме он описывает уникальные артефакты, которые ведут к несметным богатствам, однако предостерегает о коварных ловушках, способных погубить любого, кто осмелится нарушить покой древних духов."
    private val prologVariant1 = "Вы выбрали остаться дома смотреть сериалы.\nДля вас игра окончена.\nСожалеем, что вам не удалось погрузиться в удивительный мир приключений."
    private val prologVariant2 = "Собирая волю в кулак, вы решаете отправиться в опасное путешествие. Ваша цель — не только найти дядю Эдмунда и разгадать тайны Амару, но и стать частью истории, которая может навсегда изменить ваше представление о мире и о самих себе. Однако помните: джунгли хранят свои секреты, а их хранители всегда на страже…"

    private val Glava1Text1 = "Вы направляетесь к порту, где останавливаются торговые суда. На горизонте виднеется корабль, готовый к путешествию в неизведанные глубины Амазонки. Вы заключаете сделку с капитаном, опытным мореплавателем, который согласился провести вас через опасные воды в обмен на денежное вознаграждение. Вы отдаете ему все до последней копейки и готовитесь к отплытию.\n" +
            "Скоро вы уже на борту, и корабль начинает покидать гавань, оставляя позади суету цивилизации. Ветер наполняет паруса, а ваше сердце стучит от волнения. По мере того как вы плывете по реке, вы наблюдаете за изменением пейзажей: из ярких огней города в густые, темные джунгли, которые кажутся живыми.\n" +
            "\nЧерез несколько дней, когда река выводит вас все глубже в сердце джунглей, вы наконец приближаетесь к точным координатам из письма Эдмунда. Густая зелень окружает вас со всех сторон, создавая ощущение, что вы попали в другой мир. Солнце едва пробивается сквозь листву, оставляя лишь блики света, которые танцуют на земле. Дух захватывает от тайны, ожидающей вас впереди.\n" +
            "Как только вы достигаете назначенного места, ваше внимание привлекает заброшенный лагерь Эдмунда. Состояние его свидетельствует о том, что он был покинут поспешно: снаряжение разбросано, а вокруг — следы давней борьбы с дикой природой. Осматривая пространство в поисках улик о местонахождении дяди, ваше сердце начинает биться быстрее.\n" +
            "\nНа земле вы находите несколько предметов, которые могут пригодиться в вашем путешествии: потерянный компас, тяжелый топор и дневник Эдмунда. Ваша находка становится важным шагом в поисках, и, открыв дневник, вы погружаетесь в его мысли и переживания. Страницы полны записей о его путешествиях и загадках джунглей, однако на некоторых страницах видны следы его отчаяния и страха.\n" +
            "К вашему разочарованию, одна из страниц дневника, содержащая карту, оказалась порезанной на множество частей. Вам необходимо собрать ее по частям, чтобы понять, куда двигаться дальше."
    private val Glava1Text2 = "Разгадав головоломку, вы получаете восстановленную карту местности. На ней четко обозначен маршрут Эдмунда, по которому вы готовы продолжить путешествие. Ваши приключения обещают быть полными неожиданностей, и вы понимаете, что каждый шаг приближает вас к разгадке не только судьбы Эдмунда, но и тайны древнего города Амару. Ваша решимость и смелость будут проверены, но вы полны уверенности, что сможете преодолеть все преграды на своем пути.\n" +
            "\nТеперь, с картой в руках и новыми знаниями о маршруте, вы понимаете, что впереди вас ждут не только приключения, но и опасности. Джунгли вокруг становятся темнее, и вы решаете, что лучше всего будет провести ночь в лагере, чтобы подготовиться к следующему дню.\n" +
            "Вы разбиваете временный лагерь в безопасном месте неподалёку от заброшенного лагеря Эдмунда, стараясь максимально использовать имеющиеся у вас ресурсы. Пройдя день по жаркому солнцу, вы ощутили усталость, и вечерний воздух приносит с собой прохладу и тишину. Снаружи звучат мелодии джунглей, напоминая о том, что природа вокруг полна жизни.\n" +
            "\nРазжигая небольшой костер, вы углубляетесь в дневник Эдмунда, надеясь найти дополнительные подсказки о его приключениях. Каждая страница наполнена его переживаниями и наблюдениями, и вы чувствуете, что стали ближе к дяде, несмотря на расстояние и время. Лунный свет освещает страницы, и вы ловите себя на мысли, что ваши судьбы переплетены не только через кровь, но и через общие стремления и мечты.\n" +
            "Ночь постепенно окутывает джунгли, и вам не дает покоя предчувствие того, что на следующее утро вам предстоит двигаться вглубь неизведанных территорий, полных тайн и, возможно, опасностей. Вы решаете, что настраиваться на завтрашние испытания нужно с ясной головой, и, завернувшись в спальный мешок, позволяете себе немного отдохнуть.\n" +
            "\nСны, о которых вы мечтаете, смешиваются с реальностью: духи инков, потерянные сокровища и загадочные ловушки становятся частью вашей жизни, и вы понимаете, что ваше приключение только начинается. На следующее утро вы проснетесь с новыми силами и готовы продолжить поиски, чтобы разгадать тайны не только судьбы Эдмунда, но и древнего города Амару. С каждым шагом вы приближаетесь к чему-то большему — к знаниям и открытиям, которые могут навсегда изменить ваше представление о прошлом."


    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {

            val messageText = update.message.text
            val chatId = update.message.chatId

            val sendMessage1 = SendMessage()
            sendMessage1.chatId = chatId.toString()
            val sendMessage2 = SendMessage()
            sendMessage2.chatId = chatId.toString()
            val sendMessage3 = SendMessage()
            sendMessage3.chatId = chatId.toString()

            val sendPhoto = SendPhoto()
            sendPhoto.chatId = chatId.toString()
            //sendPhoto.photo = InputFile(java.io.File("C:/Users/Павел/IdeaProjects/StoryBot/pics/Письмо.jpeg"))
            //sendPhoto.caption = "Text"

            val keyboardMarkup = ReplyKeyboardMarkup()
            keyboardMarkup.selective = true // Опционально: делать кнопки доступными только для текущего пользователя
            keyboardMarkup.resizeKeyboard = true // Опционально: сделать кнопки занимать всю ширину клавиатуры

            val row = KeyboardRow()

            val inlineButtonsMessage = SendMessage()
            inlineButtonsMessage.chatId = update.message.chatId.toString()
            //inlineButtonsMessage.text = "Выберите опцию:"

            val inlineButtonsMessageWithPhoto = SendPhoto()
            inlineButtonsMessageWithPhoto.chatId = update.message.chatId.toString()

            val inlineKeyboardMarkup = InlineKeyboardMarkup()
            val keyboardButtonsRow: MutableList<InlineKeyboardButton> = ArrayList()


            when (messageText) {
                "/start" -> {
                    sendMessage1.text = prologNachalo

                    row.add("Ехать")
                    row.add("Зачилиться")

                    inlineButtonsMessageWithPhoto.photo =
                        InputFile(java.io.File("C:/Users/Павел/IdeaProjects/Amaru/pics/Письмо.jpeg"))

                    val button1 = InlineKeyboardButton()
                    button1.text = "Перевести в текст"
                    button1.callbackData = "PEREVESTI_PISMO"

                    keyboardButtonsRow.add(button1)
                }
                "Зачилиться" -> {
                    sendMessage1.text = prologVariant1

                    row.add("Оставить отзыв")
                }
                "Ехать" -> {
                    sendMessage1.text = prologVariant2

                    row.add("Отправиться в путешествие")
                }
                "Отправиться в путешествие" -> {
                    sendMessage1.text = Glava1Text1

                    row.add("Приступить к головоломке")
                }
                "Приступить к головоломке" -> {
                    sendMessage1.text = Glava1Text2

                    row.add("Продолжить")
                }
            }

            keyboardMarkup.keyboard = listOf(row)
            sendMessage1.replyMarkup = keyboardMarkup

            val rowList: MutableList<List<InlineKeyboardButton>> = ArrayList()
            rowList.add(keyboardButtonsRow)

            inlineKeyboardMarkup.keyboard = rowList
            inlineButtonsMessage.replyMarkup = inlineKeyboardMarkup
            inlineButtonsMessageWithPhoto.replyMarkup = inlineKeyboardMarkup

            try {
                when (messageText) {
                    "/start" -> {
                        execute(sendMessage1)
                        execute(inlineButtonsMessageWithPhoto)
                    }
                    "Зачилиться" -> execute(sendMessage1)
                    "Ехать" -> execute(sendMessage1)
                    "Отправиться в путешествие" -> execute(sendMessage1)
                    "Приступить к головоломке" -> execute(sendMessage1)

                }
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        } else if (update.hasCallbackQuery()) {
            val callbackData = update.callbackQuery.data
            val chatId = update.callbackQuery.message.chatId

            val responseMessage = SendMessage()
            responseMessage.chatId = chatId.toString()


            when (callbackData) {
                "PEREVESTI_PISMO" -> responseMessage.text = pismo
            }

            try {
                execute(responseMessage)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }
}


fun main() {
    val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
    try {
        botsApi.registerBot(Bot())
        println("Бот запущен!")
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }
}