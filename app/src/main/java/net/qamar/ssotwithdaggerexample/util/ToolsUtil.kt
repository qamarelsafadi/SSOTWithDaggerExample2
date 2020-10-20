
package net.qamar.ssotwithdaggerexample.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore.Images
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*


class ToolsUtil {

    companion object {
        private val SPLASH_TIME_OUT:Long=3000 // 3 sec
        val DEEP_LINK_PREFIX = "/sucesspage"


        fun hideStatusBar(context: Activity) {
            context.window.requestFeature(Window.FEATURE_NO_TITLE)
            context.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )


        }
            fun isEmailValid(email: CharSequence): Boolean {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
           fun isPhoneValid(email: CharSequence): Boolean {
                return android.util.Patterns.PHONE.matcher(email).matches()
            }



        fun shareText(subject: String, body: String , context: Context) {
            val txtIntent = Intent(android.content.Intent.ACTION_SEND)
            txtIntent.type = "text/plain"
            txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
            txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, body)
            context.startActivity(Intent.createChooser(txtIntent, "Share"))
        }
        fun isNetworkConnected(context: Context):Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }
//        fun isOnline(context:Context):Boolean {
//            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            return (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting())
//        }
        fun hideKeyboard(view: View , activity: Activity) {
            val inputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
        }  fun hideKeyboard(view: View , activity: FragmentActivity) {
            val inputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun showDialog(progressDialog : ProgressDialog) {
            progressDialog.setMessage("Loading Data...")
            progressDialog.setCancelable(false)
            progressDialog.show()

        }

        fun hideDialog(progressDialog : ProgressDialog) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

        }

        fun showingToast(context: Context , message: String?){
            Toast.makeText(
                context, message,
                Toast.LENGTH_SHORT
            ).show()
        }




        fun startActivity(context:Context , nextActivity:Class<*>){
            context.startActivity(Intent(context ,nextActivity))

        }
        fun startActivity(context:Context , nextActivity:Class<*> , bundle: Bundle){
           val intent =Intent(context,nextActivity)
            intent.putExtra("data",bundle)
            context.startActivity(intent)

        }
        fun getBitmap(path: String , image:ImageView): Bitmap? {
            var bitmap: Bitmap? = null

            try {
                val f = File(path)
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888

                bitmap = BitmapFactory.decodeStream(FileInputStream(f), null, options)
                image.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
            return bitmap

        }


        fun objectToJSONObject(`object`: Any): JSONObject? {
            var json: Any? = null
            var jsonObject: JSONObject? = null
            try {
                json = JSONTokener(`object`.toString()).nextValue()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            if (json is JSONObject) {
                jsonObject = json
            }
            return jsonObject
        }

        fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
            return Uri.parse(path)
        }

        fun getScreenShot(view: View): Bitmap {
            val screenView = view.rootView
            screenView.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(screenView.drawingCache)
            screenView.isDrawingCacheEnabled = false
            return bitmap
        }

        fun CorrectUi(windowManager: WindowManager, resources: Resources) {
            try {
                Log.e("res issue ", "called")
                Log.e("res issue ", "called")
                val metrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(metrics)
                Log.e("qmrMatrics","${metrics.density}/${metrics.densityDpi}/${metrics.xdpi}/${metrics.ydpi}")

                metrics.density = 3.0f

                metrics.densityDpi = 480
                metrics.heightPixels = 2160
                metrics.widthPixels = 1080
                metrics.scaledDensity = 3.0f
                metrics.xdpi = 480.0f
                metrics.ydpi = 480.0f
                resources.displayMetrics.setTo(metrics)
                Log.e("res issue ", Gson().toJson(resources.displayMetrics))



                //resources.flushLayoutCache()
            } catch (e: Exception) {
                Log.e("res issue error", e.toString())
            }

            Handler().postDelayed({
                try {
                    val metrics = DisplayMetrics()
                    windowManager.defaultDisplay.getMetrics(metrics)
                    metrics.density = 1.0f

                    metrics.densityDpi = 160
                    metrics.heightPixels = 1280
                    metrics.widthPixels = 800
                    metrics.scaledDensity = 1.0f
                    metrics.xdpi = 160.0f
                    metrics.ydpi = 160.0f
                    resources.displayMetrics.setTo(metrics)
                } catch (e: Exception) {
                    Log.e("res issue error", e.toString())
                }
            }, 25)

        }



        fun String.toDate(
            dateFormat: String = "yyyy-MM-dd HH:mm:ss",
            timeZone: TimeZone = TimeZone.getTimeZone(
                "UTC"
            )
        ): Date {
            val parser = SimpleDateFormat(
                dateFormat,
                Locale.getDefault()
            )
            parser.timeZone = timeZone
            return parser.parse(this)
        }

        fun Date.formatTo(
            dateFormat: String,
            timeZone: TimeZone = TimeZone.getDefault()
        ): String {
            val formatter =
                SimpleDateFormat(
                    dateFormat,
                    Locale.getDefault()
                )
            formatter.timeZone = timeZone
            return formatter.format(this)
        }

    }




    }


