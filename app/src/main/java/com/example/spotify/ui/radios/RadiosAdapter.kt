package com.example.spotify.ui.radios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.R
import com.example.spotify.data.remote.Radio
import com.example.spotify.databinding.ItemRadiosBinding
import com.squareup.picasso.Picasso

class RadiosAdapter : RecyclerView.Adapter<RadiosAdapter.RadioItemViewHolder>() {

    private val radioList = arrayListOf<Radio>()

    fun setRadioList(radioList:List<Radio>){
        this.radioList.clear()
        this.radioList.addAll(radioList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioItemViewHolder=
        RadioItemViewHolder.create(parent)

    override fun getItemCount(): Int = radioList.size

    override fun onBindViewHolder(holder: RadioItemViewHolder, position: Int) =holder.bind(radioList[position])

    class RadioItemViewHolder(private val binding:ItemRadiosBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(radio:Radio){
            binding.textviewRadioTitle.text=radio.radioName
            binding.textViewRadiotBand.text=radio.dial
            Picasso.get().load(radio.logo_small).into(binding.imageViewAvatar)
        }

        companion object {
            fun create(parent: ViewGroup):RadioItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemRadiosBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_radios,
                    parent,
                    false
                )
                return RadioItemViewHolder(binding)
            }
        }
    }
}