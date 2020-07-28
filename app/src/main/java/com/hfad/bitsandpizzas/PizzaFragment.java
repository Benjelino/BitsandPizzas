package com.hfad.bitsandpizzas;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaFragment extends Fragment {


  public PizzaFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(
        R.layout.fragment_pizza, container, false);
    String[] pizzaNames = new String[Pizza.pizzas.length];
    for (int i = 0; i < pizzaNames.length; i++) {
      pizzaNames[i] = Pizza.pizzas[i].getName();
    }
    int[] pizzaImages = new int[Pizza.pizzas.length];
    for (int i = 0; i < pizzaImages.length; i++) {
      pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
    }
    CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
    pizzaRecycler.setAdapter(adapter);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    pizzaRecycler.setLayoutManager(layoutManager);

    adapter.setListener(new CaptionedImagesAdapter.Listener() {
      public void onClick(int position) {
        Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
        intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
        getActivity().startActivity(intent);
      }
    });
    MySwipeHelper swipeHelper = new MySwipeHelper(getActivity(), pizzaRecycler, 200) {
      @Override
      public void instantiateMyButton(ViewHolder viewHolder, List<MyButton> buffer) {
        buffer.add(new MyButton(getActivity(), "Delete", 0, 50, Color.parseColor("#FF3C30"),
            new MyButtonClickListener() {
              @Override
              public void onClick(int pos) {
                Toast.makeText(getActivity(), "Delete Button has been clicked", Toast.LENGTH_SHORT)
                    .show();
              }
            }));
        buffer.add(new MyButton(getActivity(), "Update", R.drawable.ic_edit_white_24dp, 0,
            Color.parseColor("#FF9502"),
            new MyButtonClickListener() {
              @Override
              public void onClick(int pos) {
                Toast.makeText(getActivity(), "Update Button has been clicked", Toast.LENGTH_SHORT)
                    .show();
              }
            }));
      }
    };
    return pizzaRecycler;
  }

}
