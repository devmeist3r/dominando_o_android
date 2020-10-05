package com.devmeist3r.hotel

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
