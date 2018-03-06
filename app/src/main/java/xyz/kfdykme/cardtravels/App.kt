package xyz.kfdykme.cardtravels

import android.app.Application
import android.util.Log
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport
import io.realm.*

/**
 * Created by wimkf on 2018/2/26.
 */
public class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Bugly.init(getApplicationContext(), "a768847f42", false);
        Realm.init(this)

        var configuration:RealmConfiguration  = RealmConfiguration
                .Builder()
                .name("cardTravel.realm")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                // .migration(MyMigration())
                .build()

        Realm.setDefaultConfiguration(configuration)
    }

    class MyMigration: RealmMigration {
        override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
           Log.d("Realm","oldVersion is $oldVersion,newVersion is $newVersion")
            var schema = realm?.schema

            if(oldVersion == 2L){
                schema?.get("Card")
                        
                        ?.addField("content",String::class.java)

                Log.d("Realm","when oldVersion is 2,${schema?.get("Card")?.hasField("cardId")} ")
            }

        }

    }
}