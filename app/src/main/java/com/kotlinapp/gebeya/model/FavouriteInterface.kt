package com.kotlinapp.gebeya.model

interface FavouriteInterface {
    fun saveFavourite(idProduct: String, idUser: String, pname: String, price: Int, image: String)
    fun deleteFavourite(idProduct: String, idUser: String)
}