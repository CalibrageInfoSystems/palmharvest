package in.calibrage.a3fharvesting.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.calibrage.a3fharvesting.Activities.CloseLabourRequestActivity;
import in.calibrage.a3fharvesting.Activities.CloseLabourleaderRequestActivity;
import in.calibrage.a3fharvesting.Model.RequestCompleteModel;
import in.calibrage.a3fharvesting.Model.SyncLabourResponse;
import in.calibrage.a3fharvesting.R;
import in.calibrage.a3fharvesting.adapter.NotificationListner;
import in.calibrage.a3fharvesting.common.CommonUtil;
import in.calibrage.a3fharvesting.common.DateTimeUtil;
import in.calibrage.a3fharvesting.database.DatabaseQueryClass;
import in.calibrage.a3fharvesting.sync.SyncRequestHeaderResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements NotificationListner {

    RecyclerAdapter recyclerAdapter;
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
    public static String TAG = RequestsFragment.class.getSimpleName();

    String fromString, toString;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    private RecyclerView recyclerView;
    TextView emptyView;

    public RequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        databaseQueryClass = new DatabaseQueryClass(getContext());
        View view = inflater.inflate(R.layout.fragment_requests,
                container, false);


        final TextInputLayout txt_from_date = view.findViewById(R.id.txt_from_date);
        final TextInputLayout txt_to_date = view.findViewById(R.id.txt_to_date);
        final EditText fromDate = view.findViewById(R.id.from_date);
        final EditText toDate = view.findViewById(R.id.to_date);
        emptyView = view.findViewById(R.id.empty_view);
//        fromDate.setHint(CommonUtil.getMultiColourString(getString(R.string.fromDate)));
//        toDate.setHint(CommonUtil.getMultiColourString(getString(R.string.toDate)));
        Button submitBtn = view.findViewById(R.id.buttonSubmit);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerAdapter = new RecyclerAdapter(getActivity(), databaseQueryClass.getAllRequests(), this);
        recyclerAdapter = new RecyclerAdapter(getActivity(), null, this,databaseQueryClass);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);

        fetchContacts();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        fromDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        toDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fromString = fromDate.getText().toString().trim();
                toString = toDate.getText().toString().trim();

                if (fromString.equalsIgnoreCase("") || toString.equalsIgnoreCase("")) {

                    showDialog(getContext(), getResources().getString(R.string.enter_Date));
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);

                } else {


                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = null;
                    try {
                        date1 = formatter.parse(fromString);
                        Date date2 = formatter.parse(toString);
                        if (date2.compareTo(date1) < 0) {
                            showDialog(getContext(), getResources().getString(R.string.entervalidDates));
                            recyclerView.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);

                        } else {

                            List<RequestCompleteModel> data = databaseQueryClass.getRequestsBetweenDate(DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_3, fromString), DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_3, toString));
                            // showDialog(getContext(), "Dates are correct");
                            if (data.size() > 0) {
                                recyclerAdapter = new RecyclerAdapter(getActivity(),
                                        data, RequestsFragment.this,databaseQueryClass);
                                recyclerView.setAdapter(recyclerAdapter);
                                emptyView.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            } else {
                                emptyView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        return view;

    }

    public void showDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final ImageView img = dialog.findViewById(R.id.img_cross);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Animatable) img.getDrawable()).start();
            }
        }, 500);
    }

    public static boolean isDateAfter(String startDate, String endDate) {
        try {
            String myFormatString = "yyyy-M-dd"; // for example
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date date1 = df.parse(endDate);
            Date startingDate = df.parse(startDate);

            if (date1.after(startingDate))
                return true;
            else
                return false;
        } catch (Exception e) {

            return false;
        }
    }


    private void fetchContacts() {

    }

    @Override
    public void onNotificationClick(int po, RequestCompleteModel requestCompleteModel) {
        Intent intent = new Intent(getContext(), CloseLabourleaderRequestActivity.class);
        intent.putExtra("id", requestCompleteModel.getRequestCode());

        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Log.d(TAG, "Came From Close Request");

            List<RequestCompleteModel> data1 = null;
            try {
                data1 = databaseQueryClass.getRequestsBetweenDate(DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_3, fromString), DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_3, toString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // showDialog(getContext(), "Dates are correct");
            if (data1.size() > 0) {
                recyclerAdapter = new RecyclerAdapter(getActivity(),
                        data1, RequestsFragment.this,databaseQueryClass);
                recyclerView.setAdapter(recyclerAdapter);
                emptyView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }
}



