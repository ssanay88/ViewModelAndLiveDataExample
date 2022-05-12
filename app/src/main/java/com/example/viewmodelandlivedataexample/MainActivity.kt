package com.example.viewmodelandlivedataexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelandlivedataexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var myNumViewModel: MyNumViewModel    // 뷰모델 객체를 늦은 초기화 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModelProvider를 통해 뷰모델 가져오기
        // 라이프사이클을 가지고 있는 자기 자신을 넣어줌
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델 객체 생성
        myNumViewModel = ViewModelProvider(this).get(MyNumViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙
        // 라이브데이터인 currentValue에 변경이 있을 경우 관찰하여 바로 적용
        myNumViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.resultTextView.text = it.toString()
        })

        // 리스너 연결
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)

    }

    // 클릭 이벤트 발생 시 실행
    override fun onClick(view: View?) {
        val userInput: Int = binding.inputNumEditText.text.toString().toInt()

        when(view) {
            binding.plusBtn ->
                myNumViewModel.updateValue(actionType = ActionType.PLUS, input = userInput)

            binding.minusBtn ->
                myNumViewModel.updateValue(actionType = ActionType.MINUS, input = userInput)
        }

    }


    companion object {
        const val TAG: String = "로그그"
    }
}