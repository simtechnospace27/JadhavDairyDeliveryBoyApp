package simtechnospace.tech.jadhavdairydeliveryboy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import simtechnospace.tech.jadhavdairydeliveryboy.Adapters.CompleteListAdapter;
import simtechnospace.tech.jadhavdairydeliveryboy.Adapters.DeliveredListAdapter;
import simtechnospace.tech.jadhavdairydeliveryboy.Database.DBHelper;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class ProfileFragment extends Fragment {



    UserCredentialsAfterLogin userCredentialsAfterLogin;

    TextView mTextViewDate;
    RecyclerView mRecyclerViewUserList;

    CompleteListAdapter mPendingListAdapter;
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


        View view = inflater.inflate(R.layout.complete_list_fragment, container, false);

        mTextViewDate = view.findViewById(R.id.txtUserRequirements);
        mRecyclerViewUserList = view.findViewById(R.id.userList);


        mCustomerDetailsArrayList = new ArrayList<>();

        mDBHelper = new DBHelper(getActivity());

        mCustomerDetailsArrayList = mDBHelper.getAllUserList();
        //System.out.println(mCustomerDetailsArrayList.size());

        mPendingListAdapter = new CompleteListAdapter(mCustomerDetailsArrayList);


        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerViewUserList.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewUserList.setHasFixedSize(true);
        mRecyclerViewUserList.setAdapter(mPendingListAdapter);


        //we can now set adapter to recyclerView;


        mPendingListAdapter.notifyDataSetChanged();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);

        mTextViewDate.setText(strDate);


        return view;
    }



}
