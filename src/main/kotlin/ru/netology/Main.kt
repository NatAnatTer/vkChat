package ru.netology

fun main() {
    val peopleOne = People(1U, "Nata", "Наталья")
    val peopleTwo = People(2U, "Artem", "Артем")
    val peopleThree = People(3U, "Vika", "Виктория")
    val peopleFour = People(4U, "Arkadiy", "Аркадий")
    val persons: MutableSet<People> = mutableSetOf(peopleOne, peopleTwo, peopleThree, peopleFour)

    val owner = authorization(persons)
    openDirectMessage(persons, owner)

}

fun authorization(persons: MutableSet<People>): People {
    while (true) {
        print("Авторизация. Введите логин:")
        val login = readLine()
        for (user in persons) {
            if (user.login == login) {
                println("Добро пожаловать ${user.name}")
                return user
            }
        }
        println("Пользователь не найден, проверьте правильность ввода логина")
        continue
    }
}

fun openDirectMessage(persons: MutableSet<People>, owner: People) {
    while (true) {
        print("Введите имя собеседника:")
        val companion = readLine()
        for (user in persons) {
            if (user.name == companion) {
                DirectMessageService.addDirectMessages(owner, user)
                return
            }
        }
        println("Собеседник не найден, попробуйте снова")
        continue
    }
}