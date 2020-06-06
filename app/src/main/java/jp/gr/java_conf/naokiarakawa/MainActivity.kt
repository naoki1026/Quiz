package jp.gr.java_conf.naokiarakawa

import android.content.res.AssetManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.opencsv.CSVParser
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_sound.setOnClickListener {
            changeSoundMode(it)
        }

        importQuestionCsv()

    }

    private fun importQuestionCsv() {
        val reader = setCSVReader()
        var tempList : MutableList<Array<String>>? = null

        try {
            val tempList = reader.readAll()
            writeCSVDataToRealm (tempList!!)
        } catch (e: IOException) {
            makeToast(this@MainActivity, getString(R.string.import_fail))
            isDataFinished = false
        } finally {
            reader.close()
        }

    }

    private fun setCSVReader(): CSVReader {
        val assetManager : AssetManager = resources.assets
        val inputStream = assetManager.open("Questions.csv")
        val parser = CSVParserBuilder().withSeparator(',').build()
        return  CSVReaderBuilder(InputStreamReader(inputStream)).withCSVParser(parser).build()
    }

    private fun writeCSVDataToRealm(tempList: MutableList<Array<String>>) {

        val realm = Realm.getDefaultInstance()
        val iterator = tempList.iterator()
        realm.executeTransactionAsync({
            while (iterator.hasNext()) {
                val record = iterator.next()
                val questionDB = it.createObject(QuestionModel::class.java)
                questionDB.apply {
                    id = record[0]
                    gradeId = record[1]
                    question = record[2]
                    answer = record[3]
                    choice1 = record[4]
                    choice2 = record[5]
                    choice3 = record[6]
                    explanation = record[7]
                }
            }
        }, {
            isDataFinished = true
            makeToast(this@MainActivity, getString(R.string.import_success))
        }, {
            isDataFinished = false
            makeToast(this@MainActivity, getString(R.string.import_fail))
        })


    }

}
