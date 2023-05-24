package com.compose.valskinapp.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.valskinapp.data.Skin
import com.compose.valskinapp.data.SkinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val skinRepository: SkinRepository): ViewModel() {
    fun getSkin(skin: Skin): StateFlow<List<Skin>> = MutableStateFlow(skinRepository.getSkin(skin)).asStateFlow()

}
class DetailViewModelFactory(private val skinRepository: SkinRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(skinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
class DetailAppViewModel {
}