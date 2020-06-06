package jp.gr.java_conf.naokiarakawa

import android.content.Context
import android.media.SoundPool
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton


fun SoundPool.play2 (soundId : Int) {
    this.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
}

fun makeToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun changeSoundMode(v : View){
    var fab = v as FloatingActionButton

    isSoundOn = !isSoundOn
    if (isSoundOn) {
        fab.setImageResource(R.drawable.ic_volume_up_black_24dp)
    } else {
        fab.setImageResource(R.drawable.ic_volume_off_black_24dp)
    }
}