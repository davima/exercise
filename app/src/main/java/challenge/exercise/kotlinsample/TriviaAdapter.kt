package challenge.exercise.kotlinsample

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import challenge.exercise.R

class TriviaAdapter: ListAdapter<TriviaQuestion, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    private val data = mutableListOf<TriviaQuestion>()

    fun setData(data: List<TriviaQuestion>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): TriviaQuestion {
        return data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return TriviaViewHolder(parent.context, LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TriviaViewHolder).bindTo(data[position])
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TriviaQuestion>() {
            override fun areItemsTheSame(p0: TriviaQuestion, p1: TriviaQuestion): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(p0: TriviaQuestion, p1: TriviaQuestion): Boolean {
                return p0.question == p1.question && p0.question == p1.question && p0.answered != p1.answered
            }

        }

    }

    inner class TriviaViewHolder(private val context: Context, view: View): RecyclerView.ViewHolder(view) {
        private lateinit var item: TriviaQuestion

        fun bindTo(item: TriviaQuestion) {
            this.item = item
            itemView.findViewById<TextView>(R.id.question).text = item.question
            if (item.answered)
                itemView.setBackgroundColor(Color.GREEN)
            itemView.setOnClickListener {
                item.answered = true
                notifyDataSetChanged()
                showAnswer(item)
            }
        }

        private fun showAnswer(item: TriviaQuestion) {
            Log.d("ASd", "answered: ${item.answered}")
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(item.question)
            dialog.setMessage(item.answer)
            dialog.setNeutralButton(R.string.ok, null)
            dialog.show()
        }
    }
}

var TriviaQuestion.answered: Boolean
    get() = PreferencesManager.isAnswered(this.question)
    set(_) = PreferencesManager.setAnswered(this.question)