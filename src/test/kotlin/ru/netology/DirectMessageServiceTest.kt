package ru.netology

import org.junit.Assert.assertTrue
import org.junit.Test
import ru.netology.DirectMessageService.addDirectMessages
import ru.netology.DirectMessageService.directMessages

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
        val peopleOne = People(1U, "Nata", "Наталья")
        val peopleTwo = People(2U, "Artem", "Артем")
        addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
        for(chat in directMessages){
            chat.isDelete = true
        }
        val result = addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
        assertTrue(result)
    }

    @Test
    fun deleteDirectMessages() {
    }

    @Test
    fun getDirectMessages() {
    }

    @Test
    fun getUnreadChatsCount() {
    }

    @Test
    fun deleteMessage() {
    }
}