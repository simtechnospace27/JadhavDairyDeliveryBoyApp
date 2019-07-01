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

public class DeliveredListAdapter extends RecyclerView.Adapter<DeliveredListAdapter.MyViewHolder> {

    private List<Cart> mBillDetailsArrayList; // this data structure carries our title and description

    int mPosition;


    public DeliveredListAdapter(List<Cart> billDetailsArrayList) {
        this.mBillDetailsArrayList = billDetailsArrayList;
    }

    @Override
    public DeliveredListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {


        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_billdisplay, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(view);


        // inflate your custom row layout here
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(final DeliveredListAdapter.MyViewHolder holder, int position) {

        mPosition = position;


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
        LinearLayout mLinearLayoutAction;

        Button mBtnDelivered, mBtnNotDelivered;

        View mViewHide;

        MyViewHolder(View view) {
            super(view);


            context = view.getContext();
            mTextSrNoDisplay = view.findViewById(R.id.srnoDisplay);
            mCustomerNameDisaply = view.findViewById(R.id.customerNameDdisplay);
            mAddressDisplay = view.findViewById(R.id.customerAddressDisplay);
            mRequirementDisplay = view.findViewById(R.id.customerRequirementDisplay);
            mLinearLayoutAction = view.findViewById(R.id.actionLayout);
            mViewHide = view.findViewById(R.id.hideView);


            mBtnDelivered = view.findViewById(R.id.btnDelivered);
            mBtnDelivered.setVisibility(View.GONE);
            mBtnNotDelivered = view.findViewById(R.id.btnNotAvailable);
            mBtnNotDelivered.setVisibility(View.GONE);

            mLinearLayoutAction.setVisibility(View.GONE);
            mViewHide.setVisibility(View.GONE);

        }
    }

}


