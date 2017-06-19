package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterYourPlans;
import com.example.ae.ExplorEgypt.infrastructure.RecyclerClick_Listener;
import com.example.ae.ExplorEgypt.infrastructure.RecyclerTouchListener;
import com.example.ae.ExplorEgypt.infrastructure.Toolbar_ActionMode_Callback;
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.google.gson.Gson;

import java.util.ArrayList;

public class YourPlanes extends BaseActivity {

    //Action Mode for toolbar
    private ActionMode mActionMode;
    private ArrayList<Plan> myPlans = new ArrayList<>();
    private RecyclerAdapterYourPlans adapter;
    private RecyclerView plansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_planes);

        getSupportActionBar().setTitle("Your Plans");
        String place1, place2, place3, place4, place5;
        place1 = "{\"address\":\"1 Abou al hool street, Giza, Egypt, Nazlet el Samman 12561\",\"city\":\"Giza\",\"phone\":\"2\",\"description\":\"A block from the Pyramids of Giza complex, this modest guesthouse is also a 10-minute walk from the ancient Khufu ship, now a museum.\\r\\nThe simple, air-conditioned rooms offer private bathrooms, free Wi-Fi and views of the Pyramids. Room service is available.\\r\\nFreebies include breakfast tea, coffee and mineral water. There\\u0027s also a terrace overlooking the Pyramids and Sphinx.\",\"type_id\":2,\"id\":29,\"images\":\"http://ucd.hwstatic.com/propertyimages/6/69554/1.jpg , https://lh5.googleusercontent.com/-6Qeq1lQep_c/VNA22Ytyh-I/AAAAAAAAABE/kA-ZBOQmQBYL8tcAhg7vHdhz7m2QjTB2ACJkC/w408-h282-k-no/ , https://media-cdn.tripadvisor.com/media/photo-s/09/fd/2d/88/guardian-guest-house.jpg\",\"location\":\"29.975066,31.141047\",\"name\":\"Guardian Guest House\",\"rate\":5.0,\"social\":\"none\",\"website\":\"https://www.booking.com/hotel/eg/guardian-guest-house.en-gb.html?aid\\u003d357026;label\\u003dgog235jc-hotel-XX-eg-guardianNguestNhouse-unspec-eg-com-L%3Aen-O%3AwindowsS7-B%3Aopera-N%3AXX-S%3Abo-U%3AXX-H%3As;sid\\u003dc8722d00f2bf3bc680726aeaddcf4133;dist\\u003d0\\u0026sb_price_type\\u003dt\"}";
        place2 = "{\"address\":\"about 13 km 8 mi southwest of Cairo city centre\",\"city\":\"Giza\",\"phone\":\"0\",\"description\":\"includes the three pyramids, Great Sphinx and several cemeteries\\u0027\\r\\n,\\u0027about 13 km 8 mi southwest of Cairo city centre\",\"type_id\":3,\"id\":1,\"images\":\"http://ibelieveinsci.com/wp-content/uploads/925161.jpg ,http://www.thewholeworldisaplayground.com/wp-content/uploads/2014/07/Pyramids-of-Giza-3.jpg , http://www.nationalgeographic.com/content/dam/archaeologyandhistory/rights-exempt/history-magazine/2017/01-02/giza-pyramids/giza-plateau-pyramids.jpg , http://www.planetware.com/photos-large/EGY/egypt-cairo-pyramids-of-giza-and%20camels-2.jpg\",\"location\":\"29.979216, 31.134266\",\"name\":\"the giza pyramids\",\"rate\":5.0,\"social\":\"https://www.google.com.eg/url?sa\\u003dt\\u0026rct\\u003dj\\u0026q\\u003d\\u0026esrc\\u003ds\\u0026source\\u003dweb\\u0026cd\\u003d11\\u0026cad\\u003drja\\u0026uact\\u003d8\\u0026ved\\u003d0ahUKEwjIjs2czbDUAhXGmBoKHXgyDQAQFghaMAo\\u0026url\\u003dhttps%3A%2F%2Far-ar.facebook.com%2Fpages%2FGiza-pyramids-area-%25D8%25A7%25D9%2584%25D8%25B5%25D9%2581%25D8%25AD%25D8%25A9-\",\"website\":\"https://www.google.com.eg/url?sa\\u003dt\\u0026rct\\u003dj\\u0026q\\u003d\\u0026esrc\\u003ds\\u0026source\\u003dweb\\u0026cd\\u003d1\\u0026cad\\u003drja\\u0026uact\\u003d8\\u0026ved\\u003d0ahUKEwjIjs2czbDUAhXGmBoKHXgyDQAQFgggMAA\\u0026url\\u003dhttps%3A%2F%2Far.wikipedia.org%2Fwiki%2F%25D8%25A3%25D9%2587%25D8%25B1%25D8%25A7%25D9%2585_%25D8%25A7%25D9%2584%25D8%25AC%25\"}";
        place3 = "{\"address\":\"Al Montazah, Heliopolis, Cairo\",\"city\":\"Cairo\",\"phone\":\"224800100\",\"description\":\"The Italian Maestro is a nice restaurant that serves Italian cuisine in an appetizing way. It is clear that the chef and the chefs are Italian, especially pasta.\",\"type_id\":1,\"id\":18,\"images\":\"https://media-cdn.tripadvisor.com/media/photo-s/04/cb/d2/68/lovely-italian-atmosphere.jpg , https://nyoobserver.files.wordpress.com/2015/12/spartina-interior2.jpg?quality\\u003d80\\u0026w\\u003d635 , http://www.cairoscene.com/Content/Admin/Uploads/Articles/ArticlesMainPhoto/587257/80b62115-6608-4024-9336-caf4e582fe0f.jpg \",\"location\":\"30.092164, 31.320376\",\"name\":\"\u202AMaestro Italian Restaurant\u202C\",\"rate\":4.0,\"social\":\"https://www.facebook.com/IlMaestroRistorante/\",\"website\":\"https://www.tripadvisor.com.eg/Restaurant_Review-g294201-d2608254-Reviews-Maestro_Italian_Restaurant-Cairo_Cairo_Governorate.html\"}";
        place4 = "{\"address\":\"El-Gamaleya, Qism El-Gamaleya, Cairo Governorate\",\"city\":\"Cairo\",\"phone\":\"0\",\"description\":\"Khan el-Khalili is a major souk in the Islamic district of Cairo.\\r\\nThe bazaar district is one of Cairo main attractions for tourists and Egyptians alike\",\"type_id\":4,\"id\":4,\"images\":\"https://c1.staticflickr.com/2/1390/5146069762_05010b2585_b.jpg , http://images.memphistours.com/large/10030039_476853356_e291608419_o.jpg , https://farm1.staticflickr.com/456/19661583816_023e9e4748.jpg\",\"location\":\"30.048166, 31.263541\",\"name\":\"Khan el-Khalili\",\"rate\":4.0,\"social\":\"https://www.google.com.eg/url?sa\\u003dt\\u0026rct\\u003dj\\u0026q\\u003d\\u0026esrc\\u003ds\\u0026source\\u003dweb\\u0026cd\\u003d13\\u0026cad\\u003drja\\u0026uact\\u003d8\\u0026ved\\u003d0ahUKEwjNzKvPzrDUAhUB7hoKHb7xCQMQFghgMAw\\u0026url\\u003dhttps%3A%2F%2Fwww.facebook.com%2Fkhanelkhaliliusa%2F\\u0026usg\\u003dAFQjCNGPeun5Ut2AIKWGLFOLQIOd-K3_mQ\",\"website\":\"https://en.wikipedia.org/wiki/Khan_el-Khalili\"}";
        place5 = "{\"address\":\"Cairo, Egypt\",\"city\":\"Cairo\",\"phone\":\"225794596\",\"description\":\"The Museum of Egyptian Antiquities, known commonly as the Egyptian Museum or Museum of Cairo,\\r\\nin Cairo, Egypt, is home to an extensive collection of ancient Egyptian antiquities. \\r\\nIt has 120,000 items, with a representative amount on display, the remainder in storerooms. \\r\\nThe edifice is one of the largest museums in the region. As of February 2017, \\r\\nthe museum is open to the public.\",\"type_id\":3,\"id\":2,\"images\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/The_Egyptian_Museum.jpg/1200px-The_Egyptian_Museum.jpg , \\nhttp://english.ahram.org.eg/Media/News/2016/1/7/2016-635877734458981928-898.JPG ,\\nhttp://itinerary.colatour.com.tw/COLA_AppFiles/A03A_Tour/PictureObj/00015280.JPG , \\nhttp://www.bestourism.com/img/items/big/6772/Egyptian-Museum-in-Cairo_Museum-exterior-view_7390.jpg , \\nhttp://2.bp.blogspot.com/-LdKv5OCaPl0/TyEXfxtQpgI/AAAAAAAAADk/cWtPRjhTI6E/s1600/Egyptian+Museum+%25281%2529.jpg , \\nhttp://www.shaspo.com/app/webroot/img/upload/fa661_20090204_egypt_0361.jpg\",\"location\":\"30.04814, 31.23365\",\"name\":\"Egyptian Museum\",\"rate\":5.0,\"social\":\"https://www.google.com.eg/url?sa\\u003dt\\u0026rct\\u003dj\\u0026q\\u003d\\u0026esrc\\u003ds\\u0026source\\u003dweb\\u0026cd\\u003d9\\u0026cad\\u003drja\\u0026uact\\u003d8\\u0026ved\\u003d0ahUKEwiI6qHvzbDUAhVLWhoKHSBWBRYQFghRMAg\\u0026url\\u003dhttps%3A%2F%2Far-ar.facebook.com%2FEgyptianMuseumCairo%2F\\u0026usg\\u003dAFQjCNGXCuoWqdH3PvyRQBIoEaGP0Txmsg\",\"website\":\"https://en.wikipedia.org/wiki/Egyptian_Museum\"}";

        ArrayList<PlaceDataModel> d1place1 = new ArrayList<>();
        Gson gson = new Gson();
        PlaceDataModel thePlace1 = gson.fromJson(place2, PlaceDataModel.class);
        d1place1.add(thePlace1);

        ArrayList<PlaceDataModel> d1place2 = new ArrayList<>();
        PlaceDataModel thePlace2 = gson.fromJson(place1, PlaceDataModel.class);
        d1place2.add(thePlace2);

        ArrayList<PlaceDataModel> d1place3 = new ArrayList<>();
        PlaceDataModel thePlace3 = gson.fromJson(place3, PlaceDataModel.class);
        d1place3.add(thePlace3);

        ArrayList<PlaceDataModel> d1place4 = new ArrayList<>();
        PlaceDataModel thePlace4 = gson.fromJson(place4, PlaceDataModel.class);
        d1place4.add(thePlace4);

        ArrayList<PlaceDataModel> d1place5 = new ArrayList<>();
        PlaceDataModel thePlace5 = gson.fromJson(place5, PlaceDataModel.class);
        d1place5.add(thePlace5);


        /*ArrayList<PlaceDataModel> listOfPlaces1 = new ArrayList<>();
        listOfPlaces1.add(new PlaceDataModel("Hotel",getString(R.string.temp_text),getString(R.string.hotel_image),"","","29.967803, 31.129438","","","",0));

        ArrayList<PlaceDataModel> listOfPlaces2 = new ArrayList<>();
        listOfPlaces2.add(new PlaceDataModel("Pyramid",getString(R.string.temp_text),getString(R.string.pyramids_image),"","","29.967803, 31.129438","2","","",0));
*/
        ArrayList<PairOfDayAndPlace> placesInPlan = new ArrayList<>();
        //ArrayList<PairOfDayAndPlace> placesInPlan2 = new ArrayList<>();

        placesInPlan.add(new PairOfDayAndPlace(1,d1place2));
        placesInPlan.add(new PairOfDayAndPlace(1,d1place1));
        placesInPlan.add(new PairOfDayAndPlace(1,d1place3));

        placesInPlan.add(new PairOfDayAndPlace(2,d1place2));
        placesInPlan.add(new PairOfDayAndPlace(2,d1place5));
        placesInPlan.add(new PairOfDayAndPlace(2,d1place4));

        /*
        placesInPlan2.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan2.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan2.add(new PairOfDayAndPlace(3,listOfPlaces1));

        placesInPlan2.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan2.add(new PairOfDayAndPlace(3,listOfPlaces1));
        placesInPlan2.add(new PairOfDayAndPlace(1,listOfPlaces1));
*/
        //myPlans.add(new Plan("Visit egypt", "22/5/2020","30/5/2020",false,placesInPlan));
        myPlans.add(new Plan("Giza is near", "1/8/2017","3/8/2017",true,placesInPlan));

        String planJson = new Gson().toJson(myPlans);

        plansList = (RecyclerView) findViewById(R.id.activity_your_plans_recyclerView);
        plansList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterYourPlans(myPlans, this);
        plansList.setAdapter(adapter);

        implementRecyclerViewClickListeners();

        if(!isNetworkAvailable()){
            Toast.makeText(application, "No connection!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }


    //Implement item click and long click over recycler view
    private void implementRecyclerViewClickListeners() {
        plansList.
                addOnItemTouchListener(new RecyclerTouchListener(this, plansList, new RecyclerClick_Listener() {
                    @Override
                    public void onClick(View view, int position) {
                        //If ActionMode not null select item
                        if (mActionMode != null)
                            onListItemSelect(position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        //Select item on long click
                        onListItemSelect(position);
                    }
                }));
    }

    //List item select method
    private void onListItemSelect(int position) {
        adapter.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = startSupportActionMode(new Toolbar_ActionMode_Callback( this,adapter, myPlans, this));
        else if (!hasCheckedItems && mActionMode != null) {
            // there no selected items, finish the actionMode
            mActionMode.finish();
            adapter.removeSelection();
        }
            //for the edit option if selected more than one item
        if(mActionMode != null) {
            if (adapter.getSelectedCount() > 1) {
                mActionMode.getMenu().findItem(R.id.activity_your_plans_menu_edit).setVisible(false);
            } else {
                mActionMode.getMenu().findItem(R.id.activity_your_plans_menu_edit).setVisible(true);
            }
        }
        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(adapter
                    .getSelectedCount()) + " selected");
    }
    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null) {
            mActionMode = null;
            adapter.removeSelection();
        }

    }

    //Delete selected rows
    public void deleteRows() {
        SparseBooleanArray selected = adapter
                .getSelectedIds();//Get selected ids

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                myPlans.remove(selected.keyAt(i));
                //TODO: update the database with the deleted plan
                adapter.notifyDataSetChanged();//notify adapter
            }
        }
        Toast.makeText(this, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
        mActionMode.finish();//Finish action mode after use
        setNullToActionMode();

    }

    //Edit existing plan
    public void editPlan(){

        //TODO: think about how to handle this object after leaving the activity
        mActionMode.finish();
        setNullToActionMode();

        Intent editIntent = new Intent(getApplication(),CreatePlanActivity.class);
        SparseBooleanArray itemSelected = adapter.getSelectedIds();
        Plan passedPlan = myPlans.get(itemSelected.keyAt(0));
        editIntent.putExtra("edit_plan", passedPlan);
        startActivity(editIntent);
    }
}
