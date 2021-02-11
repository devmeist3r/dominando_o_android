package com.devmeist3r.hotel.details

import com.devmeist3r.hotel.repository.memory.HotelRepository

class HotelDetailsPresenter(
    private val view: HotelDetailsView,
    private val repository: HotelRepository
) {
  fun loadHotelDetails(id: Long) {
    repository.hotelById(id) { hotel ->
      if (hotel != null) {
        view.showHotelDetails(hotel)
      } else {
        view.errorHotelNotFoound()
      }
    }
  }
}
