package sophia.com.realmsandbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import sophia.com.realmsandbox.Model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm realm = Realm.getDefaultInstance();


        // Query Realm for all dogs younger than 2 years old
        final RealmResults<Product> products =
                realm.where(Product.class).findAll();
        products.size(); // => 0 because no product have been added to the Realm yet
        Log.d("RealmResult",String.valueOf(products.size()));

        Product p = new Product();
        p.setProductId(2);
        p.setTitle("Prodotto 2");
        p.setDescription("Desc 2");
        p.setPrice(99.99);
        p.setPhoto("https://realm.io/assets/svg/general_logo.svg");

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(p);
        realm.commitTransaction();

        products.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Product>>() {
            @Override
            public void onChange(RealmResults<Product> products, OrderedCollectionChangeSet changeSet) {
                changeSet.getInsertions();

                Log.d("RealmResult",String.valueOf(products.size()));
            }
        });
    }
}
