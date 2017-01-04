package biz.lungo.authtask.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import biz.lungo.authtask.R;
import biz.lungo.authtask.helpers.Utils;
import biz.lungo.authtask.models.ProfileInfo;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    private ProfileInfo profileInfo;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvEmail;
    private TextView tvCountry;
    private TextView tvPhone;
    private ImageView ivAvatar;
    private View pbAvatar;

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            showAvatarProgress(false);
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            showAvatarProgress(false);
            return false;
        }
    };

    public static ProfileFragment newInstance(ProfileInfo profileInfo) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable("profileInfo", profileInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileInfo = getArguments().getParcelable("profileInfo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View svRoot = inflater.inflate(R.layout.fragment_profile, container, false);
        svRoot.findViewById(R.id.btn_refresh).setOnClickListener(this);
        ivAvatar = (ImageView) svRoot.findViewById(R.id.iv_avatar);
        pbAvatar = svRoot.findViewById(R.id.pb_avatar);
        tvEmail = (TextView) svRoot.findViewById(R.id.tv_value_email);
        tvFirstName = (TextView) svRoot.findViewById(R.id.tv_value_first_name);
        tvLastName = (TextView) svRoot.findViewById(R.id.tv_value_last_name);
        tvCountry = (TextView) svRoot.findViewById(R.id.tv_value_country);
        tvPhone = (TextView) svRoot.findViewById(R.id.tv_value_phone);
        fillInfo();
        return svRoot;
    }

    private void fillInfo() {
        if (profileInfo == null) {
            return;
        }
        showAvatarProgress(true);
        tvEmail.setText(profileInfo.getMailAddress());
        tvFirstName.setText(profileInfo.getFirstName());
        tvLastName.setText(profileInfo.getLastName());
        tvCountry.setText(Utils.getCountryByCode(profileInfo.getCountry()));
        tvPhone.setText(profileInfo.getPhone());
        Glide.with(getActivity())
                .load(profileInfo.getAvatarSasUrl())
                .listener(requestListener)
                .into(ivAvatar);
    }

    private void showAvatarProgress(boolean show) {
        if (show) {
            pbAvatar.setVisibility(View.VISIBLE);
            ivAvatar.setVisibility(View.INVISIBLE);
        } else {
            pbAvatar.setVisibility(View.GONE);
            ivAvatar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setProfileInfo(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
        fillInfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_refresh) {
            getMainActivity().getProfileInfo();
        }
    }
}
