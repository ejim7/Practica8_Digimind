package ibarra.kevin.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gonzalez.adrian.digimind.R
import gonzalez.adrian.digimind.databinding.FragmentHomeBinding
import gonzalez.adrian.digimind.ui.Task

class HomeFragment : Fragment() {
    var task=ArrayList<Task>()
    private var adaptador:AdaptadorTareas?=null

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fillTasks()

        adaptador= AdaptadorTareas(root.context,task)
        val gridView: GridView =root.findViewById(R.id.gridView)
        gridView.adapter=adaptador

        return root
    }

    fun fillTasks(){
        task.add(Task("Practice 1", arrayListOf("Lunes"),"17:30"))
        task.add(Task("Practice 1", arrayListOf("Martes"),"17:40"))
        task.add(Task("Practice 1", arrayListOf("Miercoles"),"14:00"))
        task.add(Task("Practice 1", arrayListOf("Jueves"),"11:00"))
        task.add(Task("Practice 1", arrayListOf("Viernes"),"13:00"))
        task.add(Task("Practice 1", arrayListOf("Sabado"),"10:40"))
        task.add(Task("Practice 1", arrayListOf("Domingo"),"12:00"))

    }

    private class AdaptadorTareas:BaseAdapter{
        var task=ArrayList<Task>()
        var contexto: Context?=null

        constructor(context: Context,task:ArrayList<Task>){
            this.contexto=contexto
            this.task=task

        }


        override fun getCount(): Int {
            return task.size
        }

        override fun getItem(p0: Int): Any {
            return task[p0]

        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()

        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var task=task[p0]
            var inflador=LayoutInflater.from(contexto)
            var vista=inflador.inflate(R.layout.task_view,null)

            var tv_title:TextView=vista.findViewById(R.id.tv_title)
            var tv_time:TextView=vista.findViewById(R.id.tv_time)
            var tv_days:TextView=vista.findViewById(R.id.tv_days)

            tv_title.setText(task.tittle)
            tv_title.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}