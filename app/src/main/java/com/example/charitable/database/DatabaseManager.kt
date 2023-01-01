package com.example.charitable.database


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.charitable.fragments.Login
import java.sql.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.schedule

class DatabaseManager {


    fun connect(): String? {


        try {

            val c = DriverManager.getConnection(
                "jdbc:mysql://192.168.43.158/charitable?serverTimezone=UTC",
                "hassan",
                "hassan12345"
            )
            val s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
            val r = s.executeQuery("SELECT * FROM `users` WHERE 1")

            r.next()
            return ("done")


        } catch (e: SQLException) {
            e.printStackTrace()
            //to return
            return "error2"
        }

        return "error1"
    }

    fun insertName(name: String,email:String,password:String):Boolean {
        if (checkName(name,password))
        {
            return false
        }
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Name` ,`Email` , `Password` , `userID` , `accountID` FROM `users` ORDER BY `userID`")
                //Log.d("ay 7aga", "1-${str}")
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
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Doner Name` ,`Donation id`,`Date`,`Email`," +
                        "`Phone Number`, `Amount`,`Payment Type`,`user_id` FROM `donations` ORDER BY `Donation id`")
                //Log.d("ay 7aga", "1-${str}")
                r.last()
                val x=r.getInt("Donation id")+1

                r.moveToInsertRow()
                r.updateString("Doner Name",name)
                r.updateString("Email",email)
                r.updateString("Phone Number",phone)
                r.updateInt("Amount",amount.toInt())
                r.updateString("Payment Type",payment)
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
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Name` ,`Goal`,`Duration`,`Description`," +
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
    fun checkName(name: String,password: String):Boolean {
        var  flag=false
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Name` , `Password` FROM `users` WHERE Name = \"${name}\"")
                Log.d("ay 7aga", "1-${name}")
                while(r.next())
                if (r.getString("Password")==password)
                {
                    flag=true
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")

            }
            handler.post { }
        }

        Log.d("ay 7aga", "123${flag}")
        Thread.sleep(700)
        return flag
    }
    fun getID(str: String):Int {
        var uid=0
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `u_id` FROM `accounts` WHERE Name = \"${str}\"")
                Log.d("ay 7aga", "1-${str}")
                r.next()
                uid=r.getInt("u_id")
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")

            }
            handler.post {}
        }
        Thread.sleep(700)
        Log.d("Test ID","${uid}")
        return uid
    }
    fun getAllDonations():String {
        var str=""
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Doner Name` , `Amount` , `Payment Type` FROM `donations` ")

                while(!r.isLast)
                {
                    r.next()
                    str=str+String.format("%-40s%-20s",
                        r.getString("Doner Name"),
                        r.getInt("Amount").toString(),
                        )+"\n\n"
                }

            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")

            }
            handler.post {}
        }
        Thread.sleep(700)
        Log.d("Don",str)
        return str
    }
    fun getAllActivities():MutableList<String> {
        var str= mutableListOf("")
        str.remove("")
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Name` ,`Goal`,`Duration`,`Description`," +
                        "`From range`, `To range` , `activityId` FROM `activities` ORDER BY `activityId`")

                while(!r.isLast)
                {
                    r.next()
                    str.add(r.getString("Name"))
                    println(str)
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
    fun getUserDonation():String {
        val handler = Handler(Looper.getMainLooper())
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        var str=""
        executor.execute() {
            try {
                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `Doner Name` ,`Donation id`,`Email`," +
                        "`Phone Number`, `Amount`,`Payment Type`,`user_id` FROM `donations` WHERE `user_id` = \"${Login.uid}\"")
                //Log.d("ay 7aga", "1-${str}")
                while(!r.isLast)
                {
                    r.next()
                    str=str+String.format("%-40s%-20s",
                        r.getString("Doner Name"),
                        r.getInt("Amount").toString(),
                    )+"\n\n"
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")
            }
            handler.post { }
        }
        Thread.sleep(700)
        return str




    fun c2() {
        val handler = Handler(Looper.getMainLooper())

        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute() {
            try {

                val c = DriverManager.getConnection(
                    "jdbc:mysql://192.168.43.158/charitable?serverTimezone=UTC",
                    "hassan",
                    "hassan12345"
                )
                val s =
                    c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                val r = s.executeQuery("SELECT `u_id` FROM `accounts` WHERE Name = \"Donator\"")
                while (!r.isLast){
                r.next()
                    Log.d("ay 7aga", "${r.getString("u_id")}")
                }

            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("ay 7aga", "failed")

            }



            handler.post {

            }
        }
    }


}}