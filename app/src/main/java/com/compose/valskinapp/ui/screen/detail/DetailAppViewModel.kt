package com.compose.valskinapp.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.valskinapp.data.Skin
import com.compose.valskinapp.data.SkinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val SkinRepository: SkinRepository): ViewModel() {
    fun getSkinData(idSkin: String): StateFlow<Skin> = MutableStateFlow(SkinRepository.getSkinsId(idSkin)).asStateFlow()

}
class DetailViewModelFactory(private val SkinRepository: SkinRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(SkinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}