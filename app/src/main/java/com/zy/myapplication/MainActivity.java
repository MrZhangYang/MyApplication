package com.zy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zy.myapplication.entry.Photo;
import com.zy.myapplication.util.PermissionsUtils;
import com.zy.myapplication.util.PhotoRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionsUtils.getPermissions(this);

        List<Photo> photos = PhotoRepository.getPhoto(this);

        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new Adapter(photos));
    }

    public void find(View view) {
        Intent intent = new Intent(MainActivity.this, GroupActivity.class);
        startActivity(intent);
    }

    private class Adapter extends BaseAdapter {
        List<Photo> photos;

        public Adapter(List<Photo> photos) {
            this.photos = photos;
        }

        @Override
        public int getCount() {
            return photos != null ? photos.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Photo photo = photos.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_image, parent, false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

            Glide.with(MainActivity.this)
                    .load(photo.getPath())
                    .centerCrop()
                    .crossFade()
                    .into(imageView);

            return convertView;
        }
    }
}
