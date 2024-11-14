package bot

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import org.telegram.telegrambots.bots.TelegramLongPollingBot


class Bot : TelegramLongPollingBot() {

    override fun getBotToken(): String {
        return "7785059012:AAGDOz-CWlAa4pT2gbDABs-sVQSrXxjtGa0"
    }

    override fun getBotUsername(): String {
        return "@pisyat_dva_story_bot"
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {

            val messageText = update.message.text
            val chatId = update.message.chatId

            val sendMessage = SendMessage()
            sendMessage.chatId = chatId.toString()
            sendMessage.text = messageText

            try {
                execute(sendMessage)

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