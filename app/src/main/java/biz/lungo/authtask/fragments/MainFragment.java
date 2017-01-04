package biz.lungo.authtask.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import biz.lungo.authtask.R;
import biz.lungo.authtask.models.ProfileInfo;

public class MainFragment extends BaseFragment {

    private ProfileInfo profileInfo;
    private TextView tvGreeting;
    private Button btnProfileInfo;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_profile_info:
                    ProfileFragment profileFragment = ProfileFragment.newInstance(profileInfo);
                    getMainActivity().changeFragment(profileFragment, true);
                    break;

                case R.id.btn_sign_out:
                    getMainActivity().signOut();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rlRoot = inflater.inflate(R.layout.fragment_main, container, false);
        tvGreeting = (TextView) rlRoot.findViewById(R.id.tv_greeting);
        btnProfileInfo = (Button) rlRoot.findViewById(R.id.btn_profile_info);
        btnProfileInfo.setOnClickListener(clickListener);
        rlRoot.findViewById(R.id.btn_sign_out).setOnClickListener(clickListener);
        if (profileInfo != null) {
            setGreetingText();
        }
        return rlRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (profileInfo != null && isAdded()) {
            btnProfileInfo.setEnabled(true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            profileInfo = savedInstanceState.getParcelable("profileInfo");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("profileInfo", profileInfo);
    }

    private void setGreetingText() {
        tvGreeting.setText(String.format(getString(R.string.greeting_pattern),
                profileInfo.getSalutation(),
                profileInfo.getFirstName(),
                profileInfo.getLastName()));
    }

    @Override
    public void setProfileInfo(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
        if (profileInfo != null) {
            setGreetingText();
            btnProfileInfo.setEnabled(true);
            tvGreeting.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        } else {
            tvGreeting.setText(R.string.error_loading_data);
            tvGreeting.setTextColor(Color.RED);
        }
    }
}
