package jp.gr.java_conf.naokiarakawa

import android.media.SoundPool

var soundPool : SoundPool? = null

var soundIdCorrect = 0
var soundIdInCorrect = 0
var soundIdApplaus = 0
var soundIdTin = 0

val PREF_FILE_NAME = "jp.gr.java_conf.naokiarakawa.status"

var isPassGrade1 = false
var isPassGrade2 = false
var isSoundOn = false

var isDataFinished = true


enum class PrefKey {
    PASS_GRADE1, PASS_GRADE2
}