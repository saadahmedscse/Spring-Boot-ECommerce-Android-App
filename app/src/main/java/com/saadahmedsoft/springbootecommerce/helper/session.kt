package com.drugstore.app.helper

import android.content.Context
import com.google.gson.JsonObject
import com.saadahmedsoft.springbootecommerce.utils.SessionManager

fun Context.getBearerToken(): String = SessionManager.getInstance(this).bearerToken!!
fun Context.getPhone(): String = SessionManager.getInstance(this).number!!
fun Context.getPhoneAsBody(): JsonObject {
    val body = JsonObject()
    body.addProperty("phone", this.getPhone())
    return body
}