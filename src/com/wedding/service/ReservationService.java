package com.wedding.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wedding.dto.FoodPrice;
import com.wedding.dto.MonthRevenueDTO;
import com.wedding.dto.ReservationUpdateDTO;
import com.wedding.dto.ServicePrice;
import com.wedding.dto.TotalRevenueDTO;
import com.wedding.models.Reservation;
import com.wedding.models.ReservationUpdate;
import com.wedding.models.ServiceReservation;
import com.wedding.repository.FoodRepository;
import com.wedding.repository.ReservationRepository;
import com.wedding.repository.ServiceRepository;

public class ReservationService {
	private Gson gson = new Gson();
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
		int tablePrice = calTablePrice(reservation.getListFoodID(), 0);
		int totalServicePrice = calTotalServicePrice(reservation.getListServiceReservation());
		int totalTablePrice = (reservation.getTableQuantity() + reservation.getReservedTable()) * tablePrice;
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

	public int calTablePrice(List<Integer> listFoodID, int id) {
		List<FoodPrice> foodAndpriceList = new ArrayList<FoodPrice>();
		if (id == 0)
			foodAndpriceList = foodRepository.getListFoodAndPrice();
		else
			foodAndpriceList = foodRepository.getPriceInInvoice(id);

		int result = 0;
		for (int i : listFoodID) {
			for (FoodPrice foodprice : foodAndpriceList) {
				if (i == foodprice.getFoodID())
					result += foodprice.getFoodPrice();
			}
		}
		return result;
	}

	public int calTotalServicePrice(List<ServiceReservation> listServiceReservation) {

		List<ServicePrice> serviceAndpriceList = serviceRepository.getListServiceAndPrice();
		int result = 0;
		for (ServiceReservation serviceReservation : listServiceReservation) {
			for (ServicePrice serviceprice : serviceAndpriceList) {
				if (serviceReservation.getServiceID() == serviceprice.getServiceID())
					result += serviceprice.getServicePrice() * serviceReservation.getServiceQuantity();
			}
		}
		return result;
	}

	public List<ReservationUpdate> getAllWedding() {
		return reservationRepository.getAll();
	}

	public ReservationUpdate getReservationById(int id) {
		return reservationRepository.getReservationById(id);
	}

	public ReservationUpdateDTO convertJsonToReservationDTO(String json) {
		return reservationRepository.convertJSONtoReservationDTO(json);
	}

	public void deleteReservation(int id) {
		reservationRepository.delete(id);
	}


	public void updateReservation(ReservationUpdateDTO reservationUpdate) {
		updateInvoice(reservationUpdate.getListServiceReservation(), reservationUpdate.getListFoodID(),
				reservationUpdate.getWeddingID());

		int tablePrice = calTablePrice(reservationUpdate.getListFoodID(), reservationUpdate.getWeddingID());
		int totalServicePrice = serviceRepository.getTotalServicePrice(reservationUpdate.getWeddingID());
		int totalTablePrice = (reservationUpdate.getTableQuantity() + reservationUpdate.getReservedTable())*tablePrice;
		int totalWeddingPrice = totalServicePrice + totalTablePrice;
		int balance = totalWeddingPrice - reservationUpdate.getDeposit();

		reservationUpdate.setTablePrice(tablePrice);
		reservationUpdate.setTotalServicePrice(totalServicePrice);
		reservationUpdate.setTotalTablePrice(totalTablePrice);
		reservationUpdate.setTotalWeddingPrice(totalWeddingPrice);
		reservationUpdate.setBalance(balance);
		
		reservationRepository.update(reservationUpdate);
	}

	public void updateInvoice(List<ServiceReservation> listServiceReservation, List<Integer> listFoodID,
			int weddingID) {
		String jsonService = gson.toJson(listServiceReservation);
		String jsonFood = gson.toJson(listFoodID);
		jsonFood = jsonFood.replace("[", "");
		jsonFood = jsonFood.replace("]", "");
		String updateDate = java.time.LocalDate.now().toString();
		reservationRepository.updateInvoice(jsonService, jsonFood, weddingID, updateDate);

	}
	public void pay(int id, int userID) {
		reservationRepository.pay(id, userID);
	}
	public List<Integer> getTotalRevenue(int year) {
		List<TotalRevenueDTO> list = reservationRepository.getTotalRevenue(year);
		List<Integer> listRevenue = new ArrayList<Integer>();
		boolean isExisted = false;
		for(int i = 1; i <= 12; i++) {
			for(TotalRevenueDTO revenue : list) {
				int month = Integer.parseInt(revenue.getMonth());
				if(month == i) {
					isExisted = true;
					listRevenue.add(revenue.getTotalRevenue());
					break;
				}
			}
			if(!isExisted) {
				listRevenue.add(0);
			}
			isExisted = false;
		}
		return listRevenue;
	}
	
	public List<MonthRevenueDTO> getMonthRevenue(int year, int month) {
		List<MonthRevenueDTO> monthRevenue = reservationRepository.getMonthRevenue(year, month);
		int total = 0;
		for(MonthRevenueDTO day : monthRevenue) {
			total += day.getRevenue();
		}
		System.out.println(total);
		for(MonthRevenueDTO day : monthRevenue) {
			float proportion = Math.round((float)day.getRevenue() * 100 / (float)total);
			day.setProportion(proportion);
		}
		
		return monthRevenue;
	}
}
