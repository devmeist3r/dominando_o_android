package com.devmeist3r.hotel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HotelDetailsActivity : AppCompatActivity() {

  private val hotelId: Long by lazy { intent.getLongExtra(EXTRA_HOTEL_ID, -1) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hotel_details)
    if (savedInstanceState == null) {
      showHotelDetailsFragment()
    }
  }

  private fun showHotelDetailsFragment() {
    val fragment = HotelDetailsFragment.newInstance(hotelId)
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
      .commit()
  }

  override fun getParentActivityIntent(): Intent? {
    val it = super.getParentActivityIntent()
    it?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    return it
  }

  companion object {
    private const val EXTRA_HOTEL_ID = "hotel_id"
    fun open(context: Context, hotelId: Long) {
      context.startActivity(Intent(context, HotelDetailsActivity::class.java).apply {
        putExtra(EXTRA_HOTEL_ID, hotelId)
      })
    }
  }
}
