package com.example.logiqlinkapplication

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logiqlinkapplication.adapter.ClickListener
import com.example.logiqlinkapplication.adapter.InventoryAdapter
import com.example.logiqlinkapplication.apiService.RetrofitObject
import com.example.logiqlinkapplication.databinding.ActivityMainBinding
import com.example.logiqlinkapplication.room.InventoryDatabase
import com.example.logiqlinkapplication.room.InventoryRepository
import com.example.logiqlinkapplication.viewModel.InventoryViewModel
import com.example.logiqlinkapplication.viewModel.ViewModelFactory
import com.example.techtestapp.utils.SharedPref

class MainActivity : AppCompatActivity(), ClickListener {
    private  lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: InventoryViewModel
    private var soldCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPref.init(applicationContext)
        val settings: SharedPreferences =
            applicationContext.getSharedPreferences(
                applicationContext.packageName,
                MODE_PRIVATE
            )
        settings.edit().commit()


        val apiService = RetrofitObject.apiService
        val userDao = InventoryDatabase.getUserDatabase(this).inventoryDao()
        val repository = InventoryRepository(userDao, apiService)

        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[InventoryViewModel::class.java]

        viewModel.onGetInventory()
        viewModel.getAllInventory()

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        viewModel.inventoryData.observe(this) { data ->
            for (item in data ) {
                viewModel.insertUserData(item)
            }

            val adapter = InventoryAdapter( data, this)
            binding.recyclerview.adapter = adapter
        }

    }

    override fun onClickedToReport() {
        val email = "bossman@bosscompany.com"
        val subject = "Daily Sales Report"
        val message = "Items sold today: $soldCount"

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Choose an email client"))
        } else {
            Toast.makeText(this, "No email app installed", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClickToAdd(count: Int, temCount: TextView) {
        if (soldCount < count){
            soldCount++
            temCount.text = soldCount.toString()
        }else{
            Toast.makeText(this, "You can not add more than $soldCount items", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickToSub(count: Int, temCount:TextView) {
            if (soldCount > 1){
                soldCount--
                temCount.text = soldCount.toString()
            }else{
                Toast.makeText(this, "At least $soldCount add", Toast.LENGTH_SHORT).show()
            }
    }
}