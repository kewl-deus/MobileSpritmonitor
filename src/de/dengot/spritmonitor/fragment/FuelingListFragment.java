package de.dengot.spritmonitor.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.persistence.loader.FuelingCursorLoader;
import de.dengot.spritmonitor.widget.FuelingCursorAdapter;

public class FuelingListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FuelingListFragment.class.getSimpleName();

    private static final int LOADER_ID = (TAG + "." + FuelingCursorLoader.TAG).hashCode();

    private FuelingCursorAdapter cursorAdapter;
    private long vechileId;

    public FuelingListFragment(long vechileId) {
        this.vechileId = vechileId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final Bundle noLoaderArgs = null;
        getLoaderManager().initLoader(LOADER_ID, noLoaderArgs, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("No fuelings");
        
        final Cursor noCursor = null;
        cursorAdapter = new FuelingCursorAdapter(getActivity(), R.layout.fuelinglist_item, noCursor);
        setListAdapter(cursorAdapter);

        // Start out with a progress indicator.
        setListShown(false);
    }

    //------------------------ LoaderCallbacks -----------------------------------------------

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new FuelingCursorLoader(getActivity(), vechileId);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor data) {
        this.cursorAdapter.swapCursor(data);
        if (isResumed()){
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        this.cursorAdapter.swapCursor(null);
    }
}
