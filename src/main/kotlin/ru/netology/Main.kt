package ru.netology

fun main() {
    val peopleOne = People(1U, "Nata", "Наталья")
    val peopleTwo = People(2U, "Artem", "Артем")
    val peopleThree = People(3U, "Vika", "Виктория")
    val peopleFour = People(4U, "Arkadiy", "Аркадий")
    val persons: MutableSet<People> = mutableSetOf(peopleOne, peopleTwo, peopleThree, peopleFour)
    val obj = DirectMessageService
   obj.addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
//    obj.addDirectMessages(peopleOne,peopleThree, "Hola!!!!")
//    obj.addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleThree, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleFour, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleOne, peopleTwo, "How do you do?. Three message")
//    obj.addDirectMessages(peopleTwo, peopleOne, "I am fine, thanks. And you?. First message")
//    obj.addDirectMessages(peopleOne, peopleTwo, "Me too. First message")
    obj.printDirectMessages()
//    obj.printMessage()
//    println("@@@@@@@@@@@@@@@@@@@@@@@@")
//    obj.deleteDirectMessages(peopleOne, peopleTwo)
//    obj.printDirectMessages()
//   println( obj.getDirectMessages(peopleOne))
  // println( obj.getUnreadChatsCount(peopleOne))
  //  val editedMessage = Message(1U, peopleTwo, "EDITED TEXT", 1650023223661, false, true)
obj.editMessage(peopleOne, peopleTwo, 1U, "EDITED TEXT")
    obj.deleteMessage(peopleOne, peopleTwo, 1U)
    println("@@@@@@@@@@@@@@@@@@@@@@@@")
    obj.printDirectMessages()



//    val owner = authorization(persons)
//    openDirectMessage(persons, owner)

}
//
//fun authorization(persons: MutableSet<People>): People {
//    while (true) {
//        print("Авторизация. Введите логин:")
//        val login = readLine()
//        for (user in persons) {
//            if (user.login == login) {
//                println("Добро пожаловать ${user.name}")
//                return user
//            }
//        }
//        println("Пользователь не найден, проверьте правильность ввода логина")
//        continue
//    }
//}
//
//fun openDirectMessage(persons: MutableSet<People>, owner: People) {
//    while (true) {
//        print("Введите имя собеседника:")
//        val companion = readLine()
//        for (user in persons) {
//            if (user.name == companion) {
//                //  DirectMessageService.addDirectMessages(owner, user)
//                return
//            }
//        }
//        println("Собеседник не найден, попробуйте снова")
//        continue
//    }
//}