package simtechnospace.tech.jadhavdairydeliveryboy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simtechnospace.tech.jadhavdairydeliveryboy.Adapters.DeliveredListAdapter;
import simtechnospace.tech.jadhavdairydeliveryboy.Adapters.PendingListAdapter;
import simtechnospace.tech.jadhavdairydeliveryboy.Database.DBHelper;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.URL;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class BillDetailsFragment extends Fragment {



    UserCredentialsAfterLogin userCredentialsAfterLogin;

    TextView mTextViewDate;
    RecyclerView mRecyclerViewUserList;

    DeliveredListAdapter mPendingListAdapter;
    List<Cart> mCustomerDetailsArrayList;


    LinearLayoutManager mLinearLayoutManager;

    DBHelper mDBHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.delivered_fragment, container, false);

        mTextViewDate = view.findViewById(R.id.txtUserRequirements);
        mRecyclerViewUserList = view.findViewById(R.id.userList);


        mCustomerDetailsArrayList = new ArrayList<>();

        mDBHelper = new DBHelper(getActivity());

        mCustomerDetailsArrayList = mDBHelper.getDeliveredUserList();
        //System.out.println(mCustomerDetailsArrayList.size());

        mPendingListAdapter = new DeliveredListAdapter(mCustomerDetailsArrayList);


        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerViewUserList.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewUserList.setHasFixedSize(true);
        mRecyclerViewUserList.setAdapter(mPendingListAdapter);


        //we can now set adapter to recyclerView;


        mPendingListAdapter.notifyDataSetChanged();


return view;
    }




}
