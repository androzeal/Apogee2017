package bitspilani.dvm.apogee2017;


import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sponsors extends android.support.v4.app.Fragment {
    AVLoadingIndicatorView progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sponsors, container, false);
        progress=(AVLoadingIndicatorView) view.findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);

        WebView web = (WebView) view.findViewById(R.id.web);
        web.getSettings().setAllowFileAccess(true);
        WebSettings ws = web.getSettings();
        ws.setSaveFormData(true);
        ws.setJavaScriptEnabled(true);
        ws.setSavePassword(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setAppCacheMaxSize(2048 * 2048);
        ws.setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        ws.setAppCacheEnabled(true);

        web.setWebViewClient(new mwebViewClient());

        if(!isNetworkAvailable())
        {
            Toast.makeText(getActivity(),"No Internet Connectivity", Toast.LENGTH_LONG).show();
            return view;
        }

        web.loadUrl("http://bits-apogee.org/2016/sponsors/");


        return view;
    }
    private class mwebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            progress.setVisibility(View.VISIBLE);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            progress.setVisibility(View.GONE);
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    }

