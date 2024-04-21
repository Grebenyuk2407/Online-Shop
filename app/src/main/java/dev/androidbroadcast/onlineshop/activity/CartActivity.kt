package dev.androidbroadcast.onlineshop.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dev.androidbroadcast.onlineshop.Adapter.CartAdapter
import dev.androidbroadcast.onlineshop.Helper.ChangeNumberItemsListener
import dev.androidbroadcast.onlineshop.Helper.ManagmentCart
import dev.androidbroadcast.onlineshop.R
import dev.androidbroadcast.onlineshop.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {

                override fun onChanged() {
                    calculateCart()
                }

            })

        with(binding) {
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.0
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100
        with(binding) {
            SubtotalTxt.text = "$$itemTotal"
            totalTaxTxt.text = "$$tax"
            freeDeliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"


        }


    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}