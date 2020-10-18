package com.example.roommatefinder.view.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int POST_FRAGMENT_POSITION = 0;
    private static final int STORY_FRAGMENT_POSITION = 1;
    private static final int FOLLOWING_FRAGMENT_POSITION = 2;
    private static final int FOLLOWER_FRAGMENT_POSITION = 3;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.postFeed, R.string.userPosts};
    private final Context mContext;
    private final User user;
    private final AuthToken authToken;

    public SectionsPagerAdapter(Context context, FragmentManager fm, User user, AuthToken authToken) {
        super(fm);
        mContext = context;
        this.user = user;
        this.authToken = authToken;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return PlaceholderFragment.newInstance(position + 1);
        }
        else{
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}