package com.example.getandpostlocationadv


//(3) we create a class from the json link information
class UsersData : ArrayList<UsersData.UsersDataItem>(){
    data class UsersDataItem(
        val location: String,
        val name: String
    )
}