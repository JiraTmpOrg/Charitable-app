package Model

class Doner(email:String, national_ID:Int, phoneNumber:String): User("","",false) {
    var email=email
    val ID=national_ID
    var phoneNumber=phoneNumber
}