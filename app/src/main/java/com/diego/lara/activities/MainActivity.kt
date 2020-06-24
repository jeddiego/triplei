package com.diego.lara.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diego.lara.R
import com.diego.lara.databinding.ActivityMainBinding
import com.diego.lara.di.components.DaggerViewModelComponent
import com.diego.lara.di.modules.ContextModule
import com.diego.lara.models.Tiendas
import com.diego.lara.viewModel.TiendasViewModel
import com.diego.lara.views.TiendasItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    var length = 0
    var oldLength = 0
    var flagLocalSearch = false
    var queryFlagLocalSearch = ""
    @Inject
    lateinit var viewModel: TiendasViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapterMoviesList = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is TiendasItemView) {
/*                val sheet = ImageAndRatingsDialogFragment.newInstance(item.movies)
                sheet.show(supportFragmentManager, sheet.simpleClassName())*/
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.appBar.toolbar)
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        binding.viewModel = viewModel
        bindViewModel()
        initRecyclerView()
    }

    private fun bindViewModel() {
        viewModel.tiendas.observe(this, Observer(this::setTiendas))
    }

    private fun setTiendas(listTiendas: List<Tiendas>?) {
        adapterMoviesList.apply {
            clear()
            listTiendas?.forEach {
                add(TiendasItemView(it))
            }
        }
    }

    private fun initRecyclerView() {
        binding.frameLayout.item_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMoviesList
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager =
            this.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =
            menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                localSearch(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                oldLength = length
                length = query.length

                if (oldLength > length) {
                    localSearch(query)
                }
                return false
            }
        })
        return true
    }
    private fun localSearch(query: String) {
        if (query.length > 2) {
            viewModel.searchTiendaBySucursal(query, onlyLocal = true, findTiendaOnline = false)
        } else {
            viewModel.searchTiendaBySucursal("", onlyLocal = true, findTiendaOnline = false)
        }
        flagLocalSearch = true
        queryFlagLocalSearch = query
    }

}