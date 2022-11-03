package com.hope.bitex.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.hope.bitex.R
import com.hope.bitex.data.ResponseModel.*
import com.hope.bitex.data.model.FakePriceModel
import com.hope.bitex.ui.MainActivity.State.BUY
import com.hope.bitex.ui.MainActivity.State.SELL
import com.hope.bitex.util.getPercentWidth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),View.OnClickListener {
    private enum class State{
        BUY,SELL
    }

    private var state= BUY
    private val viewModel : MainViewModel by  viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
        changeUi()
        observeData()
        setRecycle()
    }

    private fun setRecycle() {

        val data= mutableListOf<FakePriceModel>().apply {
            add(FakePriceModel("7,887.75","0.235",10))
            add(FakePriceModel("7,857.75","0.785",40))
            add(FakePriceModel("7,487.75","0.235",20))
            add(FakePriceModel("7,997.75","0.135",100))
            add(FakePriceModel("7,877.75","0.325",70))
            add(FakePriceModel("7,387.75","0.255",50))
        }
        val maxWidth= getPercentWidth(42)
        recycle1.apply {
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter=PriceAdapter(this@MainActivity,data,maxWidth,R.color.redTextColor,R.color.redProgressBar)
        }
        recycle2.apply {
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter=PriceAdapter(this@MainActivity,data,maxWidth,R.color.greenTextColor,R.color.greenProgressBar)
        }

    }

    private fun observeData() {
        viewModel.tradResponse.observe(this, Observer {res->
            when (res) {
                is Error -> {
                    Toast.makeText(this,res.exception?.message?:"",Toast.LENGTH_SHORT).show()

                }
                is Loading -> {

                }
                is Success -> {
                    Toast.makeText(this,"Success: ${res.data}",Toast.LENGTH_SHORT).show()

                }
            }
        })
    }

    private fun changeUi() {
        when(state){
            BUY -> {
                textBuy.setTextColor(ContextCompat.getColor(this,R.color.grayDark))
                textSell.setTextColor(ContextCompat.getColor(this,R.color.white))
                textBuy.backgroundTintList= ContextCompat.getColorStateList(this, R.color.yellow)
                textSell.backgroundTintList= ContextCompat.getColorStateList(this, R.color.transparent)
            }
            SELL -> {
                textBuy.setTextColor(ContextCompat.getColor(this,R.color.white))
                textSell.setTextColor(ContextCompat.getColor(this,R.color.grayDark))
                textBuy.backgroundTintList= ContextCompat.getColorStateList(this, R.color.transparent)
                textSell.backgroundTintList= ContextCompat.getColorStateList(this, R.color.yellow)
            }
        }
    }

    private fun setListener() {
        textBuy.setOnClickListener(this)
        textSell.setOnClickListener(this)
        btnBuy.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.textBuy->{
                state=BUY
                changeUi()
            }
            R.id.textSell->{
                state=SELL
                changeUi()
            }
            R.id.btnBuy->{
                viewModel.trade("BTC",editAmount.text.toString())
            }
        }

    }
}