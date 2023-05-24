package com.compose.valskinapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.valskinapp.data.Skin
import com.compose.valskinapp.data.SkinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeAppViewModel (private val repository: SkinRepository) : ViewModel() {
    private val _groupedSkin = MutableStateFlow(
        repository.getSkin()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedSkin: StateFlow<Map<Char, List<Skin>>> get() = _groupedSkin

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedSkin.value = repository.searchSkin(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class ViewModelFactory(private val repository: SkinRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeAppViewModel::class.java)) {
            return HomeAppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}