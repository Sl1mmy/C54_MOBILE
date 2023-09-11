package com.bousquet.noe.annexe_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try(FileOutputStream is = openFileOutput("palmares.ser", Context.MODE_PRIVATE);
            ObjectOutputStream ois = new ObjectOutputStream(is)) {
            HashMap<String, Object> un = new HashMap();
            un.put("position", "3");
            un.put("nom", "Touch Me");
            un.put("date", "22/03/86");
            un.put("image", R.drawable.touchme);
            ois.writeObject(un);

            HashMap<String, Object> deux = new HashMap();
            deux.put("position", "8");
            deux.put("nom", "Nothing's gonna stop me now");
            deux.put("date", "30/05/86");
            deux.put("image", R.drawable.nothing);
            ois.writeObject(deux);

            HashMap<String, Object> trois = new HashMap();
            trois.put("position", "31");
            trois.put("nom", "Santa Maria");
            trois.put("date", "28/03/1998");
            trois.put("image", R.drawable.santamaria);
            ois.writeObject(trois);

            HashMap<String, Object> quatre = new HashMap();
            quatre.put("position", "108");
            quatre.put("nom", "Hot boy");
            quatre.put("date", "10/04/2018");
            quatre.put("image", R.drawable.hotboy);
            ois.writeObject(quatre);
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }

        liste = findViewById(R.id.item_liste);

        SimpleAdapter adapter = new SimpleAdapter(this,
                remplirDonneesAvecFichierDeSerialisation(getResources().openRawResource(R.raw.palmares)),
                R.layout.item_view,
                new String[]{"position", "nom", "date", "image"},
                new int[]{R.id.topNumber, R.id.title, R.id.date, R.id.cover});

        liste.setAdapter(adapter);
    }

    public Vector<HashMap<String, Object>> remplirDonneesAvecFichierDeSerialisation (InputStream i) {

        Vector<HashMap<String, Object>> v = new Vector<>();
        try(ObjectInputStream ois = new ObjectInputStream(i)) {
            HashMap<String, Object> h;
            while((h = (HashMap<String, Object>) ois.readObject()) != null) {
                v.add(h);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}