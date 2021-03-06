package com.munbonecci.cryptprika

import androidx.multidex.MultiDexApplication
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.munbonecci.cryptprika.database.cypher.DataBaseManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp()
class Application : MultiDexApplication(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        DataBaseManager.init(this)
    }
}