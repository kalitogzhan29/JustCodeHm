package com.example.justcode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.justcode.databinding.ItemCountryBinding

class CountryListAdapter(
    private val items:List<String>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       holder.bindView(items[position])
    }



    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class CountryViewHolder(private val binding: ItemCountryBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindView(item:String){
                binding.root.text=item
            }

    }
}