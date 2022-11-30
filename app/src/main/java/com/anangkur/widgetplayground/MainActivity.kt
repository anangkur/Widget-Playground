package com.anangkur.widgetplayground

import android.R as ANDROID_R
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.anangkur.widgetplayground.databinding.ActivityMainBinding
import com.anangkur.widgetplayground.extensions.*
import com.anangkur.widgetplayground.richlinkpreview.ViewListener
import com.anangkur.widgetplayground.utils.SpaceTokenizer
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dummyUsers = createDummyUsers()
        val adapter = ArrayAdapter(
            this,
            ANDROID_R.layout.select_dialog_singlechoice,
            dummyUsers,
        )

        binding.editText.movementMethod = LinkMovementMethod.getInstance()
        binding.editText.doAfterTextChanged { editable ->
            editable?.let { Linkify.addLinks(it, Linkify.WEB_URLS) }
        }
        binding.editText.setTokenizer(SpaceTokenizer())
        binding.editText.threshold = 1
        binding.editText.setAdapter(adapter)

        binding.editText.textChanges()
            .debounce(300)
            .onEach { process(it?.toString()) }
            .launchIn(lifecycleScope)
    }

    private fun process(text: String?) {
        val links = text.findAllLinks()
        if (links.isNotEmpty()) {
            binding.richLinkView.setLink(
                links.first().normalizeLink(),
                object : ViewListener {
                    override fun onSuccess(status: Boolean) {
                        runOnUiThread { binding.richLinkView.visible() }
                    }
                    override fun onError(e: Exception?) {
                        runOnUiThread { binding.richLinkView.gone() }
                    }
                },
            )
        }
    }

    private fun createDummyUsers(): List<String> {
        return listOf(
            "@anang",
            "@anangkur",
            "@anangk97",
            "@anangkurniawan",
            "@anangkurniawan97",
            "@anangkurniawan1997",
        )
    }
}