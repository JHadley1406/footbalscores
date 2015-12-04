package barqsoft.footballscores.widget;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.model.Match;

/**
 * Created by Josiah Hadley on 11/23/2015.
 */
public class ScoresWidgetProvider implements RemoteViewsService.RemoteViewsFactory
                                            , LoaderManager.LoaderCallbacks<Cursor> {

    private List<Match> mScores = new ArrayList();
    private Context mContext;

    public ScoresWidgetProvider(Context context, Intent intent){
        mContext = context;
    }

    @Override
    public void onCreate() {
        //create cursor here
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mScores.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_list_item);
        mView.setTextViewText(android.R.id.text1, mScores.get(position).toString());
        mView.setTextColor(android.R.id.text1, Color.BLACK);

        final Intent fillInIntent = new Intent();
        fillInIntent.setAction(ScoresWidget.ACTION_TOAST);
        final Bundle bundle = new Bundle();
        bundle.putString(ScoresWidget.EXTRA_STRING, mScores.get(position).toString());
        fillInIntent.putExtras(bundle);
        mView.setOnClickFillInIntent(android.R.id.text1, fillInIntent);

        return mView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext
                , DatabaseContract.scores_table.buildScoreWithDate()
                , null
                , null
                ,{new SimpleDateFormat("yyyy-MM-dd")
                        .format(System.currentTimeMillis())}, null);    //this is so so ugly, please // FIXME: 12/3/15
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
