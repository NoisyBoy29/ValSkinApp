package com.compose.valskinapp.data

class SkinRepository {
    fun getSkin(): List<Skin> {
        return SkinData.skins
    }

    fun searchSkin(query: String): List<Skin>{
        return SkinData.skins.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getSkinsId(id :String) : Skin =  SkinData.skins.find{
        it.id == id
    } as Skin
}