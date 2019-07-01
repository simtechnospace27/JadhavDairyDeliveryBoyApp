package simtechnospace.tech.jadhavdairydeliveryboy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;
import simtechnospace.tech.jadhavdairydeliveryboy.R;

public class CompleteListAdapter extends RecyclerView.Adapter<CompleteListAdapter.MyViewHolder> {

    private List<Cart> mBillDetailsArrayList; // this data structure carries our title and description

    int mPosition;


    public CompleteListAdapter(List<Cart> billDetailsArrayList) {
        this.mBillDetailsArrayList = billDetailsArrayList;
    }

    @Override
    public CompleteListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {


        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_complete_list, parent, false);
        final CompleteListAdapter.MyViewHolder mViewHolder = new CompleteListAdapter.MyViewHolder(view);


        // inflate your custom row layout here
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(final CompleteListAdapter.MyViewHolder holder, int position) {

        mPosition = position;


        holder.mTextSrNoDisplay.setText(position + 1 + "");
        holder.mCustomerNameDisaply.setText(mBillDetailsArrayList.get(position).getCustomerName());
        holder.mAddressDisplay.setText(mBillDetailsArrayList.get(position).getDeliveryAddress());
        holder.mRequirementDisplay.setText(mBillDetailsArrayList.get(position).getRequirements());

        if (mBillDetailsArrayList.get(position).getDeliveryStatus() == 1)
        {
            holder.mActionDisplay.setText("Delivered");
        }
        else if(mBillDetailsArrayList.get(position).getDeliveryStatus() == 2)
        {
            holder.mActionDisplay.setText("Deliver Cancled");
        }
        else{
            holder.mActionDisplay.setText("Delivery Pending");
        }


    }


    @Override
    public int getItemCount() {
        return mBillDetailsArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView mTextSrNoDisplay, mCustomerNameDisaply, mAddressDisplay, mRequirementDisplay, mActionDisplay;

        MyViewHolder(View view) {
            super(view);


            context = view.getContext();
            mTextSrNoDisplay = view.findViewById(R.id.srnoDisplay);
            mCustomerNameDisaply = view.findViewById(R.id.customerNameDdisplay);
            mAddressDisplay = view.findViewById(R.id.customerAddressDisplay);
            mRequirementDisplay = view.findViewById(R.id.customerRequirementDisplay);

            mActionDisplay = view.findViewById(R.id.actionDisplay);

        }
    }

}



