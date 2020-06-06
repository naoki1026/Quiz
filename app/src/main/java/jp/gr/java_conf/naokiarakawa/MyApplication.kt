package jp.gr.java_conf.naokiarakawa

import android.app.Application
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setRealm()
        setSoundPool()
        loadStatus()
    }

    private fun loadStatus() {
        val pref = this.getSharedPreferences(PREF_FILE_NAME, android.content.Context.MODE_PRIVATE)
        isPassGrade1 = pref.getBoolean(PrefKey.PASS_GRADE1.name, false)
        isPassGrade2 = pref.getBoolean(PrefKey.PASS_GRADE2.name, false)
    }

    private fun setSoundPool() {
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder().setAudioAttributes(
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).build())
                .setMaxStreams(1)
                .build()
        } else {
            SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }

        soundIdCorrect = soundPool!!.load(this, R.raw.sound_correct, 1)
        soundIdInCorrect = soundPool!!.load(this, R.raw.sound_incorrect, 1)
        soundIdApplaus = soundPool!!.load(this, R.raw.sound_applause, 1)
        soundIdTin = soundPool!!.load(this, R.raw.sound_tin, 1)

    }
    private fun setRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.deleteRealm(config)
    }

    override fun onTerminate() {
        soundPool?.release()
        super.onTerminate()
    }

    companion object {
        lateinit var soundPool: SoundPool
    }
}