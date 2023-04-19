package com.example.time_stop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.time_stop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뒤로가기 버튼을 누른 시각 저장
    var inTime = 0L

    //멈춘 시각 저장
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //start 버튼 클릭시 이벤트 설정
        binding.startBtn.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            //start 버튼 클릭시 세 버튼의 활성화 여부
            binding.startBtn.isEnabled = false
            binding.stopBtn.isEnabled = true
            binding.resetBtn.isEnabled = true

        }

        //stop 버튼 클릭시 이벤트 설정
        binding.stopBtn.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            //stop 버튼 클릭시 세 버튼의 활성화 여부
            binding.startBtn.isEnabled = true
            binding.stopBtn.isEnabled = false
            binding.resetBtn.isEnabled = true

        }

        //reset 버튼 클릭시 이벤트 설정
        binding.resetBtn.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            //reset 버튼 클릭시 세 버튼의 활성화 여부
            binding.startBtn.isEnabled = true
            binding.stopBtn.isEnabled = true
            binding.resetBtn.isEnabled = false

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            //뒤로가기 버튼을 처음 눌렀거나, 누른지 3초가 지난경우
            if (System.currentTimeMillis() - inTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show()
                inTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}