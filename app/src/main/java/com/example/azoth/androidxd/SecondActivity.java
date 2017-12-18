package com.example.azoth.androidxd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.Adapters.ListaMensajesAdapter;
import com.example.azoth.androidxd.FBObjects.Mensaje;
import com.example.milib.ListaMensajesFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    ListaMensajesFragment listaMensajesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        SecondActivityEvents events= new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        listaMensajesFragment=(ListaMensajesFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaMensajes);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("messages");

        /*ArrayList<String> mdatos= new ArrayList<>();
        mdatos.add("MENSAJE 1");
        mdatos.add("MENSAJE 2");
        mdatos.add("MENSAJE 3");
        mdatos.add("MENSAJE 4");

        ListaMensajesAdapter listaMensajesAdapter= new ListaMensajesAdapter(mdatos);

        listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);*/

    }
}


class SecondActivityEvents implements FireBaseAdminListener{

    public SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity=secondActivity;
    }

    @Override
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

        Log.v("SecondActivity",rama+"------------"+dataSnapshot);
        //Mensaje mensaje=dataSnapshot.getValue(Mensaje.class);
        GenericTypeIndicator<Map<String,Mensaje>> indicator=new GenericTypeIndicator<Map<String,Mensaje>>(){};
        Map<String,Mensaje> msgs = dataSnapshot.getValue(indicator);

        ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(new ArrayList<Mensaje>(msgs.values()));
        secondActivity.listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);


    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOK) {

    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOK) {

    }
}
