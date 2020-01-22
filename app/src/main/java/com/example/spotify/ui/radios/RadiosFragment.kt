package com.example.spotify.ui.radios

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.spotify.R
import com.example.spotify.data.RadioDataSource
import com.example.spotify.data.Status
import com.example.spotify.data.Status.*
import com.example.spotify.data.remote.RadioServiceProvider
import com.example.spotify.databinding.FragmentRadiosBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RadiosFragment : Fragment() {

    private lateinit var binding: FragmentRadiosBinding

    private val popularRadioAdapter = RadiosAdapter()
    private val locationRadioAdapter = RadiosAdapter()

    private val radioDataSource = RadioDataSource()
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radios, container, false)

        binding.recyclerViewPopular.adapter = popularRadioAdapter
        binding.recyclerViewLocation.adapter = locationRadioAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchRadioPage()
    }

    private fun fetchRadioPage() {

        val popularObservable = radioDataSource.fetchPopularRadios()
        val locationObservable = radioDataSource.fetchLocationRadios()

        disposable.add(Observable.combineLatest(
            popularObservable,
            locationObservable,
            RadioPageCombiner()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { renderUI(it) })

    }

    private fun renderUI(radiosFragmentViewState: RadiosFragmentViewState) {

        when (radiosFragmentViewState.popularRadios.status) {
            SUCCESS -> {
                binding.progressPopularRadios.visibility = View.GONE
                popularRadioAdapter.setRadioList(radiosFragmentViewState.popularRadios.data!!)
            }
            LOADING -> {
                binding.progressPopularRadios.visibility = View.VISIBLE

            }
        }
        when (radiosFragmentViewState.locationRadios.status) {
            SUCCESS -> {
                binding.progressLocationRadios.visibility = View.GONE
                locationRadioAdapter.setRadioList(radiosFragmentViewState.locationRadios.data!!)
            }
            LOADING -> {
                binding.progressLocationRadios.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return RadiosFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}