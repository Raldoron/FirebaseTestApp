package com.example.raldoron.firebasetestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.raldoron.firebasetestapp.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Raldoron on 25.05.17.
 */

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private TextView userName;
    private ImageView userImage;
    private Button logoutButton;
    private Profile userProfile;

    public ProfileFragment(){
    }

    public static ProfileFragment getInstance(){
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(new Bundle());
        return profileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userName = (TextView) view.findViewById(R.id.profileName);
        userImage = (ImageView) view.findViewById(R.id.profileImage);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userProfile = getActivity().getIntent().getParcelableExtra("profile");
        userName.setText(userProfile.getName());
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
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
