package nnconsulting.app.spacia.utils;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCommon<T> {
    public Spinner mSpinner;
    public SpinnerAdapter<T> mAdapter;

    public SpinnerCommon(Spinner spinner) {
        this(spinner, new ArrayList<T>(), new SpinnerAdapter<T>() {
        }, false, android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item);
    }

    public SpinnerCommon(Spinner spinner, List<T> objects) {
        this(spinner, objects, new SpinnerAdapter<T>() {
        }, false, android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item);
    }

    public SpinnerCommon(Spinner spinner, List<T> objects, SpinnerAdapter<T> adapter) {
        this(spinner, objects, adapter, false, android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item);
    }

    public SpinnerCommon(Spinner spinner, List<T> objects, SpinnerAdapter<T> adapter, boolean hasHint) {
        this(spinner, objects, adapter, hasHint, android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item);
    }

    public SpinnerCommon(Spinner spinner, ArrayList<T> objects, boolean hasHint, @LayoutRes int resourceId, @LayoutRes int dropDownResourceId) {
        this(spinner, objects, new SpinnerAdapter<T>() {
        }, hasHint, resourceId, dropDownResourceId);
    }

    public SpinnerCommon(Spinner spinner, List<T> objects, SpinnerAdapter<T> adapter, boolean hasHint, @LayoutRes int resourceId, @LayoutRes int dropDownResourceId) {
        mSpinner = spinner;
        mAdapter = adapter;
        mAdapter.setObjects(objects);
        mAdapter.setResource(resourceId);
        mAdapter.setHasHint(hasHint);
        mAdapter.setDropDownViewResource(dropDownResourceId);
        mAdapter.setInflater(LayoutInflater.from(spinner.getContext()));
        mSpinner.setAdapter(mAdapter);
    }

    public T getItem(int position) {
        return mAdapter.getItem(position);
    }

    public void add(T object) {
        mAdapter.add(object);
    }

    public void clear() {
        mAdapter.clear();
    }

    public static abstract class SpinnerAdapter<TT> extends BaseAdapter {
        private final Object mLock = new Object();
        private LayoutInflater mInflater;
        private List<TT> mObjects;
        private int mDropDownResource;
        private int mResource;
        private boolean hasHint;
        private int mFieldId = 0;

        public void add(TT object) {
            synchronized (mLock) {
                mObjects.add(object);
            }
            notifyDataSetChanged();
        }

        public void clear() {
            synchronized (mLock) {
                mObjects.clear();

            }
            notifyDataSetChanged();
        }

        public void setFieldId(int fieldId) {
            this.mFieldId = mFieldId;
        }

        public void setHasHint(boolean hasHint) {
            this.hasHint = hasHint;
        }

        public long getItemId(int position) {
            return position;
        }

        public TT getItem(int position) {
            return mObjects.get(position);
        }

        public String getItemRepresentation(int position) {
            return mObjects.get(position).toString();
        }

        public int getCount() {
            return mObjects.size() - (hasHint ? 1 : 0);
        }

        public void setResource(@LayoutRes int mResource) {
            this.mResource = mResource;
        }

        public void setInflater(LayoutInflater mInflater) {
            this.mInflater = mInflater;
        }

        public void setObjects(List<TT> mObjects) {
            this.mObjects = mObjects;
        }

        public void setDropDownViewResource(@LayoutRes int resource) {
            this.mDropDownResource = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(mInflater, position, convertView, parent, mResource);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(mInflater, position, convertView, parent, mDropDownResource);
        }

        private View createViewFromResource(LayoutInflater inflater, int position, View convertView, ViewGroup parent, int resource) {
            View view;
            TextView text;

            if (convertView == null) {
                view = inflater.inflate(resource, parent, false);
            } else {
                view = convertView;
            }

            try {
                if (mFieldId == 0) {
                    //  If no custom field is assigned, assume the whole resource is a TextView
                    text = (TextView) view;
                } else {
                    //  Otherwise, find the TextView field within the layout
                    text = (TextView) view.findViewById(mFieldId);
                }
            } catch (ClassCastException e) {
                Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e);
            }

            String item = getItemRepresentation(position);
            text.setText(item);

            return view;
        }
    }
}