package Control

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Model.Item
import com.example.charitable.R
import Model.database.DatabaseManager
import Model.myAdapter


class AllDonation() : Fragment() {

    private lateinit var adapter: myAdapter
    private lateinit var recyclerView1: RecyclerView
    private lateinit var newsArrayList: ArrayList<Item>

    lateinit var imageId:Array<Int>
    lateinit var heading:Array<String>
    lateinit var news: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allDonationsInitialize()
        val layoutManager=LinearLayoutManager(context)
        recyclerView1=view.findViewById(R.id.recyclerView)
        recyclerView1.layoutManager=layoutManager
        recyclerView1.setHasFixedSize(true)
        adapter= myAdapter(newsArrayList)
        recyclerView1.adapter=adapter

    }

    private fun allDonationsInitialize(){
        newsArrayList= arrayListOf<Item>()
        val db= DatabaseManager.db
        val heading=db.getAllDonations()
        for (i in heading.indices)
        {
            val news= Item(R.drawable.a2,heading[i],)
            newsArrayList.add(news)
        }
    }
}