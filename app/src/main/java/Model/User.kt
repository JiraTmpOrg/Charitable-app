package Model

abstract class User(name: String, id: String, isAdmin: Boolean) {
    val isAdmin = isAdmin
    val name = name
    val id = id
    fun getIsAdmin(): Boolean {
        return isAdmin
    }
}
