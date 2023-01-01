package Model.database


import android.os.Handler
import android.os.Looper
import android.util.Log
import Control.Login
import java.sql.*
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DatabaseManager {

val ip="192.168.1.120"
    companion object {
        var db = DatabaseManager()
    }
    fun s(): Statement {
        val c = DriverManager.getConnection(
            "jdbc:mysql://${ip}/charitable?serverTimezone=UTC",
            "hassan",
            "hassan12345"
        )
        val s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
        return s
    }


    fun insertName(name: String,email:String,password:String):Boolean {
        if (checkName(name,password)!=0)
        {
            return false
        }
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {

                val r = s().executeQuery("SELECT `Name` ,`Email` , `Password` , `userID` , `accountID` FROM `users` ORDER BY `userID`")
                var x:Int
                if(r.last())
                {r.last()
                    x=r.getInt("userId")+1}
                else{
                    x=1}
                r.moveToInsertRow()
                r.updateString("Name",name)
                r.updateString("Email",email)
                r.updateString("Password",password)
                r.updateInt("userID",x)
                r.updateInt("accountId",2)
                r.insertRow()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")
            }
            handler.post { }
        }
        Thread.sleep(700)
        return true
    }
    fun insertDonation(name: String,email:String,phone:String,amount:String,payment:String):Boolean {
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Doner Name` ,`Donation id`,`Date`,`Email`," +
                        "`Phone Number`, `Amount`,`Payment Type`,`user_id` FROM `donations` ORDER BY `Donation id`")
                //Log.d("ay 7aga", "1-${str}")
                r.last()
                var x:Int
                if(r.last())
                {r.last()
                    x=r.getInt("Donation id")+1}
                else{
                    x=1}
                r.moveToInsertRow()
                r.updateString("Doner Name",name)
                r.updateString("Email",email)
                r.updateString("Phone Number",phone)
                r.updateInt("Amount",amount.toInt())
                var p_id =0
                if(payment=="Card")
                    p_id=1
                r.updateInt("Payment Type",p_id)
                r.updateInt("user_id", Login.uid)
                r.updateInt("Donation id",x)
                val sdf=SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                val date=java.util.Date()
                println(sdf.format(date))
                r.updateString("Date",sdf.format(date))
                r.insertRow()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")
            }
            handler.post { }
        }
        Thread.sleep(700)
        return true
    }
    fun addActivity(name: String,goal:String,duration:String,description:String,from:Int,to:Int):Boolean {
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Name` ,`Goal`,`Duration`,`Description`," +
                        "`From range`, `To range` , `activityId` FROM `activities` ORDER BY `activityId`")
                //Log.d("ay 7aga", "1-${str}")
                var x:Int
                if(r.last())
                {r.last()
                x=r.getInt("activityId")+1}
                else{
                   x=1}
                r.moveToInsertRow()
                r.updateString("Name",name)
                r.updateString("Goal",goal)
                r.updateString("Duration",duration)
                r.updateString("Description",description)
                r.updateInt("From range", from)
                r.updateInt("To range",to)
                r.updateInt("activityId",x)
                r.insertRow()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")
            }
            handler.post { }
        }
        Thread.sleep(700)
        return true
    }
    fun checkForAge(age:Int,activityName: String):Boolean{
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        var flag=true
        executor.execute() {

            try {
                val r = s().executeQuery("SELECT `Name` ,`From range`, `To range` FROM `activities` WHERE Name = \"${activityName}\"")
                r.next()

                val min = r.getInt("From range")
                val max = r.getInt("To range")
                if(age>max||age<min)
                    flag=false
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")
            }
            handler.post { }
        }
        Thread.sleep(700)
        return flag
    }
    fun applyForActivity(activityName:String,name:String,Email:String,phone:String,nationalID:String,branch:String,age:Int):Boolean {
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Activity` , `Name` ,`Email`,`phone`,`NationalID`," +
                        "`branch`, `age` , `applicationId` FROM `applicationsforactivities` ORDER BY `applicationId`")
                var x:Int
                if(r.last())
                {r.last()
                    x=r.getInt("applicationId")+1}
                else{
                    x=1}
                r.moveToInsertRow()
                r.updateString("Activity",activityName)
                r.updateString("Name",name)
                r.updateString("Email",Email)
                r.updateString("phone",phone)
                r.updateString("NationalId", nationalID)
                r.updateString("branch",branch)
                r.updateInt("age",age)
                r.updateInt("applicationId",x)
                r.insertRow()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            handler.post { }
        }
        Thread.sleep(700)
        return true
    }
    fun checkName(name: String,password: String):Int {
        var  flag=0
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Name` , `Password` , `accountID` FROM `users` WHERE Name = \"${name}\"")
                while(r.next())
                if (r.getString("Password")==password)
                {
                    flag=1
                    if (r.getString("accountID")=="1")
                        flag=2
                }
            } catch (e: SQLException) {
                e.printStackTrace()

            }
            handler.post { }
        }

        Thread.sleep(700)
        return flag
    }
    fun getID(str: String):Int {
        var uid=0
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `userID` FROM `users` WHERE Name = \"${str}\"")
                r.next()
                uid=r.getInt("userId")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            handler.post {}
        }
        Thread.sleep(700)
        return uid
    }
    fun getAllDonations():MutableList<String> {
        var str= mutableListOf("")
        str.remove("")
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Doner Name` , `Amount` , `Payment Type` , `Date` FROM `donations` ")

                while(!r.isLast)
                {
                    r.next()
                    var paymentTypes="Cash"
                    if (r.getInt("Payment Type")==1)
                        paymentTypes="Card"
                    str.add(r.getString("Doner Name")+"\n"+r.getString("Amount")+" EGP\n"
                            +paymentTypes+"\n"+r.getString("Date"))
                }

            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")

            }
            handler.post {}
        }
        Thread.sleep(700)
        return str
    }
    fun getAllActivities():MutableList<String> {
        var str= mutableListOf("")
        str.remove("")
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val r = s().executeQuery("SELECT `Name` ,`Goal`,`Duration`,`Description`," +
                        "`From range`, `To range` , `activityId` FROM `activities` ORDER BY `activityId`")

                while(!r.isLast)
                {
                    r.next()
                    str.add(r.getString("Name"))
                    println(str)
                }

            } catch (e: SQLException) {
                e.printStackTrace()

            }
            handler.post {}
        }
        Thread.sleep(700)
        return str
    }
    fun getUserDonation():MutableList<String> {
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        var str= mutableListOf("")
        str.remove("")
        executor.execute() {
            try {

                val r = s().executeQuery("SELECT `Doner Name` ,`Donation id`,`Email`," +
                        "`Phone Number`, `Amount`,`Payment Type`,`user_id` , `Date` FROM `donations` WHERE `user_id` = \"${Login.uid}\"")
                while(!r.isLast)
                {
                    r.next()
                    var paymentTypes="Cash"
                    if (r.getInt("Payment Type")==1)
                        paymentTypes="Card"
                    str.add(r.getString("Doner Name")+"\n"+r.getString("Amount")+" EGP\n"
                            +paymentTypes+"\n"+r.getString("Date"))
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            handler.post { }
        }
        Thread.sleep(700)
        return str


}}