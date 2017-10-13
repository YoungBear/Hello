package com.example.hello.adapter.viewholder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.glide.GlideApp;
import com.example.hello.model.easyrecyclerview.Person;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class PersonViewHolder extends BaseViewHolder<Person> {
    private TextView mTv_name;
    private ImageView mImg_face;
    private TextView mTv_sign;


    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_person);
        mTv_name = $(R.id.person_name);
        mTv_sign = $(R.id.person_sign);
        mImg_face = $(R.id.person_face);
    }

    @Override
    public void setData(final Person person) {
        Log.i("ViewHolder", "position" + getDataPosition());
        mTv_name.setText(person.getName());
        mTv_sign.setText(person.getSign());
        GlideApp.with(getContext())
                .load(person.getFace())
                .placeholder(R.drawable.default_image)
                .into(mImg_face);
    }
}
