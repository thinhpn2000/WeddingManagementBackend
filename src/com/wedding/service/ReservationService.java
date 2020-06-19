package com.wedding.service;

import java.util.List;

import com.wedding.dto.FoodPrice;
import com.wedding.dto.ServicePrice;
import com.wedding.models.Reservation;
import com.wedding.models.ServiceReservation;
import com.wedding.models.ReservationUpdate;
import com.wedding.repository.FoodRepository;
import com.wedding.repository.ReservationRepository;
import com.wedding.repository.ServiceRepository;

public class ReservationService {

	private ReservationRepository reservationRepository;
	private FoodRepository foodRepository;
	private ServiceRepository serviceRepository;
	
	public ReservationService() {
		reservationRepository = new ReservationRepository();
		foodRepository = new FoodRepository();
		serviceRepository = new ServiceRepository();
	}
	
	public Reservation convertJSONtoReservation(String JSON) {
		return reservationRepository.convertJSONtoReservation(JSON);
	}
	
	public void addReservation(Reservation reservation) {
		int tablePrice = calTablePrice(reservation.getListFoodID());
		int totalServicePrice = calTotalServicePrice(reservation.getListServiceReservation());
		int totalTablePrice = (reservation.getTableQuantity() + reservation.getReservedTable())*tablePrice;
		int totalWeddingPrice = totalServicePrice + totalTablePrice;
		int balance = totalWeddingPrice - reservation.getDeposit();
		
		reservation.setTablePrice(tablePrice);
		reservation.setTotalServicePrice(totalServicePrice);
		reservation.setTotalTablePrice(totalTablePrice);
		reservation.setReservationDate(java.time.LocalDate.now().toString());
		reservation.setTotalWeddingPrice(totalWeddingPrice);
		reservation.setBalance(balance);
		
		
		reservationRepository.add(reservation);
	}
	public int calTablePrice(List<Integer> listFoodID) {
		List<FoodPrice> foodAndpriceList = foodRepository.getListFoodAndPrice();
		int result = 0;
		for( int i : listFoodID) {
			for(FoodPrice foodprice : foodAndpriceList) {
				if(i == foodprice.getFoodID())
					result += foodprice.getFoodPrice();
			}
		}
		return result;
	}
	public int calTotalServicePrice(List<ServiceReservation> listServiceReservation) {
		
		List<ServicePrice> serviceAndpriceList = serviceRepository.getListServiceAndPrice();
		int result = 0;
		for( ServiceReservation serviceReservation: listServiceReservation) {
			for(ServicePrice serviceprice : serviceAndpriceList) {
				if( serviceReservation.getServiceID() == serviceprice.getServiceID())
					result += serviceprice.getServicePrice()*serviceReservation.getServiceQuantity();
			}
		}
		return result;
	}
	
	public List<ReservationUpdate> getAllWedding(){
		return reservationRepository.getAll();
	}
	
	public ReservationUpdate getReservationById(int id) {
		return reservationRepository.getReservationById(id);
	}
	
	public void deleteReservation(int id) {
		reservationRepository.delete(id);
	}
	
}
