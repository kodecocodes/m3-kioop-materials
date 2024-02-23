package com.yourcompany.museumtry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.yourcompany.museumtry.ui.theme.MuseumTryTheme

// DONE: Implement Comparable Interface
open class MuseumObject(
  val objectID: Int,
  val title: String,
  val objectURL: String,
  val creditLine: String,
  val isPublicDomain: Boolean
): Comparable<MuseumObject> {

  @SuppressLint("ComposableNaming")
  @Composable
  open fun showImage() {
    return WebViewComposable(url = objectURL)
  }

  override fun compareTo(other: MuseumObject): Int {
//    return when {
//      objectID > other.objectID -> 1
//      objectID == other.objectID -> 0
//      else -> -1
//    }
    return objectID.compareTo(other.objectID)
  }
}

val obj =
  MuseumObject(
    objectID = 436535,
    title = "Wheat Field with Cypresses",
    objectURL = "https://www.metmuseum.org/art/collection/search/436535",
//    primaryImageSmall = "https://images.metmuseum.org/CRDImages/ep/original/DT1567.jpg",
    creditLine = "Purchase, The Annenberg Foundation Gift, 1993",
    isPublicDomain = true
  )

val obj2 =
  MuseumObject(
    objectID = 11521,
    title = "Afternoon among the Cypress",
    objectURL = "https://www.metmuseum.org/art/collection/search/11521",
    creditLine = "Gift of Mrs. Henrietta Zeile, 1909",
    isPublicDomain = false
  )

class PublicDomainObject(
  objectID: Int,
  title: String,
  objectURL: String,
  creditLine: String,
  isPublicDomain: Boolean = true,
  val primaryImageSmall: String,
) : MuseumObject(objectID, title, objectURL, creditLine, isPublicDomain) {

  @SuppressLint("ComposableNaming")
  @Composable
  override fun showImage() {
    return MuseumObjectComposable(obj = this)
  }
}

val obj_pd =
  PublicDomainObject(
    objectID = 436535,
    title = "Wheat Field with Cypresses",
    objectURL = "https://www.metmuseum.org/art/collection/search/436535",
    primaryImageSmall = "https://images.metmuseum.org/CRDImages/ep/original/DT1567.jpg",
    creditLine = "Purchase, The Annenberg Foundation Gift, 1993",
  )

// DONE: Create OnDisplay interface
interface OnDisplay {
  val galleryNumber: String get() = ""
  fun showMap(from: String, to: String)
}

// DONE: Create a MuseumObject subclass that conforms to OnDisplay interface
class OnDisplayObject(
  objectID: Int,
  title: String,
  objectURL: String,
  creditLine: String,
  isPublicDomain: Boolean = true,
  val galleryNum: String,
) : MuseumObject(objectID, title, objectURL, creditLine, isPublicDomain), OnDisplay {
  override val galleryNumber: String
    get() = this.galleryNum

  override fun showMap(from: String, to: String) {
    galleryNumber.takeIf { it.isNotEmpty() } ?: return
    // implementation
  }

}

// DONE: Create OnDisplayObject instance
val obj_od =
  OnDisplayObject(
    objectID = 436535,
    title = "Wheat Field with Cypresses",
    objectURL = "https://www.metmuseum.org/art/collection/search/436535",
    creditLine = "Purchase, The Annenberg Foundation Gift, 1993",
    galleryNum = "822"
  )

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // DONE: Compare object based on objectID
    println("COMPARE ${obj > obj2}")
    setContent {
      MuseumTryTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          //          obj_pd.showImage()
//          obj.showImage()
//          obj2.showImage()
          obj_od.showImage()
        }
      }
    }
  }
}

@Composable
fun MuseumObjectComposable(obj: PublicDomainObject) {

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(rememberScrollState())
  ) {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .defaultMinSize(minHeight = 44.dp)
        .background(
          color = Color.Blue.copy(alpha = 0.2f,),
          shape = RoundedCornerShape(10.dp)
        )
        .padding(horizontal = 16.dp)
    ) {
      Text(text = obj.title)
    }
    AsyncImage(
      model = obj.primaryImageSmall,
      contentDescription = "MuseumObject Item Image",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .padding(vertical = 32.dp)
    )
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .defaultMinSize(minHeight = 44.dp)
        .background(
          color = Color.Blue.copy(alpha = 0.2f,),
          shape = RoundedCornerShape(10.dp)
        )
        .padding(horizontal = 16.dp)
    ) {
      Text(text = obj.creditLine)
    }
  }
}


@Composable
fun WebViewComposable(url: String) {
  // A full screen layout WebView inside an AndroidView
  AndroidView(
    factory = { context ->
      WebView(context).apply {
        layoutParams = ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT
        )
        webViewClient = WebViewClient()
        loadUrl(url)
      }
    },
    update = { webView ->
      webView.loadUrl(url)
    })

}
