package com.example.charitable.database


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.sql.*
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.schedule

class DatabaseManager {


    fun connect(): String? {


        try {

            val c = DriverManager.getConnection(
                "jdbc:mysql://192.168.1.120/charitable?serverTimezone=UTC",
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

    fun insertName(str: String):Boolean {
        if (checkName(str))
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
                val r = s.executeQuery("SELECT `Name` ,`u_id` FROM `account` ORDER BY `u_id`")
                //Log.d("ay 7aga", "1-${str}")
                r.last()
                val x=r.getInt("u_id")+1
                r.moveToInsertRow()
                r.updateString("Name",str)
                r.updateInt("u_id",x)
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
    fun checkName(str: String):Boolean {
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
                val r = s.executeQuery("SELECT `Name` FROM `account` WHERE Name = \"${str}\"")
                Log.d("ay 7aga", "1-${str}")
                r.next()
                if (r.isFirst)
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



    fun c2() {
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
                val r = s.executeQuery("SELECT `u_id` FROM `account` WHERE Name = \"Donator\"")
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


}