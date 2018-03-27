package com.mns.tmdb.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.mns.tmdb.api.MnSMoviesAPIClient;
import com.mns.tmdb.api.MnSMoviesAPIInterface;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSBaseActivity extends Activity {
    private static MnSMoviesAPIInterface mnSMoviesAPIInterface;
    private static ProgressDialog progressDialog;

    protected MnSMoviesAPIInterface getMnSMoviesAPIService() {
        if (mnSMoviesAPIInterface == null) {
            mnSMoviesAPIInterface = MnSMoviesAPIClient.
                    getClient().create(MnSMoviesAPIInterface.class);
        }
        return mnSMoviesAPIInterface;
    }

    protected void showDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
    }

    protected void dismissDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
