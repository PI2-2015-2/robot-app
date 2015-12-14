package com.br.robot_app.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import com.br.robot_app.R;
import com.br.robot_app.model.Sequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProgramListActivity extends ListActivity {

    private ArrayAdapter<String> adapter;
    private List<String> programsName;
    private Sequence toUpSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programs_screen);

        programsName = getAllPrograms();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,programsName);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        openSequenceFile(l.getItemAtPosition(position).toString());
    }

    private void openSequenceFile(String fileName){
        Context context = getBaseContext();
        File folder = new File(String.valueOf(context.getFilesDir()));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if(listOfFiles[i].getName().compareTo(fileName) == 0) {
                    toSequence(listOfFiles[i]);
                    Log.d(listOfFiles[i].getName(), fileName);
                }
            }
        }
    }

    private void toSequence(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String instruction = reader.readLine();

            Intent intentFile = new Intent(getApplicationContext(), SequenceActivity.class);
            intentFile.putExtra("instructions",instruction);
            startActivity(intentFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAllPrograms(){
        List<String> programs = new ArrayList<String>();
        Context context = getBaseContext();
        File folder = new File(String.valueOf(context.getFilesDir()));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                programs.add(listOfFiles[i].getName());
                Log.d("File: ", listOfFiles[i].getName());
            }
        }
        Log.d("Size", String.valueOf(programs.size()));
        return programs;
    }

    public void removeAllPrograms(View v){
        List<String> programs = new ArrayList<String>();
        Context context = getBaseContext();
        File folder = new File(String.valueOf(context.getFilesDir()));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFiles[i].delete();
            }
        }
    }
}
