package Model

import java.util.Date

class Receiver(national_ID:Int, lastReceived:Date,address:String,city:String,category:String):
    User("","",false) {
   val ID=national_ID
    var lastReceived=lastReceived
    var address=address
    var city=city
    var category=category
}