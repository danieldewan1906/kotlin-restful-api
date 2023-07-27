package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.entity.User

interface UserService {

    fun getUserByUser(username: String): User

    fun getAllUser(): List<User>

    fun createUser(user: User)
}