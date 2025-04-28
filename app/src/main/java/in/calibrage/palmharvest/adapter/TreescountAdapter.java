package in.calibrage.palmharvest.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.calibrage.palmharvest.Model.LabourDbModel;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.localData.SharedPrefsData;

public class TreescountAdapter extends RecyclerView.Adapter<TreescountAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    DatabaseQueryClass _databaseQueryClass;
    private   List<SyncLabourLeaderDetailsResponse.ListResult> _listresult;
    private OnEditTextChanged onEditTextChanged;
    Map<Integer,String> items =new HashMap<Integer,String>();
    Context _ctx;
    public String[][] data = new String[30][4];

    public TreescountAdapter(Context ctx, List<SyncLabourLeaderDetailsResponse.ListResult> _listresult, DatabaseQueryClass databaseQueryClass,OnEditTextChanged onEditTextChanged){

        inflater = LayoutInflater.from(ctx);
        this._ctx = ctx;
        this._databaseQueryClass = databaseQueryClass;
        this._listresult = _listresult;
        this.onEditTextChanged =onEditTextChanged;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.edit_item, parent, false);

        MyViewHolder  holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // TOdo based on Labour id get labour name
        final SyncLabourLeaderDetailsResponse.ListResult currentUser= _listresult.get(position);
        Log.e("labour===========1",currentUser.getLabourId()+"");


        final List<LabourDbModel> labour =  _databaseQueryClass.getLabourinfoBylabourid(currentUser.getLabourId().toString());

        if( SharedPrefsData.getCatagoriess(_ctx).getResult().getRoleId() !=2)
        {
            holder.txtName.setText(labour.get(0).getFirstname()+" "+labour.get(0).getLastname());
        }
        else {
            holder.txtName.setText(SharedPrefsData.getCatagoriess(_ctx).getResult().getFirstName()+" "+SharedPrefsData.getCatagoriess(_ctx).getResult().getLastname());
        }
        Log.d("print","yes");
       // holder.editText.setText();
       // holder.editText.setText(data[position][position]);

        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  int position = (int)  holder.editText.getTag();
                //   Log.e("id==============",currentUser.getLabourId()+"====," +charSequence.toString());
                //   _listresult.get(position).setTreesCount(Integer.valueOf(charSequence.toString()));

            }
            @Override
            public void afterTextChanged(Editable s) {
            //  data[holder.getAdapterPosition()][position] = s.toString();
                Log.d("DATA" + holder.getAdapterPosition() + "0", s.toString());
                if (holder.editText.hasFocus()) {
                    if (s != null && s.length() > 0) {

                        updatevalue(_listresult.get(position).getLabourId(), s.toString());

                    } else {
                        updatevalue(_listresult.get(position).getLabourId(), "0");
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _listresult.size();
    }
    @Override
    public long getItemId(int position) {
        setHasStableIds(true);
        return position;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText editText;
        TextView txtName;

        public MyViewHolder(View itemView) {
            super(itemView);

            editText = (EditText) itemView.findViewById(R.id.tressCount_edittxt);
            txtName =  itemView.findViewById(R.id.txtName);



        }

    }

    public interface OnEditTextChanged {
        void onTextChanged(Integer totalCount,Map<Integer,String> items);
    }
    public void updatevalue(Integer po, String value)
    {

        Log.d("Adapter ", "Updated item position :"+po + "===================value :"+value);
        items.put(po,value);
        int totalitemscount= 0;
        for (Map.Entry<Integer,String> entry : items.entrySet())
        {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

            //  _databaseQueryClass.updateTressCount(entry.getKey(),Integer.parseInt(entry.getValue()));
            _databaseQueryClass.updateTressCount(entry.getKey(),Integer.parseInt(entry.getValue()),_listresult.get(0).getRequestCode(),SharedPrefsData.getCatagoriess(_ctx).getResult().getId());

            totalitemscount = totalitemscount+  Integer.parseInt(entry.getValue());
        }Log.d("Total Itemas Count", "===== > Count : "+totalitemscount);
        onEditTextChanged.onTextChanged(totalitemscount,items);
    }


}
