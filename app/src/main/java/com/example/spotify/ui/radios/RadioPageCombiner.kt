package com.example.spotify.ui.radios

import com.example.spotify.data.remote.Radio
import com.example.spotify.data.Resource
import io.reactivex.functions.BiFunction

class RadioPageCombiner : BiFunction<Resource<List<Radio>>, Resource<List<Radio>>,RadiosFragmentViewState>{
    override fun apply(
        popularRadios: Resource<List<Radio>>,
        locationRadios: Resource<List<Radio>>
    ): RadiosFragmentViewState {
        return RadiosFragmentViewState(popularRadios,locationRadios)
    }

}