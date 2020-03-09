package com.example.pagingexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.pagingexample.databinding.ActivityMainBinding
import com.example.pagingexample.view.ExampleBasicListActivity
import com.example.pagingexample.view.ExampleGroupieActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.tvBasic.setOnClickListener {
            startActivity(Intent(this, ExampleBasicListActivity::class.java))
        }

        mBinding.tvGroupie.setOnClickListener {
            startActivity(Intent(this, ExampleGroupieActivity::class.java))
        }
    }
}
