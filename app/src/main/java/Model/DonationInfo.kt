package Model

import java.util.Date

class DonationInfo(donerName:String, email:String, phoneNumber:String, amount:Int, paymentType:String, date:Date)
{
    val name=donerName
    val email=email
    val phoneNumber=phoneNumber
    val amount=amount
    val paymentType=paymentType
    val date=date
}