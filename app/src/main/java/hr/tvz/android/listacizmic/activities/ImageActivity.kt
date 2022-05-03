package hr.tvz.android.listacizmic.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import hr.tvz.android.listacizmic.databinding.ActivityImageBinding
import hr.tvz.android.listacizmic.utils.Util
import java.io.File
import java.io.FileOutputStream


class ImageActivity : AppCompatActivity() {
    private lateinit var image: String;
    private val TAG = "LABOS"
    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.image.setImageURI(
            Util.popuri(resources, intent.getIntExtra("uri_src", 0))
        )
        binding.image.setOnClickListener {
            binding.image.animate().apply {
                duration = 3000
                alpha(.5f)
                scaleXBy(-.6f)
                scaleYBy(-.6f)
                rotationXBy(360f)
            }
                .withEndAction {
                binding.image.animate().apply {
                    duration = 3000
                    alpha(1f)
                    scaleXBy(.6f)
                    scaleYBy(.6f)
                    rotationXBy(-360f)
                }
            }.start()
        }

        Log.d(TAG, "onCreate: " + intent.getIntExtra("uri_src", 0))

        binding.wapp.setOnClickListener {
            startActivity(shareDrawable(this, intent.getIntExtra("uri_src", 0), "slika"))
        }
    }


    @WorkerThread
    fun shareDrawable(context: Context, @DrawableRes resourceId: Int, fileName: String): Intent {
        val bitmap = ResourcesCompat.getDrawable(context.resources, resourceId, null)!!.toBitmap()
        val outputFile = File(context.cacheDir, "$fileName.png")
        val outPutStream = FileOutputStream(outputFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        outPutStream.close()
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "auto-generated .png from .xml vector sent through my android application"
        )
        shareIntent.putExtra(
            Intent.EXTRA_STREAM, androidx.core.content.FileProvider.getUriForFile(
                context,
                context.packageName,
                outputFile
            )
        )
        shareIntent.setPackage("com.whatsapp")
        shareIntent.type = "image/png"
        return shareIntent
    }

}
