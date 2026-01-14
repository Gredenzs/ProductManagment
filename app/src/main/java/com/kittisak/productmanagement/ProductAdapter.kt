package com.kittisak.productmanagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kittisak.productmanagement.databinding.ItemProductBinding


class ProductAdapter (
    private var products: List<Product>,
    private var onItemClick: (Product) -> Unit,
    private var onEditClick: (Product) -> Unit,
    private var onDeleteClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewFHolder>() {

    inner class ProductViewFHolder(private val binding: ItemProductBinding) :
RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {

            if (!product.imagePath.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(product.imagePath)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .into(binding.imageViewProduct)
            }else{

                binding.imageViewProduct.setImageResource(R.drawable.ic_image_placeholder)
            }

            binding.textViewName.text = product.name
            binding.textviewPrice.text = "ราคา: ${product.price} บาท"
            binding.textViewQuantity.text = "จำนวน: ${product.quantity} ชิ้น"
            binding.root.setOnClickListener { onItemClick(product) }
            binding.ButtomEdit.setOnClickListener { onEditClick(product) }
            binding.ButtomDelete.setOnClickListener { onDeleteClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewFHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return  ProductViewFHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewFHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
    fun updateProducts(newProducts: List<Product>){
        products = newProducts
        notifyDataSetChanged()
    }
}