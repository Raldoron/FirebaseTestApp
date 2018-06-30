package com.example.raldoron.firebasetestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.LoginActivity;
import com.example.raldoron.firebasetestapp.MainActivity;
import com.example.raldoron.firebasetestapp.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/*
 * Created by Raldoron on 25.05.17.
 */

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private MainActivity mainActivity;

    private TextView userName;
    private ImageView userImage;
    private Button logoutButton;

    public ProfileFragment(){
    }

    public static ProfileFragment getInstance(){
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(new Bundle());
        return profileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userName = view.findViewById(R.id.profileName);
        userImage = view.findViewById(R.id.profileImage);
        logoutButton = view.findViewById(R.id.logoutButton);

        mainActivity = (MainActivity) getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Profile userProfile = mainActivity.getIntent().getParcelableExtra("profile");
        userName.setText(userProfile.getName());
        logoutButton.setOnClickListener(view -> {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(mainActivity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            mainActivity.finish();
        });

        Picasso.with(getContext())
                .load(userProfile.getProfilePictureUri(200, 200))
                .error(R.drawable.com_facebook_profile_picture_blank_square)
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .into(userImage, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "Couldn't load image");
                    }
                });
    }

}
