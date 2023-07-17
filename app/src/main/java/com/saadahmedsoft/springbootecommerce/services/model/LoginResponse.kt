package com.saadahmedsoft.springbootecommerce.services.model

import com.google.gson.annotations.SerializedName


data class LoginResponse (

  @SerializedName("status"  ) var status  : Boolean? = null,
  @SerializedName("message" ) var message : String?  = null,
  @SerializedName("token"   ) var token   : String?  = null

)