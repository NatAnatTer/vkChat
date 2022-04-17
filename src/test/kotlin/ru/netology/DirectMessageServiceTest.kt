package ru.netology

import org.junit.Assert.*
import org.junit.Test
import ru.netology.DirectMessageService.addDirectMessages
import ru.netology.DirectMessageService.deleteDirectMessages
import ru.netology.DirectMessageService.deleteMessage
import ru.netology.DirectMessageService.directMessages
import ru.netology.DirectMessageService.editMessage
import ru.netology.DirectMessageService.getDirectMessages
import ru.netology.DirectMessageService.getMessages
import ru.netology.DirectMessageService.getUnreadChatsCount

class DirectMessageServiceTest {

    @Test
    fun addDirectMessagesTest() {
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")

        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }

    @Test
    fun addDirectMessagesTestTwo() {
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hi. Second message")
        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }

    @Test
    fun addDirectMessagesTestThree() {
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }

    @Test
    fun addDirectMessagesTestIsDeleted() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
        for (chat in directMessages) {
            chat.isDelete = true
        }
        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }
    @Test
    fun addDirectMessagesTestIsDeletedRevers() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hi. Second message")
        for (chat in directMessages) {
            chat.isDelete = true
        }
        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }

    @Test
    fun deleteDirectMessagesTest() {
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val result = deleteDirectMessages(peopleOne, peopleTwo)
        assertTrue(result)
    }

    @Test
    fun deleteDirectMessagesTestFalse() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        val result = deleteDirectMessages(peopleOne, peopleTwo)
        assertFalse(result)
    }


    @Test
    fun getDirectMessagesTest() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")

        val result = getDirectMessages(peopleOne)
        assertEquals(directMessages, result)
    }

    @Test
    fun getDirectMessagesTestIsEmpty() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        val peopleThree = People(3U, "Vika", "Виктория")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val result = getDirectMessages(peopleThree)
        directMessages.clear()
        assertEquals(directMessages, result)
    }

    @Test
    fun getUnreadChatsCountTest() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        val peopleThree = People(3U, "Vika", "Виктория")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        addDirectMessages(peopleThree, peopleTwo, "Hello. First message")
        val result = getUnreadChatsCount(peopleTwo)
        assertEquals(2, result)
    }

    @Test
    fun editMessageTest() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val result = editMessage(peopleOne, peopleTwo, 1U, "Edited message")
        assertTrue(result)
    }

    @Test
    fun editMessageTestRevers() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val result = editMessage(peopleTwo, peopleOne, 1U, "Edited message")
        assertTrue(result)
    }

    @Test
    fun editMessageTestText() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val text = "Edited message"
        editMessage(peopleTwo, peopleOne, 1U, "Edited message")
        var resultText = ""
        for (chat in directMessages) {
            for (message in chat.message!!) {
                resultText = message.text
            }
        }

        assertEquals(text, resultText)
    }

    @Test
    fun deleteMessageTest() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        val result = deleteMessage(peopleOne, peopleTwo, 1U)
        assertTrue(result)
    }

    @Test
    fun deleteMessageTestRevers() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hello. First message")
        val result = deleteMessage(peopleOne, peopleTwo, 1U)
        assertTrue(result)
    }

    @Test
    fun deleteMessageTestCheckIsDelete() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hello. First message")
        deleteMessage(peopleOne, peopleTwo, 1U)
        var result = false
        for (chat in directMessages) {
            for (message in chat.message!!) {
                result = message.isDelete
            }
        }
        assertEquals(true, result)
    }

    @Test
    fun deleteMessageTestCheckIsDeleteChat() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hello. First message")
        deleteMessage(peopleOne, peopleTwo, 1U)
        var result = false
        for (chat in directMessages) {
            result = chat.isDelete
        }
        assertEquals(true, result)
    }

    @Test
    fun getMessagesTest() {
        directMessages.clear()
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hello. First message")
        addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
        addDirectMessages(peopleOne, peopleTwo, "How do you do?. Three message")
        addDirectMessages(peopleTwo, peopleOne, "I am fine, thanks. And you?. First message")
        addDirectMessages(peopleOne, peopleTwo, "Me too. First message")
        val result = getMessages(1U, 2U, 4, peopleOne)
        val messages = mutableListOf<Message>()
        for (chat in directMessages) {
            for (message in chat.message!!) {
                if (message.idMessage >= 2U) {
                    messages.add(message)
                }
            }
        }
        assertEquals(messages, result)
    }


}