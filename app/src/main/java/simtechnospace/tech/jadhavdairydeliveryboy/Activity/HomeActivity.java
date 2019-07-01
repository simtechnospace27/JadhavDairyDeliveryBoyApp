package simtechnospace.tech.jadhavdairydeliveryboy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simtechnospace.tech.jadhavdairydeliveryboy.Database.DBHelper;
import simtechnospace.tech.jadhavdairydeliveryboy.Fragments.BillDetailsFragment;
import simtechnospace.tech.jadhavdairydeliveryboy.Fragments.HomeFragment;
import simtechnospace.tech.jadhavdairydeliveryboy.Fragments.ProfileFragment;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.URL;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class HomeActivity extends AppCompatActivity {


    Toolbar toolbar;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    DBHelper mDbHelper;

    List<Cart> mCompleteDetailSaveToServerList;
    UserCredentialsAfterLogin userCredentialsAfterLogin;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.replace(R.id.fragment_container, homeFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_subscription:
                    BillDetailsFragment billDetailsFragment = new BillDetailsFragment();
                    transaction.replace(R.id.fragment_container, billDetailsFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_recent:
                    ProfileFragment profileFragment = new ProfileFragment();
                    transaction.replace(R.id.fragment_container, profileFragment);
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDbHelper = new DBHelper(this);

        userCredentialsAfterLogin = new UserCredentialsAfterLogin(this);


        List<Cart> c1 = mDbHelper.getDate();

        for (int i=0; i<c1.size(); i++)
        {

            Cart c = c1.get(i);

            String date = c.getTimeStamp();

          //  System.out.println(date);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date();
            String chkDate = dateFormat.format(date1); //2016/11/16 12:08:43
            long curretnTime = 0, dbTime = 0;

            try {
                Timestamp ts = new Timestamp((dateFormat.parse(chkDate)).getTime());
                Timestamp ts1 = new Timestamp((dateFormat.parse(date)).getTime());

                curretnTime = ts.getTime();
                dbTime = ts1.getTime();

                System.out.println(ts1);


            } catch (ParseException e) {
                e.printStackTrace();
            }


            if (curretnTime > dbTime)
            {
                mDbHelper.deleteUserDetailsByDate(c);
            }


        }




        mCompleteDetailSaveToServerList = mDbHelper.getAllUserList();

        for (int i=0; i<mCompleteDetailSaveToServerList.size(); i++)
        {

            Cart cart = mCompleteDetailSaveToServerList.get(i);

            String customerId = cart.getCustomerId();
            String empEmail = userCredentialsAfterLogin.getEmail();
            String deliveryDate = cart.getTimeStamp();
            int delivery_status = cart.getDeliveryStatus();
            String requi = cart.getRequirements();

            


        }





        getSupportActionBar().setDisplayShowTitleEnabled(false);
        frameLayout = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.navigation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = new HomeFragment();
        transaction.add(R.id.fragment_container, homeFragment);
        transaction.commit();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);
        FrameLayout notifCount = (FrameLayout) MenuItemCompat.getActionView(item);


        final MenuItem itemNotification = menu.findItem(R.id.cart);
        View actionViewNotification = MenuItemCompat.getActionView(itemNotification);
        actionViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(itemNotification);
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.cart) {

            Toast.makeText(this, "Notifications - regarding bills", Toast.LENGTH_SHORT).show();

            return true;
        } else if (id == R.id.user) {
            // Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

            UserCredentialsAfterLogin loginUserDetails = new UserCredentialsAfterLogin(HomeActivity.this);
            loginUserDetails.removeUserInfo();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
