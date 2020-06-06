package jp.gr.java_conf.naokiarakawa

import io.realm.Realm
import io.realm.RealmObject

open class QuestionModel : RealmObject() {

    var id : String = ""

    var gradeId : String = ""

    var  question : String = ""

    var answer : String = ""

    var choice1 : String = ""

    var choice2 : String = ""

    var choice3 : String = ""

    var explanation : String = ""


}