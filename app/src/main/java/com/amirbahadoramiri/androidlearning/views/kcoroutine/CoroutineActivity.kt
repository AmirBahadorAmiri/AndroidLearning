package com.amirbahadoramiri.androidlearning.views.kcoroutine

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amirbahadoramiri.androidlearning.bases.BaseActivity
import com.amirbahadoramiri.androidlearning.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CoroutineActivity : BaseActivity() {

    lateinit var binding : ActivityCoroutineBinding
    lateinit var textModel: TextModel
    lateinit var textViewModel: TextViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewCompat()

        textViewModel = ViewModelProvider(this).get(TextViewModel::class.java)
        textViewModel.getMutableLiveData()?.observe(this,object : Observer<TextModel> {
            override fun onChanged(value: TextModel) {
                binding.data = value
            }
        })
        binding.button.setOnClickListener {

            textModel = CoroutineActivity.TextModel("text 1","text 2","text 3")

//            CoroutineScope(Dispatchers.Default).launch {
//                setText(textModel)
//            }

//            GlobalScope.launch {
//                setText(textModel)
//            }
//
            CoroutineScope(Dispatchers.IO).launch {
                setText(textModel)
            }

        }

    }

    suspend fun setText(textModel: TextModel) {
        delay(3.seconds)
//        withContext(Dispatchers.Main){
//            binding.textView1.text = str
//        }
//
//        MainScope().launch {
//            binding.textView2.text = str
//        }

//        lifecycleScope.launch {
//            binding.data = textModel
//        }

        textViewModel.getMutableLiveData()?.value = textModel

    }

    data class TextModel(var str1: String,var str2: String,var str3: String)

}
