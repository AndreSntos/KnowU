package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.example.knowu.R
import com.example.knowu.adapters.ListAdpaterFaq

class FaqActivity : AppCompatActivity() {

    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()
    private lateinit var expandableListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        expandableListView = findViewById(R.id.expandableListView)

        val question1: MutableList<String> = ArrayList()
        question1.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")


        val question2: MutableList<String> = ArrayList()
        question2.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")

        val question3: MutableList<String> = ArrayList()
        question3.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")


        val question4: MutableList<String> = ArrayList()
        question4.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")


        val question5: MutableList<String> = ArrayList()
        question5.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")

        val question6: MutableList<String> = ArrayList()
        question6.add("Vá para home, clique no botão ao\n" +
                "lado direito e preencha as informações.\n" +
                "Após isso clique em “Criar”.")

        header.add("1. Como criar um evento?")
        header.add("2. Como convidar meus amigos?")
        header.add("3. Como mudar meu nome?")
        header.add("4. Como alterar minha senha?")
        header.add("5. Como postar?")
        header.add("6. Como entrar em contato?")

        body.add(question1)
        body.add(question2)
        body.add(question3)
        body.add(question4)
        body.add(question5)
        body.add(question6)

        expandableListView.setAdapter(ListAdpaterFaq(this, expandableListView, header, body))

    }

    fun voltar(v: View) {
        val Main: Intent = Intent(baseContext, ProfileActivity::class.java)
        startActivity(Main)
    }
}