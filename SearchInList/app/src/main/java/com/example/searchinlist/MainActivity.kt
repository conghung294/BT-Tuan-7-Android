package com.example.searchinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var filteredList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyen Van A", "20173001"),
            Student("Tran Thi B", "20173002"),
            Student("Le Van C", "20173003"),
            // Thêm các sinh viên khác nếu cần
        )
        filteredList = studentList.toMutableList()

        // Thiết lập RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(filteredList)
        recyclerView.adapter = studentAdapter

        // Ô tìm kiếm
        val edtSearch = findViewById<EditText>(R.id.edtSearch)
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim().lowercase(Locale.getDefault())
                if (keyword.length > 2) {
                    // Lọc danh sách sinh viên theo từ khóa
                    filteredList.clear()
                    filteredList.addAll(studentList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(keyword) ||
                                it.mssv.lowercase(Locale.getDefault()).contains(keyword)
                    })
                } else {
                    // Hiển thị toàn bộ danh sách nếu từ khóa < 3 ký tự
                    filteredList.clear()
                    filteredList.addAll(studentList)
                }
                // Cập nhật danh sách hiển thị
                studentAdapter.updateList(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
