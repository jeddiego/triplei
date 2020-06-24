package com.diego.lara.views

import com.diego.lara.R
import com.diego.lara.databinding.ItemTiendaBinding
import com.diego.lara.models.Tiendas
import com.xwray.groupie.databinding.BindableItem

class TiendasItemView(
    val tiendas: Tiendas
) : BindableItem<ItemTiendaBinding>() {
    override fun getLayout(): Int = R.layout.item_tienda

    override fun bind(bind: ItemTiendaBinding, position: Int) {
        bind.tvCadena.text = tiendas.cadena
        bind.tvDeterminante.text = tiendas.determinante
        bind.tvSucursal.text = tiendas.sucursal
        bind.tvLatitud.text = tiendas.latitud.toString()
        bind.tvLongitud.text = tiendas.longitud.toString()
    }

}