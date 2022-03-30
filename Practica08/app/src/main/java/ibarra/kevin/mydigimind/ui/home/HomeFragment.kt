package ibarra.kevin.mydigimind.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ibarra.kevin.mydigimind.R
import ibarra.kevin.mydigimind.Recordatorio

class HomeFragment : Fragment() {

    private var adaptador:AdaptadorRecordatorio?=null;
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Recordatorio>();
        var firts = true;
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root:View = inflater.inflate(R.layout.fragment_home, container, false)

        if(firts){llenarTasks(); firts = false;}


        adaptador = AdaptadorRecordatorio(root.context, tasks);
        val gridView:GridView = root.findViewById(R.id.tabla_recordatorios);
        gridView.adapter = adaptador;

        return root
    }

    fun llenarTasks(){
        tasks.add(Recordatorio("Practica 1", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 2", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 3", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 4", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 5", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 6", arrayListOf("Monday","Saturday"), "17:30"));
        tasks.add(Recordatorio("Practica 7", arrayListOf("Monday","Saturday"), "17:30"));
    }

    private class AdaptadorRecordatorio: BaseAdapter {
        var tasks = ArrayList<Recordatorio>();
        var contexto: Context? = null

        constructor(contexto: Context, tasks:ArrayList<Recordatorio>){
            this.contexto = contexto;
            this.tasks = tasks;
        }

        override fun getCount(): Int {
            return tasks.size;
        }

        override fun getItem(position: Int): Any {
            return tasks[position];
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position];
            var inflater = LayoutInflater.from(contexto);
            var vista = inflater.inflate(R.layout.recordatorio, null);

            var textViewNombre = vista.findViewById(R.id.nombre_recordatorio) as TextView;
            var textViewDias = vista.findViewById(R.id.dias_recordatorio) as TextView;
            var textViewTiempo = vista.findViewById(R.id.hora_recordatorio) as TextView;

            textViewNombre.setText(task.nombre);
            textViewDias.setText(task.dias.toString());
            textViewTiempo.setText(task.tiempo);

            return vista;
        }


    }

}
