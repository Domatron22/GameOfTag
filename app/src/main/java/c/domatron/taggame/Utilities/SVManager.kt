package c.domatron.taggame.Utilities

import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException
import java.net.NetworkInterface
import java.util.*

/* Author: Dominic Triano
 * Date: 4/30/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * Functions useful when contacting the server
 *
 */
val url = "http://192.168.86.30:8080"

//Tag another Player
fun tagPlayer(tagN : String, userId : String)
{
    //url of server
    //Build Statement to send
    val request = Request.Builder().url(url + "/tag")
        .header("user-id", userId)
        .addHeader("tag-id",tagN).build()

    val client = OkHttpClient()
    //Start a new call to link with the server
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })

    runBlocking {
        delay(1000)
    }

}

//Register your Player
fun register(userId : String, groupId : String, mac : String)
{
    val request = Request.Builder().url(url + "/register")
        .header("user-id", userId)
        .addHeader("group-id",groupId)
        .addHeader("mac", mac).build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object: Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })
}

//Join an existing group
fun joinGroup(userId : String, mac : String, grpCode : String) : Boolean
{
    var flag = false
    val request = Request.Builder().url(url + "/join-grp")
        .header("user-id", userId)
        .addHeader("group-id",grpCode)
        .addHeader("mac", mac).build()


    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
            flag = true
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
            flag = false
        }
    })


    runBlocking {
        delay(1000)
    }

    return flag
}

fun tagGet(mac: String) : String?
{
    var tag : String? = null
    val request = Request.Builder().url(url + "/get-tag")
        .header("mac", mac).build()


    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
            tag = body
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })

    runBlocking {
        delay(1000)
    }

    return tag
}

//Register a new group
fun regGroup(userId : String, mac : String, grpCode : String) : String?
{
    var grp : String? = null
    val request = Request.Builder().url(url + "/reg-grp")
        .header("user-id", userId)
        .addHeader("group-id",grpCode)
        .addHeader("mac", mac).build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
            grp = body
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })


    runBlocking {
        delay(1000)
    }

    return grp

}

//Check the status of the user
fun chkStatus(userId : String): String?
{
    var status : String? = null
    val request = Request.Builder().url(url + "/check-status")
        .header("user-id", userId).build()


    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
            status = body
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })

    runBlocking {
        delay(1000)
    }

    return status
}

fun chkUser(mac : String) : String
{
    val request = Request.Builder().url(url + "/check-user")
        .header("mac", mac).build()

    var uName : String = ""

    val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                uName = body!!
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute Request")
            }
        })

    runBlocking {
        delay(1000)
    }

    return uName
}

fun tagSet(mac : String, tid : String) : Boolean
{
    val request = Request.Builder().url(url + "/set-tag")
        .header("mac", mac)
        .addHeader("tid", tid).build()


    val client = OkHttpClient()
    client.newCall(request).enqueue(object: Callback {
        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            println(body)
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Failed to Execute Request")
        }
    })

    runBlocking {
        delay(1000)
    }

    return true
}
//
//fun startGame(mac : String)
//{
//    val request = Request.Builder().url(url + "/start-game")
//        .header("mac", mac).build()
//
//
//    val client = OkHttpClient()
//    client.newCall(request).enqueue(object: Callback {
//        override fun onResponse(call: Call, response: Response) {
//            val body = response?.body()?.string()
//            println(body)
//        }
//
//        override fun onFailure(call: Call, e: IOException) {
//            println("Failed to Execute Request")
//        }
//    })
//
//    runBlocking {
//        delay(1000)
//    }
//}



//Find the leaders of the group
//fun leader(grpCode : String)
//{
//    val url = "http://192.168.86.39:8080/leader"
//    val request = Request.Builder().url(url)
//        .header("group-id", grpCode).build()
//
//    val client = OkHttpClient()
//    client.newCall(request).enqueue(object: Callback {
//        override fun onResponse(call: Call, response: Response) {
//            val body = response?.body()?.string()
//            println(body)
//        }
//
//        override fun onFailure(call: Call, e: IOException) {
//            println("Failed to Execute Request")
//        }
//    })
//}

//Get the mac address in a weird way
fun getMacAddr(): String {
    try {
        val all = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (nif in all) {
            if (!nif.getName().equals("wlan0")) continue

            val macBytes = nif.getHardwareAddress() ?: return ""

            val res1 = StringBuilder()
            for (b in macBytes) {
                //Taking a signed byte it will do a sign ext, the 255 does a bitwise and in order
                //to turn off the Higher order bits. If this is not done, the Higher order bits will all be 1.
                //The 255 has all zeros before its last 8 bits, so it flips all of the ones in the higher order to a 0
                //Yes this seems unneccisary, but it cannot
                var x = Integer.toHexString(b.toInt() and 255)
                res1.append(x + ":")
            }

            if (res1.length > 0) {
                res1.deleteCharAt(res1.length - 1)
            }
            return res1.toString()
        }
    } catch (ex: Exception) {
    }

    return "02:00:00:00:00:00"
}