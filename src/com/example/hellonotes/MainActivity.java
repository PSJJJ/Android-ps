package com.example.hellonotes;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity implements OnClickListener{

	private Button textbtn, imgbtn, videobtn;
	private ListView lv;
	private Intent i;
	private MyAdapter adapter;
	private NotesDB notesDB;
	private SQLiteDatabase dbReader;
	private Cursor cursor;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    
    }
    public void initView() {
		lv = (ListView) findViewById(R.id.list);
		textbtn = (Button) findViewById(R.id.text);
		imgbtn = (Button) findViewById(R.id.img);
		videobtn = (Button) findViewById(R.id.video);
		textbtn.setOnClickListener(this);
		imgbtn.setOnClickListener(this);
		videobtn.setOnClickListener(this);
		notesDB = new NotesDB(this);
		dbReader = notesDB.getReadableDatabase();
		
	}
	@Override
	public void onClick(View v) {
		i = new Intent(this, AddContent.class);
		switch (v.getId()) {
		case R.id.text:
			i.putExtra("flag", "1");
			startActivity(i);
			break;

		
		
		}
	}
	public void selectDB() {
		cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null, null,
				null, null);
		adapter = new MyAdapter(this, cursor);
		lv.setAdapter(adapter);
	}
	@Override
	protected void onResume() {
		super.onResume();
		selectDB();
	}

}
