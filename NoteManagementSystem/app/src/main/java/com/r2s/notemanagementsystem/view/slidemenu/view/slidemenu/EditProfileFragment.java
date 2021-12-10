package com.r2s.notemanagementsystem.view.slidemenu.view.slidemenu;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.databinding.FragmentEditProfileBinding;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.dao.UserDao;
import com.r2s.notemanagementsystem.viewmodel.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentEditProfileBinding binding;
    private UserViewModel mUserViewModel;
    private User mUser;
    private AppDatabase mDb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EditProfileFragment() {
        // Required empty public constructor
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
        binding = FragmentEditProfileBinding.inflate(getLayoutInflater());
        binding.btnChange.setOnClickListener(this);
        binding.btnHome.setOnClickListener(this);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_change:
                editProfile();
                break;
            case R.id.btn_home:
                Intent intent = new Intent(getActivity().getApplicationContext(),HomeFragment.class);
                startActivity(intent);
                break;
        }
    }
    private void editProfile(){
        String strFirstName = binding.etFirstName.getText().toString();
        String strLastName = binding.etLastName.getText().toString();
        String strEmail = binding.etEmail.getText().toString().trim();
        if(TextUtils.isEmpty(strFirstName) || TextUtils.isEmpty(strLastName) ||
        TextUtils.isEmpty(strEmail)){
            return;
        }
        mUser.setFirstName(strFirstName);
        mUser.setLastName(strLastName);
        mUser.setEmail(strEmail);

        AppDatabase.getInstance(getContext()).getUserDao().updateprofile(mUser);
        Toast.makeText(getContext(),"Update user successfully",Toast.LENGTH_SHORT).show();


    }
}
