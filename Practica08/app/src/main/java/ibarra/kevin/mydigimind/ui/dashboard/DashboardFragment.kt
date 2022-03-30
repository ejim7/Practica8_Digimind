package ibarra.kevin.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ibarra.kevin.mydigimind.R
import ibarra.kevin.mydigimind.Recordatorio
import ibarra.kevin.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val botonTiempo:Button = root.findViewById(R.id.tiempo_boton) as Button;

        botonTiempo.setOnClickListener {
            val calendar = Calendar.getInstance();
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                botonTiempo.text = SimpleDateFormat("HH:mm").format(calendar.time);
            }
            TimePickerDialog(root.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true).show()
        }


        val boton_aceptar:Button = root.findViewById(R.id.agregarNota_boton) as Button;
        val nombre:TextView = root.findViewById(R.id.recordatorio_nombre) as TextView;
        val monday: CheckBox = root.findViewById(R.id.monday) as CheckBox;
        val tuesday: CheckBox = root.findViewById(R.id.tuesday) as CheckBox;
        val wednesday: CheckBox = root.findViewById(R.id.wednesday) as CheckBox;
        val thursday: CheckBox = root.findViewById(R.id.thursday) as CheckBox;
        val friday: CheckBox = root.findViewById(R.id.friday) as CheckBox;
        val saturday: CheckBox = root.findViewById(R.id.saturday) as CheckBox;
        val sunday: CheckBox = root.findViewById(R.id.sunday) as CheckBox;

        boton_aceptar.setOnClickListener {
            var nombre = nombre.text.toString();
            var tiempo = botonTiempo.text.toString();
            var dias = ArrayList<String>();

            if(monday.isChecked){dias.add("Monday");}
            if(tuesday.isChecked){dias.add("Tuesday");}
            if(wednesday.isChecked){dias.add("Wednesday");}
            if(thursday.isChecked){dias.add("Thursday");}
            if(friday.isChecked){dias.add("Friday");}
            if(saturday.isChecked){dias.add("Saturday");}
            if(sunday.isChecked){dias.add("Sunday");}

            var task = Recordatorio(nombre,dias,tiempo);

            HomeFragment.tasks.add(task);
            Toast.makeText(root.context, "New task added!", Toast.LENGTH_SHORT).show();
        }


        return root
    }


}