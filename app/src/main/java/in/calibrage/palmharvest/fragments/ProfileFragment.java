package in.calibrage.palmharvest.fragments;

import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import in.calibrage.palmharvest.Model.GetUserOTPResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseFragment;
import in.calibrage.palmharvest.localData.SharedPrefsData;


public class ProfileFragment extends BaseFragment {
    public static String TAG = ProfileFragment.class.getSimpleName();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private TextView farmerName, email, mobileNumber, contactNumber, managerName,role_name;
    private LinearLayout lyt_farmerName, lyt_email, lyt_mobileNumber, lyt_contactNumber, lyt_managerName,lyt_roleName;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GetUserOTPResponse catagoriesList;


    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        init(view);
        setviews();

        return view;

    }


    private void setviews() {

        catagoriesList =  SharedPrefsData.getCatagoriess(getContext());

        String name = catagoriesList.getResult().getFirstName()+catagoriesList.getResult().getMiddleName()+catagoriesList.getResult().getLastname();
        farmerName.setText(": " + name.replace("null", " "));


        if (catagoriesList.getResult().getEmail() != null && !(TextUtils.isEmpty("" + catagoriesList.getResult().getEmail())))

            email.setText(": " +catagoriesList.getResult().getEmail());
        else
            lyt_email.setVisibility(View.GONE);


        if (catagoriesList.getResult().getMobileNumber() != null && !(TextUtils.isEmpty("" + catagoriesList.getResult().getMobileNumber())))
            mobileNumber.setText(": " + catagoriesList.getResult().getMobileNumber());
        else
            lyt_mobileNumber.setVisibility(View.GONE);

        if (catagoriesList.getResult().getContactNumber() != null && !(TextUtils.isEmpty("" + catagoriesList.getResult().getContactNumber())))
            contactNumber.setText(": " + catagoriesList.getResult().getContactNumber());
        else
            lyt_contactNumber.setVisibility(View.GONE);

        if (catagoriesList.getResult().getRoleId() == 2 )
            role_name.setText(": " + "Harvesting Labour");
        else
            role_name.setText(": " + "Harvesting Supervisor");

//        if (catagoriesList.getResult().getManagerName() != null && !(TextUtils.isEmpty("" + catagoriesList.getResult().getUserInfos().getManagerName())))
//
//            managerName.setText(": " + catagoriesList.getResult().getUserInfos().getManagerName());
//        else
//            lyt_managerName.setVisibility(View.GONE);
    }

    private void init(View view) {


        farmerName = view.findViewById(R.id.farmerName);
        email = view.findViewById(R.id.email);
        mobileNumber = view.findViewById(R.id.mobilenumber);
        contactNumber = view.findViewById(R.id.contactNumber);
        role_name= view.findViewById(R.id.rolename);
       // managerName = view.findViewById(R.id.manager);

        lyt_farmerName = view.findViewById(R.id.lyt_farmerName);
        lyt_email = view.findViewById(R.id.lyt_email);
        lyt_mobileNumber = view.findViewById(R.id.lyt_mobileNumber);
        lyt_contactNumber = view.findViewById(R.id.lyt_contactNumber);
        lyt_roleName = view.findViewById(R.id.lyt_rolename);

    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

//        My3FFragment.Adapter adapter = new My3FFragment.Adapter(getChildFragmentManager());
//        adapter.addFragment(new profile_fragment(),  getResources().getString(R.string.farmer_profile));
//        adapter.addFragment(new plot_details(), getResources().getString(R.string.plot_details));
//
//
//        viewPager.setAdapter(adapter);



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
