package challenge.exercise.kotlinsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import challenge.exercise.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TriviaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (!::adapter.isInitialized) {
            adapter = TriviaAdapter()
            recyclerView.adapter = adapter
            adapter.setData(MockRepo().triviaQuestions)
        }
    }
}
