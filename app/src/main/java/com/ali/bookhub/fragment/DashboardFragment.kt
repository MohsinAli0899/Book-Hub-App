package com.ali.bookhub.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ali.bookhub.R
import com.ali.bookhub.adapter.DashboardRecyclerAdapter
import com.ali.bookhub.model.Book
import com.ali.bookhub.util.ConnectionManager
import org.w3c.dom.Text

class DashboardFragment : Fragment() {

lateinit var recyclerDashboard: RecyclerView

lateinit var layoutManager: RecyclerView.LayoutManager

lateinit var btnCheckInternet: Button

val bookList = arrayListOf<String>(
    "P.S I Love You",
    "The Great Gatsby",
    "Anna Karenina",
    "Madame Bovary",
    "War and Peace",
    "Lolita",
    "Middlemarch",
    "The Adventures of Huckleberry Finn",
    "Moby-Dick",
    "The Lord of the Rings")

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val bookInfoList = arrayListOf<Book>(
        Book("P.S I Love You", "Cecilia Ahern", "Rs. 299", "4.5", R.drawable.ps_ily),
        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.great_gatsby),
        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.anna_kare),
        Book("Madame Bovary", "Gustav Flaubert", "Rs. 500", "4.0", R.drawable.madame),
        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.war_and_peace),
        Book("Lolita", "Vlamidir Nabokov", "Rs. 349", "3.9", R.drawable.lolita),
        Book("Middlemarch", "George Elliot", "Rs. 599", "4.2", R.drawable.middlemarch),
        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.adventures_finn),
        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.5", R.drawable.moby_dick),
        Book("The Lord of the Rings", "J.R.R Tolkien", "Rs. 749", "5.0", R.drawable.lord_of_rings),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
        btnCheckInternet.setOnClickListener {
            if (ConnectionManager().checkConnectivity(activity as Context)){
                //Internet is available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet Connection Found")
                dialog.setPositiveButton("Ok"){ text , listener ->
                //Do Nothing
                }
                dialog.setNegativeButton("Cancel"){text, listener ->
                    //Do Nothing
                }
                dialog.create()
                dialog.show()
            }
            else{
                //Internet is not available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection Not Found")
                dialog.setPositiveButton("Ok"){ text , listener ->
                    //Do Nothing
                }
                dialog.setNegativeButton("Cancel"){text, listener ->
                    //Do Nothing
                }
                dialog.create()
                dialog.show()
            }
        }

        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookInfoList)

        recyclerDashboard.adapter = recyclerAdapter

        recyclerDashboard.layoutManager = layoutManager

        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation))

        return view
    }
}