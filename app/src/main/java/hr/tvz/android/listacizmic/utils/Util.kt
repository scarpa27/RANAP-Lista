package hr.tvz.android.listacizmic.utils

import android.content.ContentResolver
import android.content.res.Resources
import android.net.Uri

class Util {
    
    companion object {
        fun popuri(resources: Resources, id: Int): Uri {
            return Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(id))
                .appendPath(resources.getResourceTypeName(id))
                .appendPath(resources.getResourceEntryName(id))
                .build()
        }
    }

}