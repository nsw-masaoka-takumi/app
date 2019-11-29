package jp.pioneer.carsync.application.content;

import android.content.Context;
import android.util.Log;

import com.flurry.android.FlurryAgent;

/**
 * Flurryクラス.
 */
public class FlurryAnalyticsToolStrategy implements AnalyticsToolStrategy{
    /** FlurryのAPI. */
    //public static final String FLURRY_API = "92XY6QSWZJ6BBJ52XC9X";
    public static final String FLURRY_API = "QC5CGWCTJZHDPSG27Q69";
    private static final boolean DBG = false;
    private final static String TAG = FlurryAnalyticsToolStrategy.class.getSimpleName();


    /**
     * セッション開始.
     *
     * @param context Context
     */
    public void startSession(Context context) {
        if (!isSessionStarted()) {
            new FlurryAgent.Builder()
                    .withLogEnabled(true)
                    .withLogLevel(Log.VERBOSE)//TODO:不要？
                    .withIncludeBackgroundSessionsInMetrics(true)
                    .build(context, FLURRY_API);
        }
    }

    /**
     * セッション開始しているか否か.
     */
    private boolean isSessionStarted() {
        return FlurryAgent.isSessionActive();
    }

    public AnalyticsEventSubmitter createEventSubmitter(Analytics.AnalyticsEvent event) {
        return new FlurryAnalyticsEventSubmitter(event);
    }

}