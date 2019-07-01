package simtechnospace.tech.jadhavdairydeliveryboy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import simtechnospace.tech.jadhavdairydeliveryboy.Activity.HomeActivity;
import simtechnospace.tech.jadhavdairydeliveryboy.Database.DBHelper;
import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.MyViewHolder> {

    private List<Cart> mBillDetailsArrayList; // this data structure carries our title and description

    int mPosition;


    public PendingListAdapter(List<Cart> billDetailsArrayList) {
        this.mBillDetailsArrayList = billDetailsArrayList;
    }

    @Override
    public PendingListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {


        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_billdisplay, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(view);


        mViewHolder.mBtnDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cart cart = mBillDetailsArrayList.get(mViewHolder.getPosition());
                cart.setDeliveryStatus(1);

                DBHelper dbHelper = new DBHelper(mViewHolder.context);


                dbHelper.updateUserDetails(cart);

                Intent intent = new Intent(mViewHolder.context, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mViewHolder.context.startActivity(intent);
                ((Activity)mViewHolder.context).finish();

            }
        });


        mViewHolder.mBtnNotDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cart cart = mBillDetailsArrayList.get(mViewHolder.getPosition());
                cart.setDeliveryStatus(2);

                DBHelper dbHelper = new DBHelper(mViewHolder.context);


                dbHelper.updateUserDetails(cart);

                Intent intent = new Intent(mViewHolder.context, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mViewHolder.context.startActivity(intent);
                ((Activity)mViewHolder.context).finish();

            }
        });



        // inflate your custom row layout here
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(final PendingListAdapter.MyViewHolder holder, int position) {

        mPosition = position;


        String today = mBillDetailsArrayList.get(position).getTimeStamp().split(" ")[0];



        holder.mTextSrNoDisplay.setText(position + 1 + "");
            holder.mCustomerNameDisaply.setText(mBillDetailsArrayList.get(position).getCustomerName());
            holder.mAddressDisplay.setText(mBillDetailsArrayList.get(position).getDeliveryAddress());
            holder.mRequirementDisplay.setText(mBillDetailsArrayList.get(position).getRequirements());




    }


    @Override
    public int getItemCount() {
        return mBillDetailsArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView mTextSrNoDisplay, mCustomerNameDisaply, mAddressDisplay, mRequirementDisplay;

        Button mBtnDelivered, mBtnNotDelivered;


        MyViewHolder(View view) {
            super(view);


            context = view.getContext();
            mTextSrNoDisplay = view.findViewById(R.id.srnoDisplay);
            mCustomerNameDisaply = view.findViewById(R.id.customerNameDdisplay);
            mAddressDisplay = view.findViewById(R.id.customerAddressDisplay);
            mRequirementDisplay = view.findViewById(R.id.customerRequirementDisplay);


            mBtnDelivered = view.findViewById(R.id.btnDelivered);
            mBtnNotDelivered = view.findViewById(R.id.btnNotAvailable);

        }
    }
}
