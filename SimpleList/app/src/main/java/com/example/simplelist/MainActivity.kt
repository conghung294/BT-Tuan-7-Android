package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo các view
        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        // Thiết lập sự kiện khi nhấn nút Show
        btnShow.setOnClickListener {
            val input = edtNumber.text.toString()
            val n = input.toIntOrNull()

            if (n == null || n < 1) {
                // Hiển thị thông báo lỗi nếu đầu vào không hợp lệ
                tvError.text = "Please enter a valid positive integer."
                listView.adapter = null
            } else {
                tvError.text = ""  // Xóa thông báo lỗi nếu có
                val selectedId = radioGroup.checkedRadioButtonId
                val resultList = when (selectedId) {
                    R.id.radioEven -> getEvenNumbers(n)
                    R.id.radioOdd -> getOddNumbers(n)
                    R.id.radioSquare -> getSquareNumbers(n)
                    else -> emptyList()
                }

                // Gán dữ liệu vào ListView
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                listView.adapter = adapter
            }
        }
    }

    // Hàm lấy danh sách các số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Hàm lấy danh sách các số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Hàm lấy danh sách các số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
