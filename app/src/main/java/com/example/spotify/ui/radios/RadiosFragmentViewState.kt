package com.example.spotify.ui.radios

import com.example.spotify.data.remote.Radio
import com.example.spotify.data.Resource

data class RadiosFragmentViewState(
    val popularRadios: Resource<List<Radio>>,
    val locationRadios: Resource<List<Radio>>
)

