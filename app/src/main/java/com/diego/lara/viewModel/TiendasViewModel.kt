package com.diego.lara.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.diego.lara.di.components.DaggerRepositoryComponent
import com.diego.lara.domain.TiendasRepository
import com.diego.lara.models.Tiendas
import javax.inject.Inject

class TiendasViewModel: ViewModel() {
    @Inject
    lateinit var repository: TiendasRepository
    private val query = MutableLiveData<String>()
    private var onlyLocal = false
    private var findTiendaOnline = false
    val tiendas: LiveData<List<Tiendas>> = Transformations.switchMap(
        query,
        ::temp
    )

    private fun temp(name: String) = repository.getData(name, onlyLocal, findTiendaOnline)

    init {
        DaggerRepositoryComponent.builder()
            .build().inject(this)
        query.value = ""
    }

    fun searchTiendaBySucursal(name: String, onlyLocal: Boolean, findTiendaOnline: Boolean) = apply {
        this.onlyLocal = onlyLocal
        this.findTiendaOnline = findTiendaOnline
        query.value = name
    }

}