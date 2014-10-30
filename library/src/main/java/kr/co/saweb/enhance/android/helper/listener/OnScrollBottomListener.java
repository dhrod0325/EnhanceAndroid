package kr.co.saweb.enhance.android.helper.listener;

import android.os.AsyncTask;
import android.widget.AbsListView;

/**
 * Created by OKS on 2014-10-20.
 */
public abstract class OnScrollBottomListener implements AbsListView.OnScrollListener {
    private boolean lock = false;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int f, int v, int t) {
        int count = t - v;

        if (f >= count && t != 0 && count > 0 && !lock) {
            new IsBottomTask(absListView, f, v, t).execute();
        }
    }

    private class IsBottomTask extends AsyncTask<Void, Boolean, Boolean> {
        AbsListView absListView;
        int f;
        int v;
        int t;

        private IsBottomTask(AbsListView absListView, int f, int v, int t) {
            this.absListView = absListView;
            this.f = f;
            this.v = v;
            this.t = t;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            lock = true;
            OnScrollBottomListener.this.doInBackground(absListView, f, v, t);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                lock = false;
                OnScrollBottomListener.this.onPostExecute(absListView, f, v, t);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            OnScrollBottomListener.this.onPreExecute(absListView, f, v, t);
        }
    }

    public abstract void onPreExecute(AbsListView absListView, int f, int v, int t);

    public abstract void doInBackground(AbsListView absListView, int f, int v, int t);

    public abstract void onPostExecute(AbsListView absListView, int f, int v, int t);
}