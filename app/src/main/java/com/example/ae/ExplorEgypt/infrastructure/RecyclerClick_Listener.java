package com.example.ae.ExplorEgypt.infrastructure;

import android.view.View;

/**
 * Created by ahmed.E on 4/24/2017.
 */

public interface RecyclerClick_Listener {
    /**
     * Interface for Recycler View Click listener
     **/

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
