package com.example.viewmodelandlivedataexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 LiveData를 가지고 있다.
class MyNumViewModel : ViewModel() {

    // LiveData의 두 종료
    // MutableLiveData - 수정 가능한 데이터
    // LiveData - 수정이 불가능, 읽기 전용 데이터

    // 내부에서 설정하는 자료형은 Mutable
    // 즉 내부에서만 수정이 가능하고 외부에서 접근할때는 수정이 불가능하도록 해준다.
    private val _currentValue = MutableLiveData<Int>()

    // 변경이 불가능한 데이터를 가져 올때 _(언더바)가 없이 설정
    // 외부에서 데이터에 접근할 수 있도록 private이 아닌 public으로 선언
    // LiveData에 직접 접근하는 것이 아닌 ViewModel을 통해 데이터를 가져올 수 있도록 설정
    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정정
   init {
        Log.d(TAG,"MyNumberViewModel - 생성자 호출 ")
        _currentValue.value = 0
    }

    // 유저가 한 행동에 따라 덧셈과 뺄셈으로 구분하여 적용하는 메서드
    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)

            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }


    companion object {
        const val TAG: String = "로그그"
    }
}