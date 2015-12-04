package barqsoft.footballscores.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.widget.ScoresWidgetProvider;

/**
 * Created by Josiah Hadley on 12/3/2015.
 */
public class ScoresWidgetService extends RemoteViewsService {

    @Override
    public ScoresWidgetProvider onGetViewFactory(Intent intent){

        ScoresWidgetProvider dataProvider = new ScoresWidgetProvider(
                getApplicationContext(), intent);

        return dataProvider;
    }
}
