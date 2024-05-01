///////////////App - 1 //////////////////////////////


//MainActivity.java - App - 1
package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<Item> itemList=new ArrayList<>();
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),itemList,this));

        RecyclerView recyclerView2=findViewById(R.id.recyclerview_2);
        LinearLayoutManager llm2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(llm2);
        recyclerView2.setAdapter(new MyAdapter(getApplicationContext(),itemList,this));
    }

    @Override
    public void onItemClicked(Item item) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}

//fragmen1.java
package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class fragment1 extends Fragment {
    ImageView image;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_fragment1, container, false);
        return view;
    }
}
//item.java
package com.example.newsapp;

public class Item {

    int image;
    public Item(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



}

//MyAdapter.java
package com.example.newsapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    private SelectListener listener;

    public MyAdapter(Context context, List<Item> items,SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener=listener;
    }

    List<Item> items;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

//MyViewHolder.java
package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    public CardView cardView;
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.image_View);
        cardView=itemView.findViewById(R.id.main_container);
    }
}


//SecondActivity.java
package com.example.newsapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        replaceFragment(new fragment1());
        List<Item> itemList=new ArrayList<>();
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));
        itemList.add(new Item(R.drawable.download));
        itemList.add(new Item(R.drawable.download2));

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),itemList,null));//
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}


//SelectListener.java

package com.example.newsapp;

public interface SelectListener {
    void onItemClicked(Item item);
}




////////////////////  APP - 2 //////////////////////////////////// 


//MainActivity - App - 2

package com.example.videoplayer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button  signup;

    private  EditText username;
    private EditText password;


    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        login=findViewById(R.id.login_btn);
        signup=findViewById(R.id.signup_btn);
        username=findViewById(R.id.user_name_loginpage);
        password=findViewById(R.id.password_loginpage);


        DB=new DBHelper(MainActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //i was writing below line after coding db helper class when exception occured
                //there should be another code
                String usernameTXT=username.getText().toString();
                String passwordTXT=password.getText().toString();
                Boolean res=DB.checkForUser(usernameTXT,passwordTXT);
                if(res){
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class); //current activity and destination activity
                    intent.putExtra("username",usernameTXT);
                    startActivity(intent);
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //i was writing below line after coding db helper class when exception occured
                //there should be another code
                Intent intent=new Intent(MainActivity.this,CreateAccountActivity.class); //current activity and destination activity
                startActivity(intent);
            }
        });



    }
}

//CreateAccountActivity.java


package com.example.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAccountActivity extends AppCompatActivity {

    DBHelper DB;
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private Button createAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB=new DBHelper(CreateAccountActivity.this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name=findViewById(R.id.full_name);
        username=findViewById(R.id.user_name);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm_password);
        createAcc=findViewById(R.id.create_acc_btn);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTXT=name.getText().toString();
                String usernameTXT=username.getText().toString();
                String passwordTXT=password.getText().toString();
                String confirmPaswTXT=confirm_password.getText().toString();
                if(passwordTXT.equals(confirmPaswTXT)){
                    Boolean checkInsertUserData=DB.insertUser(nameTXT,usernameTXT,passwordTXT);
                    //here add a Toast
                    if(checkInsertUserData){
                        Intent intent=new Intent(CreateAccountActivity.this,MainActivity.class); //current activity and destination activity
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
//DBHelper.java

package com.example.videoplayer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"Userdata.db",null,3);
    }

    //I am trying not to use a context here


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Users(name TEXT primary key, username TEXT, password TEXT)");
        DB.execSQL("create Table Userurl(name TEXT primary key, url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Users");
        DB.execSQL("drop Table if exists Userurl");
        DB.execSQL("create Table Users(name TEXT primary key, username TEXT, password TEXT)");
        DB.execSQL("create Table Userurl(name TEXT , url TEXT)");
    }
    public Boolean insertUser(String name,String username,String password){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=DB.insert("Users",null,contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean insertUrl(String username,String url){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",username);//column name should be changed to username
        contentValues.put("url",url);
        long result=DB.insert("Userurl",null,contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkForUser(String username,String password){
        //here i wront this with the assumption of there is only one user,i have to make this write
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[] { username, password });
        boolean userExists = false;

        if (cursor.getCount() != 0) {
            userExists=true;
        }
        return userExists;
    }

    public List<Item> getUrls(String username){
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Userurl WHERE name=?", new String[] { username });
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                itemList.add(new Item(url));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }

}

//HomeActivity.java

package com.example.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private Button play_btn;
    private Button add_to_playlist;
    private Button my_playlist_btn;
    private EditText urlTXT;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        play_btn=findViewById(R.id.play_btn);
        add_to_playlist=findViewById(R.id.add_to_playlist);
        my_playlist_btn=findViewById(R.id.my_playlist);
        urlTXT=findViewById(R.id.url);
        username=getIntent().getStringExtra("username");

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_text=urlTXT.getText().toString();
                Intent intent=new Intent(HomeActivity.this,PlayerActivity.class); //current activity and destination activity
                intent.putExtra("url",url_text);
                startActivity(intent);
            }
        });

        add_to_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_text=urlTXT.getText().toString();
                DBHelper DB=new DBHelper(HomeActivity.this);
                DB.insertUrl(username,url_text);
                Intent intent=new Intent(HomeActivity.this,HomeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        my_playlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,PlayListActivity.class); //current activity and destination activity
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });




    }
}

//Item.java

package com.example.videoplayer;

public class Item {
    public Item(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;


}


//MyAdapter.java

package com.example.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.urlTXT.setText(items.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


//MyPlaylistActivity.java
package com.example.videoplayer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyPlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_playlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


//MyViewHolder.java
package com.example.videoplayer;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView urlTXT;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        urlTXT=itemView.findViewById(R.id.url_);
    }
}



//PlayerActivity.java

package com.example.videoplayer;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerActivity extends AppCompatActivity {
    private String stringJavaScript = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<div id=\"player\"></div>\n" +
            "<script>\n" +
            "var tag = document.createElement('script');\n" +
            "tag.src = 'https://www.youtube.com/iframe_api';\n" +
            "var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
            "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
            "var player;\n" +
            "function onYouTubeIframeAPIReady() {\n" +
            "  player = new YT.Player('player', {\n" +
            "    height: '100%',\n" +
            "    width: '100%',\n" +
            "    videoId: 'VIDEO_ID_PLACEHOLDER',\n" + // Placeholder for videoId
            "    playerVars: {\n" +
            "      'playsinline': 1\n" +
            "    },\n" +
            "    events: {\n" +
            "      'onReady': onPlayerReady,\n" +
            "      'onStateChange': onPlayerStateChange\n" +
            "    }\n" +
            "  });\n" +
            "}\n" +
            "function onPlayerReady(event) {\n" +
            "  event.target.playVideo();\n" +
            "}\n" +
            "var done = false;\n" +
            "function onPlayerStateChange(event) {\n" +
            "  if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
            "    setTimeout(stopVideo, 10000);\n" +
            "    done = true;\n" +
            "  }\n" +
            "}\n" +
            "function stopVideo() {\n" +
            "  player.stopVideo();\n" +
            "}\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String url=getIntent().getStringExtra("url");

        String videoId=extractVideoId(url);
        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        stringJavaScript = stringJavaScript.replace("VIDEO_ID_PLACEHOLDER", videoId);
        webView.loadData(stringJavaScript,"text/html","utf-8");
    }
    private String extractVideoId(String videoUrl) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl); //url is youtube url for which you want to extract video id.
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }


}

//PlayListActivity.java


package com.example.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    String username;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username=getIntent().getStringExtra("username");
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        backButton =findViewById(R.id.back_btn);
        List<Item> items;

        DBHelper DB=new DBHelper(PlayListActivity.this);
        items=DB.getUrls(username);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlayListActivity.this,HomeActivity.class); //current activity and destination activity
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }
}