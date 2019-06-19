package com.example.nihal.medeasy.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.example.nihal.medeasy.Adapters.AddMedcineAdapter;
import com.example.nihal.medeasy.Models.Drugs;
import com.example.nihal.medeasy.Models.UserModel;
import com.example.nihal.medeasy.R;
import com.example.nihal.medeasy.Utils.Constants;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.orhanobut.hawk.Hawk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private com.github.mikephil.charting.charts.LineChart chart;
    private com.github.mikephil.charting.charts.LineChart chart1;
    CircleImageView profile_image;
    TextView name, gender, weight, height, age, blood_type, relation;
    RecyclerView RV;
    AddMedcineAdapter addMedcineAdapter;
    ArrayList<Drugs> drugs;
    StorageReference mImageRef;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference mDatabase;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //declartion
        chart = view.findViewById(R.id.chart1);
        chart1 = view.findViewById(R.id.chart1);
        profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(ProfileFragment.this)
                        .folderMode(true) // folder mode (false by default)
                        .toolbarFolderTitle("Folder") // folder selection title
                        .toolbarImageTitle("Tap to select") // image selection title
                        .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                        .includeVideo(false) // Show video on image picker
                        .multi() // multi mode (default mode)
                        .limit(1) // max images can be selected (99 by default)
                        .showCamera(true) // show camera or not (true by default)
                        .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                        .enableLog(false) // disabling log
                        .start(); // start image picker activity with request code
            }
        });
        try {
            mImageRef =
                    FirebaseStorage.getInstance().getReference("images/"+Hawk.get(Constants.userID)+"");

            final long ONE_MEGABYTE = 1024 * 1024;
            mImageRef.getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Log.d("TTTTTTTTTTT", "onSuccess: "+bytes.length);
                            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            DisplayMetrics dm = new DisplayMetrics();
                            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                            profile_image.setImageBitmap(bm);
                        }

                    });
            mImageRef.getBytes(ONE_MEGABYTE).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TTTTTTTTTTT", "onSuccess: "+e.getMessage());

                }
            });
        } catch (Exception e) {
            Log.d("TTTTTTTTTTT", "onSuccess: "+e.getMessage());

        }
        name = view.findViewById(R.id.name);
        gender = view.findViewById(R.id.gender);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        age = view.findViewById(R.id.age);
        blood_type = view.findViewById(R.id.blood_type);
        relation = view.findViewById(R.id.relation);
        returnData();
        draw_chart1();
        draw_chart2();
        RV = view.findViewById(R.id.RV);
        RV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        drugs = new ArrayList<>();
        addMedcineAdapter = new AddMedcineAdapter(drugs, new AddMedcineAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {

            }
        });
        RV.setAdapter(addMedcineAdapter);
        retrieveMedicine();
        return view;
    }

    private void returnData() {
        mDatabase.child("Users").child(Hawk.get(Constants.userID) + "")
                .child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    name.setText(userModel.getUserName() + "");
                    if (userModel.getGender().equals("0")) {
                        gender.setText("Male");

                    } else {
                        gender.setText("female");

                    }
                    weight.setText(userModel.getWeight());
                    height.setText(userModel.getHeight());

                    Log.d("TTTT", "onDataChange: " + Calendar.getInstance().get(Calendar.YEAR));
                    Log.d("TTTT", "onDataChange: " + Integer.parseInt(userModel.getYearOfBirth()));
                    age.setText(((Calendar.getInstance().get(Calendar.YEAR))
                            - Integer.parseInt(userModel.getYearOfBirth())) + "");
                    //blood_type.setText(userModel.get());
                    //relation.setText(userModel.get());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void draw_chart1() {
        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundResource(R.color.colorPrimary);


        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        // y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        chart.getAxisRight().setEnabled(true);


        chart.getLegend().setEnabled(true);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        setData1(11, 12);

        chart.invalidate();

    }

    private void draw_chart2() {
        chart1.setViewPortOffsets(0, 0, 0, 0);
        chart1.setBackgroundResource(R.color.colorPrimary);
        // no description text
        chart1.getDescription().setEnabled(false);
        // enable touch gestures
        chart1.setTouchEnabled(true);
        // enable scaling and dragging
        chart1.setDragEnabled(true);
        chart1.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        chart1.setPinchZoom(false);
        chart1.setDrawGridBackground(false);
        chart1.setMaxHighlightDistance(300);
        XAxis x = chart1.getXAxis();
        x.setEnabled(false);
        YAxis y = chart1.getAxisLeft();
        // y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        chart1.getAxisRight().setEnabled(true);


        chart1.getLegend().setEnabled(true);

        chart1.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        setData2(11, 12);

        chart1.invalidate();

    }

    private void setData1(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            set1.setDrawCircles(true);
            chart.animateXY(2000, 2000);
            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(true);
            // set data
            chart.setData(data);
        }
    }

    private void setData2(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart1.getAxisLeft().getAxisMinimum();
                }
            });
            set1.setDrawCircles(true);
            chart1.animateXY(2000, 2000);
            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(true);
            // set data
            chart1.setData(data);
        }
    }

    private void retrieveMedicine() {

        final Query q = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child("1UbTozyso3SR8ZY7y0O6mZxTVqd2/Roshetat");


        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    Log.d("TTTTTT", "onDataChange: " + dataSnap.toString());

                    for (final DataSnapshot tData : dataSnap.child("Rosheta").getChildren()) {
                        Log.d("TTTTTTT", "onDataChange: " + tData.toString());

                        for (final DataSnapshot l : tData.getChildren()) {
                            Log.d("TTTTTTTT", "onDataChange: " + l.toString());
                            try {
                                Drugs drug = l.getValue(Drugs.class);
                                drugs.add(drug);
                                Log.d("TTTTTTTT", "onDataChange: " + drugs.size());

                            } catch (Exception e) {
                            }

                        }
                    }
                }

                addMedcineAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void uploadImage(Uri filePath) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + Hawk.get(Constants.userID)+"");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        } else {
            Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            Bitmap myBitmap = BitmapFactory.decodeFile(image.getPath());
            profile_image.setImageBitmap(myBitmap);
            File file = new File(image.getPath());
            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("parameters[0]", file.getName(), reqFile);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), myBitmap, "Title", null);

            uploadImage(Uri.parse(path));
        }
        //uploadPhotoService(body);
    }
}