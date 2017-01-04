package biz.lungo.authtask.fragments;

import android.app.Fragment;

import biz.lungo.authtask.activities.MainActivity;
import biz.lungo.authtask.models.ProfileInfo;

public abstract class BaseFragment extends Fragment {

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public abstract void setProfileInfo(ProfileInfo profileInfo);

}
