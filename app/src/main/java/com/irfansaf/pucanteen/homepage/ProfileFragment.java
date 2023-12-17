package com.irfansaf.pucanteen.homepage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.irfansaf.pucanteen.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView editprofileBtn;
    Button logoutBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View Return =  inflater.inflate(R.layout.fragment_profile, container, false);

        logoutBtn = Return.findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(v -> logoutUser());
        return Return;
    }
    private void editProfile() {
        AlertDialog.Builder ConfirmEdit = new AlertDialog.Builder(requireContext());
        ConfirmEdit.setTitle(getString(R.string.edit_profile));
        ConfirmEdit.setMessage(R.string.edit_profile_text);
        ConfirmEdit.setPositiveButton(getString(R.string.confirm_yes), (dialog, which) -> {
            Intent userEdit = new Intent(getActivity(), com.irfansaf.pucanteen.user.EditProfile.class);
            startActivity(userEdit);
        });
        ConfirmEdit.setNegativeButton(getString(R.string.confirm_no), (dialog, which) -> {});
        AlertDialog alertDialog = ConfirmEdit.create();
        alertDialog.show();
    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        AlertDialog.Builder UserLogout = new AlertDialog.Builder(requireContext());
        UserLogout.setTitle(getString(R.string.user_logout));
        UserLogout.setMessage(R.string.user_logout_text);
        UserLogout.setPositiveButton(getString(R.string.confirm_yes),(dialog, which) -> {
            Intent logout = new Intent(getActivity(), com.irfansaf.pucanteen.MainActivity.class);
            logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);
        });
        UserLogout.setNegativeButton(getString(R.string.confirm_no), (dialog, which) -> {});
        AlertDialog alertDialog = UserLogout.create();
        alertDialog.show();
    }
}