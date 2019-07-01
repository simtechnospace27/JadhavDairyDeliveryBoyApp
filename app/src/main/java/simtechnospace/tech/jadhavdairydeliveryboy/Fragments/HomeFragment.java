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

import simtechnospace.tech.jadhavdairydeliveryboy.Activity.HomeActivity;
import simtechnospace.tech.jadhavdairydeliveryboy.Adapters.PendingListAdapter;
import simtechnospace.tech.jadhavdairydeliveryboy.Database.DBHelper;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.URL;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class HomeFragment extends Fragment {

    UserCredentialsAfterLogin userCredentialsAfterLogin;

    TextView mTextViewDate;
    RecyclerView mRecyclerViewUserList;

    PendingListAdapter mPendingListAdapter;
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


        View view = inflater.inflate(R.layout.home_fragment, container, false);

        mTextViewDate = view.findViewById(R.id.txtUserRequirements);
        mRecyclerViewUserList = view.findViewById(R.id.userList);


        mCustomerDetailsArrayList = new ArrayList<>();

        mDBHelper = new DBHelper(getActivity());

        mPendingListAdapter = new PendingListAdapter(mCustomerDetailsArrayList);



        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerViewUserList.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewUserList.setHasFixedSize(true);


        //we can now set adapter to recyclerView;


        //mPendingListAdapter.notifyDataSetChanged();




        UserCredentialsAfterLogin userCredentialsAfterLogin = new UserCredentialsAfterLogin(getActivity());



        String getUserDetailsUrl = URL.mGetUserListFromServerUrl;

        JSONObject params = new JSONObject();
        try {

            params.put("email", userCredentialsAfterLogin.getEmail());


        } catch (JSONException e) {
            e.printStackTrace();

        }


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUserDetailsUrl, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    if (response.getInt("status") == 1) {

                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i=0; i< jsonArray.length(); i++)
                        {

                            JSONObject js = jsonArray.getJSONObject(i);

                            String userId = js.getString("userId");
                            String customerName = js.getString("customerName");
                            String customerAddress = js.getString("customerAddress");
                            String requirements = js.getString("requirements");




                            Cart cart = mDBHelper.getSingleUserDetailsUsingCustomerId(userId);

                            if (cart != null)
                            {
                                cart.setRequirements(requirements);
                                cart.setCustomerName(customerName);
                                cart.setDeliveryAddress(customerAddress);
                                cart.setCustomerId(userId);

                                mDBHelper.updateUserDetails(cart);

                                System.out.println("Updated...");

                                mCustomerDetailsArrayList = mDBHelper.getPendingUserList();

                                //Cart c = mCustomerDetailsArrayList.get(2);
                                //System.out.println("demo = "+c.getCustomerId());


                                mPendingListAdapter = new PendingListAdapter(mCustomerDetailsArrayList);
                                //System.out.println(mCustomerDetailsArrayList.size());
                                mRecyclerViewUserList.setAdapter( mPendingListAdapter );
                                mPendingListAdapter.notifyDataSetChanged();

                            }
                            else{

                               // System.out.println(userId);
                               // System.out.println(customerName);
                               // System.out.println(customerAddress);

                              long id =  mDBHelper.insertUserDetails(userId, customerName, customerAddress, requirements);

                                //System.out.println("inserted... at = "+id);


                                mCustomerDetailsArrayList = mDBHelper.getPendingUserList();

                                //Cart c = mCustomerDetailsArrayList.get(2);
                                //System.out.println("demo = "+c.getCustomerId());

                                mPendingListAdapter = new PendingListAdapter(mCustomerDetailsArrayList);
                               // System.out.println(mCustomerDetailsArrayList.size());
                                mRecyclerViewUserList.setAdapter( mPendingListAdapter );
                                mPendingListAdapter.notifyDataSetChanged();

                            }

                        }



                    }
                    else{
                   //     Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();


                        mCustomerDetailsArrayList = mDBHelper.getPendingUserList();


                        mPendingListAdapter = new PendingListAdapter(mCustomerDetailsArrayList);
                        mRecyclerViewUserList.setAdapter( mPendingListAdapter );
                        mPendingListAdapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mCustomerDetailsArrayList = mDBHelper.getPendingUserList();

                mPendingListAdapter = new PendingListAdapter(mCustomerDetailsArrayList);
                mRecyclerViewUserList.setAdapter( mPendingListAdapter );
                mPendingListAdapter.notifyDataSetChanged();


            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);




        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);

        mTextViewDate.setText(strDate);




        return view;

    }


    }
