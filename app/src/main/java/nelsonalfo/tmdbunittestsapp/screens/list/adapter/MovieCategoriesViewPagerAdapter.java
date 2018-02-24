package nelsonalfo.tmdbunittestsapp.screens.list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nelsonalfo.tmdbunittestsapp.models.Constants;
import nelsonalfo.tmdbunittestsapp.screens.list.MoviesTabFragment;


/**
 * Created by nelso on 23/2/2018.
 */

public class MovieCategoriesViewPagerAdapter extends FragmentPagerAdapter {
    private static final int TOP_RATED_TAB = 0;
    private static final int POPULAR_TAB = 1;
    private static final int UPCOMING_TAB = 2;

    private MoviesTabFragment popularMoviesFragment;
    private MoviesTabFragment topRatedMoviesFragment;
    private MoviesTabFragment upcomingFragment;

    public MovieCategoriesViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        popularMoviesFragment = MoviesTabFragment.newInstance(Constants.MOST_POPULAR_MOVIES);
        topRatedMoviesFragment = MoviesTabFragment.newInstance(Constants.TOP_RATED_MOVIES);
        upcomingFragment = MoviesTabFragment.newInstance(Constants.UPCOMING_MOVIES);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TOP_RATED_TAB:
                return topRatedMoviesFragment;
            case POPULAR_TAB:
                return popularMoviesFragment;
            case UPCOMING_TAB:
                return upcomingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
