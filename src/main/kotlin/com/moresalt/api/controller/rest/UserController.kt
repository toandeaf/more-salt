package com.moresalt.api.controller.rest

import com.moresalt.api.model.Ambition
import com.moresalt.api.model.TasteProfile
import com.moresalt.api.model.User
import com.moresalt.api.service.UserService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
class UserController {

    @Inject
    lateinit var userService: UserService

    @GET
    @Path("/{userId}")
    fun fetchUser(@PathParam("userId") userId: Long): User? {
        return userService.fetchUser(userId)
    }

    @POST
    fun createUser(user: User) {
        return userService.createUser(user)
    }

    @DELETE
    fun deleteUser(user: User) {
        return userService.deleteUser(user)
    }

    @PUT
    fun updateUser(user: User) {
        return userService.updateUser(user)
    }

    @GET
    @Path("/test")
    fun testUser(): String {
        val user = User()
        user.email = "test@email.com"
        user.password = "testpass"
        user.username = "testuser"

        val tasteProfile = TasteProfile()
        tasteProfile.bitterness = 5
        tasteProfile.sweetness = 5
        user.tasteProfile = tasteProfile

        val ambition = Ambition()
        ambition.complexity = "COMPLEX"
        ambition.instructions = "HEAVY"
        ambition.social = "SOCIAL"
        ambition.variety = "VARIOUS"
        user.ambitions = ambition

        userService.createUser(user)

        return "Worked!"
    }
}