package ru.netology

import org.junit.Assert.*
import org.junit.Test
import ru.netology.DirectMessageService.addDirectMessages
import ru.netology.DirectMessageService.deleteDirectMessages
import ru.netology.DirectMessageService.directMessages
import ru.netology.DirectMessageService.getDirectMessages
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
    fun deleteMessage() {
    }
}