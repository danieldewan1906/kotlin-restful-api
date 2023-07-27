package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.UserRepository
import programmer.zaman.now.kotlin.restful.service.UserService

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService{

    override fun getUserByUser(username: String): User {
        return userRepository.findByUsername(username).orElseThrow { NotFoundException() }
    }

    override fun getAllUser(): List<User> {
        return userRepository.findAll()
    }

    override fun createUser(user: User) {
        userRepository.save(user)
    }
}